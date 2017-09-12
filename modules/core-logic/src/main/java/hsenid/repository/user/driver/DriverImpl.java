package hsenid.repository.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;
import java.sql.Driver;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hsenid on 9/6/17.
 */
@Repository

public class DriverImpl implements DriverRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    private static final AtomicLong counter = new AtomicLong();

    public DriverImpl() {

    }


    public boolean isDriverAuthenticate(String email, String password) {

        String sql = "SELECT COUNT(*) FROM driver WHERE email=? AND password=? ";

        int result = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public boolean registerDriver(String email, String password, String first_name, String last_name, String license_number, String contact_number, Date birthday, String gender, Integer driver_status, Integer confirmed_by, String nic) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        try {

            String sql = "INSERT INTO driver(email,password,first_name,last_name,license_number,contact_number,birthday,gender,driver_status,confirmed_by,nic) VALUES (?,?,?,?,?,?,?,?,1,?,?);";

            jdbcTemplate.update(sql, email, password, first_name, last_name, license_number, contact_number, birthday, gender, first_name, nic);
            System.out.println("Query 1 completed");

            String getDriverId = "SELECT driver_id FROM driver WHERE email= ? LIMIT 1 ";
            String driver_id = jdbcTemplate.queryForObject(getDriverId, new Object[]{email}, String.class);


            System.out.println("Driver Id : " + driver_id);

            String defaultLocationSql = "INSERT INTO driver_current_location(driver_id,longitude,latitude,update_time) VALUES(?,?,?,NOW())";
            jdbcTemplate.update(defaultLocationSql, driver_id, 0.000000, 0.000000);

            platformTransactionManager.commit(transactionStatus);
            return true;
        } catch (DataAccessException e) {
            System.out.println("Exception msg => " + e.getMessage());
            platformTransactionManager.rollback(transactionStatus);
        }
        return false;

    }

    public void sendDriverDelails() {

    }



}

