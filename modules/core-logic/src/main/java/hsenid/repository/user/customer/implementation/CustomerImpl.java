package hsenid.repository.user.customer.implementation;

import hsenid.domain.user.customer.Customer;
import hsenid.repository.user.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Menuka on 9/6/17.
 */
@Repository
public class CustomerImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;

    public CustomerImpl() {
    }

    public boolean isCustomerAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM customer WHERE email = ? AND password = ?";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
        return count > 0;
    }

    //    Add object
    public boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday, String contact_number, String nic, String gender) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        System.out.println("value => " + email);
        try {
            String sql = "INSERT INTO customer (email, password, first_name, last_name, birthday, contact_number, nic, gender, user_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1);";
            jdbcTemplate.update(sql, email, password, first_name, last_name, birthday, contact_number, nic, gender);

            String getCustomerId = "SELECT customer_id FROM customer WHERE email = ? LIMIT 1";
            String customerId = jdbcTemplate.queryForObject(getCustomerId, new Object[]{email}, String.class);

            String defaultLocationSql = "INSERT INTO customer_current_location (customer_id, longitude, latitude, update_time) VALUES (?, ?, ?, NOW())";
            jdbcTemplate.update(defaultLocationSql, customerId, 0.000000, 0.000000);
            transactionManager.commit(status);

            return true;
        } catch (DataAccessException e) {
            System.out.println("Exception msg => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    /**
     * @param emai
     * @return
     */

    public Customer sendCustomerDetails(String emai) {

        Customer customer = this.jdbcTemplate.queryForObject(
                "SELECT customer_id, email, first_name, last_name, birthday, contact_number, nic, gender, user_status FROM customer Limit 1",

                (rs, rowNum) -> {
                    Customer customerRowMapper = new Customer();
                    customerRowMapper.setCustomer_id(Integer.parseInt(rs.getString("customer_id")));
                    customerRowMapper.setEmail(rs.getString("email"));
                    customerRowMapper.setFirst_name(rs.getString("first_name"));
                    customerRowMapper.setLast_name(rs.getString("last_name"));
                    customerRowMapper.setBirthday(rs.getDate("birthday"));
                    customerRowMapper.setContact_number(rs.getString("contact_number"));
                    customerRowMapper.setNic(rs.getString("nic"));
                    customerRowMapper.setGender(rs.getString("gender"));
                    customerRowMapper.setUser_status(rs.getInt("user_status"));

                    return customerRowMapper;
                });

        return customer;
    }

}
