package hsenid.repository.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hsenid on 9/6/17.
 */
@Repository
public class CustomerImpl implements CustomerRepository{

    public CustomerImpl() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String isCustomerAuthenticated(String username, String password) {
        int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customer", Integer.class);

        return String.valueOf(result);
    }
}
