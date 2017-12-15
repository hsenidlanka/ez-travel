package corelogic.repository.hire.implementation;

import corelogic.repository.hire.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class LocationImpl implements LocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public boolean updateDriverCurrentLocation(String email, float longitude, float latitude) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);


        try{
            String sqlForGetDriverId = "SELECT driver_id FROM driver WHERE email = ?";
            Object[] args = new Object[]{email};

            String driverId = jdbcTemplate.queryForObject(sqlForGetDriverId, new Object[]{email}, String.class);

            String sqlForUpdateDriverLocation = "UPDATE driver_current_location set longitude = ?,latitude = ?, update_time = NOW() WHERE driver_id = ?";

            Object[] argsForLocationUpdate = new Object[]{longitude, latitude, Integer.parseInt(driverId)};

            jdbcTemplate.update(sqlForUpdateDriverLocation, argsForLocationUpdate);

            transactionManager.commit(status);
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
