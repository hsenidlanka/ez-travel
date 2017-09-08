package hsenid.repository.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;

/**
 * Created by hsenid on 9/6/17.
 */
@Repository
public class CustomerImpl implements CustomerRepository{

    public CustomerImpl() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;



    public boolean isCustomerAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM customer WHERE email = ? AND password = ?";

        int count = jdbcTemplate.queryForObject(sql, new Object[] { email, password }, Integer.class);
        if (count > 0){
            return true;
        }

        return false;
    }

    public boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday, String contact_number, String nic, String gender) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql = "INSERT INTO customer (email, password, first_name, last_name, birthday, contact_number, nic, gender, user_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1);";
            jdbcTemplate.update(sql, email, password, first_name, last_name, birthday, contact_number, nic, gender);

            System.out.println("Query 1 completed");

            String getCustomerId = "SELECT customer_id FROM customer WHERE email = ? LIMIT 1";
            String customerId = jdbcTemplate.queryForObject(getCustomerId, new Object[]{email}, String.class);


            System.out.println("Customer id =>" + customerId);
//            System.out.println(customerId);
            System.out.println("Query 2 completed");

            String defaultLocationSql = "INSERT INTO customer_current_location (customer_id, longitude, latitude, update_time) VALUES (?, ?, ?, NOW())";
            jdbcTemplate.update(defaultLocationSql, customerId, 0.000000, 0.000000);
            System.out.println("Query 3 completed");

            transactionManager.commit(status);

            return true;
        } catch (DataAccessException e) {
            System.out.println("Exception msg => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }


}
