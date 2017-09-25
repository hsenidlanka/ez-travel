package hsenid.repository.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
        return  result > 0;
    }

    public boolean registerDriver(hsenid.repository.user.driver.domain.Driver driver) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        try {

            String sql = "INSERT INTO driver(email,password,first_name,last_name,license_number,contact_number," +
                    "birthday,gender,driver_status,confirmed_by,nic) VALUES (?,?,?,?,?,?,?,?,1,?,?);";

            jdbcTemplate.update(sql, driver.getEmail(), driver.getPassword(), driver.getFirst_name(),
                    driver.getLast_name(), driver.getLicense_number(), driver.getContact_number(), driver.getBirthday(),
                    driver.getGender(), driver.getFirst_name(), driver.getNic());
            System.out.println("Query 1 completed");

            String getDriverId = "SELECT driver_id FROM driver WHERE email= ? LIMIT 1 ";
            String driver_id = jdbcTemplate.queryForObject(getDriverId, new Object[]{driver.getEmail()}, String.class);


            System.out.println("Driver Id : " + driver_id);

            String defaultLocationSql = "INSERT INTO driver_current_location(driver_id,longitude,latitude,update_time) " +
                    "VALUES(?,?,?,NOW())";
            jdbcTemplate.update(defaultLocationSql, driver_id, 0.000000, 0.000000);

            platformTransactionManager.commit(transactionStatus);
            return true;
        } catch (DataAccessException e) {
            // TODO: 9/15/17 add loggers
            System.out.println("Exception msg => " + e.getMessage());
            platformTransactionManager.rollback(transactionStatus);
        }
        return false;

    }

    public boolean UploadImages(byte driver_licence,byte revenue_licence,byte insurance,byte driver_image )
    {
        TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        TransactionStatus transactionStatus=platformTransactionManager.getTransaction(transactionDefinition);
        try {
            /*
            INSERT INTO Results (People, names )
   SELECT d.id, 'Henry'
   FROM Names f
   JOIN People d ON d.id  = f.id
             */
            String sql="INSERT INTO driver_image (driver_licence,revenue_licence,insurance,driver_image)  ";
        }

    }
    public void sendDriverDelails() {

    }



}

