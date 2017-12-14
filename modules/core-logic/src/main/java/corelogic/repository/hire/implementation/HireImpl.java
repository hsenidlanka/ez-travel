package corelogic.repository.hire.implementation;

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
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class HireImpl implements HireRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

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

    @Override
    public boolean placeHire(String customer_email,
                             String start_location_latitude,
                             String start_location_longitude,
                             String vehicle_type,
                             Date dateTime) {


        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            String sqlForGetCustomerId = "SELECT customer_id FROM customer WHERE email = ?";
            Object[] args = new Object[]{customer_email};

            String customer_id = jdbcTemplate.queryForObject(sqlForGetCustomerId, args, String.class);

            String sqlForIntialHirePlace = "INSERT INTO hire (start_location_latitude, start_location_longitude, vehicle_type, date, customer_id) VALUES (?, ?, ?, ?, ?)";

            Object[] argsForPlaceHire = new Object[]{start_location_latitude, start_location_longitude, vehicle_type, dateTime, Integer.parseInt(customer_id)};
            jdbcTemplate.update(sqlForIntialHirePlace, argsForPlaceHire);

            transactionManager.commit(status);

            return true;
        }catch (Exception e){

            System.out.println(e.getMessage());
        }
        return false;
    }



}
