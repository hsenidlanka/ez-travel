package hsenid.repository.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hsenid on 9/6/17.
 */
@Repository
public class DriverImpl implements DriverRepository {
    private Object email;

    public DriverImpl() {

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDriverAuthenticate(String email, String password) {

        String sql="SELECT COUNT(*) FROM driver WHERE email=? AND password=? ";

        int result = jdbcTemplate.queryForObject(sql,new Object[]{ email,password}, Integer.class);
        if(result>0)
        {
            return true;
        }
        return false;
    }
}

