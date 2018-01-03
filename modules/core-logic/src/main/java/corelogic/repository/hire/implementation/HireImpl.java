package corelogic.repository.hire.implementation;

import corelogic.domain.hire.CustomerHireRecord;
import corelogic.domain.hire.DriverHireRecord;
import corelogic.domain.hire.IntialHireModel;
import corelogic.repository.hire.Repository.HireRepository;
import corelogic.repository.user.driver.Implementation.DriverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation class for Hire related Database activities.
 * This use for autowire
 *
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class HireImpl implements HireRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LocationImpl locationImpl;

    @Autowired
    private DriverImpl driverImpl;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * This method contains the algorithm for cost of trip calculation
     *
     * @param length      - length of trip
     * @param vehicleType - type of vehicle use
     * @return - calculated cost as double
     */
    @Override
    public double sendCostCalculated(String length, String vehicleType) {

        double numOfKm = Double.parseDouble(length);
        double totalCost = 50.0;

        if ((numOfKm - 1) < 1) {
            return totalCost;
        }

        double nextLength = numOfKm - 1;

        switch (vehicleType) {
            case "budget":
                totalCost += nextLength * 35;
                break;
            case "hybrid":
                totalCost += nextLength * 55;
                break;
            default:
                totalCost += nextLength * 45;
        }

        return totalCost;
    }

    /**
     * This method is responsible for placing initial hire request
     *
     * @param customer_email           - customer's email who is going to place the hire
     * @param start_location_latitude  - customer's starting point latitude
     * @param start_location_longitude - customer's starting point longitude
     * @param vehicle_type             - type of vehicle use
     * @param date                     - date of trip
     * @param time                     - starting time of trip
     * @return - IntialHireModel model with hirePlaceSucces, hireId
     */
    @Override
    public IntialHireModel placeHire(String customer_email,
                                     String start_location_latitude,
                                     String start_location_longitude,
                                     String vehicle_type,
                                     Date date,
                                     String time) {


        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        IntialHireModel hireModel = new IntialHireModel();

        try {

            String sqlForGetCustomerId = "SELECT customer_id FROM customer WHERE email = ?";
            Object[] args = new Object[]{customer_email};

            String customer_id = jdbcTemplate.queryForObject(sqlForGetCustomerId, args, String.class);

            String sqlForIntialHirePlace = "INSERT INTO hire (start_location_latitude, start_location_longitude, vehicle_type, date, time, customer_id) VALUES (?, ?, ?, ?, ?, ?)";

            Object[] argsForPlaceHire = new Object[]{start_location_latitude, start_location_longitude, vehicle_type, date, time, Integer.parseInt(customer_id)};
            jdbcTemplate.update(sqlForIntialHirePlace, argsForPlaceHire);

            String sqlForGetHireId = "SELECT hire_id FROM hire WHERE customer_id = ? AND date = ? LIMIT 1 ";
            Object[] argsForHireIdGet = new Object[]{Integer.parseInt(customer_id), date};
            String hire_id = jdbcTemplate.queryForObject(sqlForGetHireId, argsForHireIdGet, String.class);

            hireModel.setHireId(hire_id);
            hireModel.setHirePlaceSucces(true);
            transactionManager.commit(status);

            return hireModel;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        hireModel.setHirePlaceSucces(false);
        hireModel.setHireId(String.valueOf(0));

        return hireModel;
    }

    /**
     * This method is responsible for completing hire deatils storing
     *
     * @param hire_id                - hire id
     * @param end_location_latitude  - customer's end point latitude
     * @param end_location_longitude - customer's end point longitude
     * @param cost                   - cost of trip
     * @param driver_id              - driver's id who completed the hire
     * @param length                 - length of trip
     * @return - boolean saying hire placement succeed or not
     */
    @Override
    public boolean confirmHirePlacement(int hire_id,
                                        float end_location_latitude,
                                        float end_location_longitude,
                                        double cost,
                                        int driver_id,
                                        double length) {


        String sqlForUpdateCustomer = "UPDATE hire set end_location_latitude = ?,  end_location_longitude = ?, cost = ?, driver_id = ? ,length=?, hire_status=1 where hire_id = ?";
        Object[] args = new Object[]{end_location_latitude, end_location_longitude, cost, driver_id, length, hire_id};

        boolean isHirePlacementConfirmed = (jdbcTemplate.update(sqlForUpdateCustomer, args) == 1);

        return isHirePlacementConfirmed;
    }

    @Override
    public List<CustomerHireRecord> getCustomerHireDetails(String customer_email) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        List<CustomerHireRecord> hireRecords = new ArrayList<>();

        try {
            String sqlForGetCustomerId = "SELECT customer_id FROM customer WHERE email = ?";
            Object[] args = new Object[]{customer_email};
            String customer_id = jdbcTemplate.queryForObject(sqlForGetCustomerId, args, String.class);

            Object[] argsForHireRecords = new Object[]{Integer.parseInt(customer_id)};
            String sqlForCustomerHireRecords = "SELECT * FROM hire WHERE customer_id=? AND hire_status=1";

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlForCustomerHireRecords, argsForHireRecords);
            for (Map row : rows) {
                CustomerHireRecord record = new CustomerHireRecord();

                record.setHire_id((Integer) row.get("hire_id"));
                record.setVehicle_type((String) row.get("vehicle_type"));
                record.setLength((Double) row.get("length"));
                record.setCost((Double) row.get("cost"));

                double startLatitude = (float) row.get("start_location_latitude");
                double startLongitude = (float) row.get("start_location_longitude");
                record.setFrom(locationImpl.giveAddressOfGivenCordinates(startLatitude, startLongitude));

                double endLatitude = (float) row.get("end_location_latitude");
                double endLongitude = (float) row.get("end_location_longitude");
                record.setTo(locationImpl.giveAddressOfGivenCordinates(endLatitude, endLongitude));

                int driver_id = (int) row.get("driver_id");

                record.setDriver_id(driver_id);
                record.setDriver_name(driverImpl.sendDriverName(driver_id));
                record.setDate((Date) row.get("date"));

                hireRecords.add(record);
            }

            transactionManager.commit(status);
            return hireRecords;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return hireRecords;

    }

    @Override
    public List<DriverHireRecord> getDriverHireDetails(String driver_email) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        List<DriverHireRecord> hireRecords = new ArrayList<>();

        try {
            String sqlForGetDriverId = "SELECT driver_id FROM driver WHERE email = ?";
            Object[] args = new Object[]{driver_email};
            String driver_id = jdbcTemplate.queryForObject(sqlForGetDriverId, args, String.class);

            Object[] argsForHireRecords = new Object[]{Integer.parseInt(driver_id)};
            String sqlForDriverHireRecords = "SELECT * FROM hire WHERE driver_id=? AND hire_status=1";

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlForDriverHireRecords, argsForHireRecords);
            for (Map row : rows) {
                DriverHireRecord record = new DriverHireRecord();

                record.setHire_id((Integer) row.get("hire_id"));
                record.setVehicle_type((String) row.get("vehicle_type"));
                record.setLength((Double) row.get("length"));
                record.setCost((Double) row.get("cost"));

                double startLatitude = (float) row.get("start_location_latitude");
                double startLongitude = (float) row.get("start_location_longitude");
                record.setFrom(locationImpl.giveAddressOfGivenCordinates(startLatitude, startLongitude));

                double endLatitude = (float) row.get("end_location_latitude");
                double endLongitude = (float) row.get("end_location_longitude");

                record.setTo(locationImpl.giveAddressOfGivenCordinates(endLatitude, endLongitude));

                int customer_id = (int) row.get("customer_id");

                record.setCustomer_id(customer_id);

                record.setDate((Date) row.get("date"));

                hireRecords.add(record);
            }

            transactionManager.commit(status);
            return hireRecords;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return hireRecords;
    }
}
