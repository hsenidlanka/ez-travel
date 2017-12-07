package corelogic.repository.user.driver.Implementation;

import corelogic.domain.user.driver.Driver;
import corelogic.repository.user.driver.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;

/**
 * Created by Vidushika on 12/5/17.
 */
public class DriverImpl implements DriverRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public DriverImpl() {
    }


    @Override
    public boolean isDriverAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM driver WHERE email=? AND password=?";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);

        return count > 0;
    }

    @Override
    public boolean registerDriver(String email, String password, String first_name, String last_name, String license_number, String contact_number, Date birthday, String gender, String confirmed_by, String nic) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            String sql = "INSERT INTO driver(email,password,first_name,last_name,license_number,contact_number," +
                    "birthday,gender,driver_status,confirmed_by,nic) VALUES (?,?,?,?,?,?,?,?,2,?,?);";

            Object[] args = new Object[]{email, password, first_name, last_name, license_number, contact_number, birthday, gender, confirmed_by, nic};
            jdbcTemplate.update(sql, args);
            String getDriverId = "SELECT driver_id FROM driver WHERE email=? LIMIT 1";
            String driverId = jdbcTemplate.queryForObject(getDriverId, new Object[]{email}, String.class);

            String defaultLocationSql = "INSERT INTO driver_current_location(driver_id,longitude, latitude, update_time) VALUES(?,?,?,NOW()) ";
            jdbcTemplate.update(defaultLocationSql, driverId, 0.000000, 0.000000);
            platformTransactionManager.commit(transactionStatus);

            return true;

        } catch (DataAccessException e) {
            System.out.println("Exception msg => " + e.getMessage());
            platformTransactionManager.rollback(transactionStatus);
        }

        return false;
    }





    @Override
    public Driver sendDriverDetails(String email) {

        return this.jdbcTemplate.queryForObject("SELECT driver_id,email,password,first_name,last_name,license_number," +
                        "contact_number,birthday,gender,driver_status,confirmed_by,nic FROM driver Limit 1",
                (resultSet, i) -> {
                    Driver driverRowMapper = new Driver();

                    driverRowMapper.setDriver_id(Integer.parseInt(resultSet.getString("customer_id")));
                    driverRowMapper.setEmail(resultSet.getString("email"));
                    driverRowMapper.setFirst_name(resultSet.getString("first_name"));
                    driverRowMapper.setLast_name(resultSet.getString("last_name"));
                    driverRowMapper.setLicense_number(resultSet.getString("license_number"));
                    driverRowMapper.setBirthday(resultSet.getDate("birthday"));
                    driverRowMapper.setContact_number(resultSet.getString("contact_number"));
                    driverRowMapper.setNic(resultSet.getString("nic"));
                    driverRowMapper.setGender(resultSet.getString("gender"));
                    driverRowMapper.setDriver_status(resultSet.getInt("user_status"));
                    driverRowMapper.setConfirmed_by(resultSet.getInt("confirmed_by"));

                    return driverRowMapper;

                });

    }

    @Override
    public boolean isDriverDeleted(String email) {

        String sqlForDeleteDriver = "DELETE FROM driver WHERE email=?;";

        Object[] args = new Object[]{email};
        boolean isDeleted = (jdbcTemplate.update(sqlForDeleteDriver, args) == 1);

        return isDeleted;
    }

    @Override
    public boolean updatePassword(String email, String currentPassword, String newPassword) {

        String sqlForUpdateDriver = "UPDATE customer set password = ? where email = ? AND password = ?;";
        Object[] args = new Object[]{newPassword, email, currentPassword};

        boolean isPasswordUpdated = (jdbcTemplate.update(sqlForUpdateDriver, args) == 1);

        return isPasswordUpdated;
    }
}