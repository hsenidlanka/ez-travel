package hsenid.repository.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/6/17.
 */
@Repository
public class CustomerImpl implements CustomerRepository{

    public CustomerImpl() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isCustomerAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM customer WHERE email = ? AND password = ?";

        int count = jdbcTemplate.queryForObject(sql, new Object[] { email, password }, Integer.class);
        if (count > 0){
            return true;
        }

        return false;
    }

    public boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday, String contact_number, String nic, String gender) {

        String sql = "INSERT INTO customer (email, password, first_name, last_name, birthday, contact_number, nic, gender, user_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1);";
        jdbcTemplate.update(sql, email, password, first_name, last_name, birthday, contact_number, nic, gender);


        return false;
    }
}
