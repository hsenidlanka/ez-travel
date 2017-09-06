package hsenid.repository.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
