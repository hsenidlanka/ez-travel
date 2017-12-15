package corelogic.repository.hire.implementation;

import corelogic.domain.hire.IntialHireModel;
import corelogic.repository.hire.Repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;

/**
 * Implementation class for Hire related Database activities.
 * This use for autowire
 *
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class HireImpl implements HireRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * This method contains the algorithm for cost of trip calculation
     *
     * @param length - length of trip
     * @param vehicleType - type of vehicle use
     * @return - calculated cost as double
     */
    @Override
    public double sendCostCalculated(String length, String vehicleType) {

        double numOfKm = Double.parseDouble(length);
        double totalCost = 50.0;

        if ((numOfKm - 1) < 1){
            return totalCost;
        }

        double nextLength = numOfKm - 1;

        switch(vehicleType) {
            case "budget" :
                totalCost += nextLength * 35;
                break;
            case "hybrid" :
                totalCost += nextLength * 55;
                break;
            default :
                totalCost += nextLength * 45;
        }

        return totalCost;
    }

    /**
     * This method is responsible for placing initial hire request
     *
     * @param customer_email - customer's email who is going to place the hire
     * @param start_location_latitude - customer's starting point latitude
     * @param start_location_longitude - customer's starting point longitude
     * @param vehicle_type - type of vehicle use
     * @param date - date of trip
     * @param time - starting time of trip
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

            Object[] argsForPlaceHire = new Object[]{start_location_latitude, start_location_longitude, vehicle_type, date, time,Integer.parseInt(customer_id)};
            jdbcTemplate.update(sqlForIntialHirePlace, argsForPlaceHire);

            String sqlForGetHireId = "SELECT hire_id FROM hire WHERE customer_id = ? AND date = ? LIMIT 1 ";
            Object[] argsForHireIdGet = new Object[]{Integer.parseInt(customer_id), date};
            String hire_id = jdbcTemplate.queryForObject(sqlForGetHireId, argsForHireIdGet, String.class);

            hireModel.setHireId(hire_id);
            hireModel.setHirePlaceSucces(true);
            transactionManager.commit(status);

            return hireModel;
        }catch (Exception e){

            System.out.println(e.getMessage());
        }

        hireModel.setHirePlaceSucces(false);
        hireModel.setHireId(String.valueOf(0));

        return hireModel;
    }

    /**
     * This method is responsible for completing hire deatils storing
     *
     * @param hire_id - hire id
     * @param end_location_latitude - customer's end point latitude
     * @param end_location_longitude - customer's end point longitude
     * @param cost - cost of trip
     * @param driver_id - driver's id who completed the hire
     * @param length - length of trip
     * @return - boolean saying hire placement succeed or not
     */
    @Override
    public boolean confirmHirePlacement(int hire_id,
                                        float end_location_latitude,
                                        float end_location_longitude,
                                        double cost,
                                        int driver_id,
                                        double length) {


        String sqlForUpdateCustomer = "UPDATE hire set end_location_latitude = ?,  end_location_longitude = ?, cost = ?, driver_id = ? ,length=? where hire_id = ?";
        Object[] args = new Object[]{end_location_latitude, end_location_longitude, cost, driver_id, length, hire_id};

        boolean isHirePlacementConfirmed = (jdbcTemplate.update(sqlForUpdateCustomer, args) == 1);

        return isHirePlacementConfirmed;
    }


}
