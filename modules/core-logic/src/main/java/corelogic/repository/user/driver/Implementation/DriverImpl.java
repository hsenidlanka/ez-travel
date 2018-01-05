package corelogic.repository.user.driver.Implementation;


import corelogic.domain.user.driver.Driver;
import corelogic.domain.user.driver.DriverName;
import corelogic.repository.user.driver.Repository.DriverRepository;
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
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class DriverImpl implements DriverRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public DriverImpl() {
    }


    /**
     * This method is used for authentication
     * It will check whether user is in the database and not banned.
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public boolean isDriverAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM driver WHERE email=? AND password=? AND driver_status NOT IN (0)";
        Object[] args = new Object[]{email, password};

        System.out.printf(sql, args);
        int count = jdbcTemplate.queryForObject(sql, args, Integer.class);

        return count > 0;
    }

    /**
     * This method is responsible for add new drivers to database.
     * But there default driver status will be 2 and confirmed by will be null.
     * Because in the initial stage no one confirm it.
     * <p>
     * 0 - ban
     * 1 - valid user
     * 2 - not verified
     *
     * @param email          - new driver's email
     * @param password       - new driver's password
     * @param first_name     - new driver's First name
     * @param last_name      - new driver's Last name
     * @param license_number - new driver's license number
     * @param contact_number - new driver's contact number
     * @param birthday       - new driver's birthday
     * @param gender         - new driver's gender
     * @param nic            - new driver's National ID card number
     * @return - boolean
     */
    @Override
    public boolean registerDriver(String email, String password, String first_name, String last_name, String license_number, String contact_number, Date birthday, String gender, String nic) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            String sql = "INSERT INTO driver(email, password, first_name, last_name, license_number, contact_number, birthday, gender, driver_status, confirmed_by, nic) VALUES (?,?,?,?,?,?,?,?,2, NULL,?)";

            Object[] args = new Object[]{email, password, first_name, last_name, license_number, contact_number, birthday, gender, nic};
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


    /**
     * This method is responsible for sending driver's details
     *
     * @param email - email of driver which you need details
     * @return Driver object (corelogic.domain.user.driver.Driver;)
     */
    @Override
    public Driver sendDriverDetails(String email) {

        String sqlForDriverDetailsSend = "SELECT driver_id, email, password, first_name, last_name, license_number, contact_number, birthday, gender, driver_status, confirmed_by, nic FROM driver WHERE email = ?";
        Object[] args = new Object[]{email};

        Driver driverDetails = this.jdbcTemplate.queryForObject(sqlForDriverDetailsSend, args,
                (resultSet, i) -> {
                    Driver driverRowMapper = new Driver();

                    driverRowMapper.setDriver_id(Integer.parseInt(resultSet.getString("driver_id")));
                    driverRowMapper.setEmail(resultSet.getString("email"));
                    driverRowMapper.setFirst_name(resultSet.getString("first_name"));
                    driverRowMapper.setLast_name(resultSet.getString("last_name"));
                    driverRowMapper.setLicense_number(resultSet.getString("license_number"));
                    driverRowMapper.setBirthday(resultSet.getDate("birthday"));
                    driverRowMapper.setContact_number(resultSet.getString("contact_number"));
                    driverRowMapper.setNic(resultSet.getString("nic"));
                    driverRowMapper.setGender(resultSet.getString("gender"));
                    driverRowMapper.setDriver_status(resultSet.getInt("driver_status"));
                    driverRowMapper.setConfirmed_by(resultSet.getInt("confirmed_by"));

                    return driverRowMapper;

                });
        return driverDetails;

    }

    /**
     * This method is resposible for banning the drivers.
     *
     * @param email- email of driver who is going to be banned
     * @return
     */
    @Override
    public boolean banDriver(String email) {
        String sqlForBanningDriver = "UPDATE driver set driver_status=0 WHERE email=? ";

        Object[] args = new Object[]{email};
        boolean isDriverBanSuccess = (jdbcTemplate.update(sqlForBanningDriver, args) == 1);
        return isDriverBanSuccess;
    }

    /**
     * This method responsible for self account deletion of driver
     *
     * @param email    - Email of driver, who request for account deletion
     * @param password - password of the driver
     * @return - boolean
     */
    @Override
    public boolean isDriverDeleted(String email, String password) {

        String sqlForDeleteDriver = "DELETE FROM driver WHERE email=? AND password= ?;";

        Object[] args = new Object[]{email, password};
        boolean isDeleted = (jdbcTemplate.update(sqlForDeleteDriver, args) == 1);

        return isDeleted;
    }

    /**
     * This method is responsible for updating the password
     *
     * @param email           - email of driver who request for password change
     * @param currentPassword - current password of the driver
     * @param newPassword     - the new password which is going to assign to driver(if current password matches)
     * @return - boolean
     */
    @Override
    public boolean updatePassword(String email, String currentPassword, String newPassword) {

        String sqlForUpdateDriver = "UPDATE driver set password = ? where email = ? AND password = ?;";
        Object[] args = new Object[]{newPassword, email, currentPassword};

        boolean isPasswordUpdated = (jdbcTemplate.update(sqlForUpdateDriver, args) == 1);

        return isPasswordUpdated;
    }


    /**
     * This method is responsible for updating driver's first name, last name and contact number
     *
     * @param email         - driver's email you want to details updated
     * @param firstName     - driver's new first name or previous
     * @param lastName      - driver's new last name or previous
     * @param contactNumber - driver's new contact number or previous
     * @return - boolean
     */
    @Override
    public boolean updateContactDetails(String email, String firstName, String lastName, String contactNumber) {

        String sqlForUpdateDriverContacts = "UPDATE driver set first_name = ?, last_name=? , contact_number=? where email = ?";

        Object[] args = new Object[]{firstName, lastName, contactNumber, email};
//        System.out.println("Updated ? => " + jdbcTemplate.update(sqlForUpdateDriverContacts, args));

        boolean isContactsUpdated = (jdbcTemplate.update(sqlForUpdateDriverContacts, args) == 1);

        return isContactsUpdated;
    }

    /**
     * This method is responsible to search whether the owner of email is actually in the database or not.
     * Implemented for Error handling purpose.
     *
     * @param email - Email of driver, who we need to search whether they are in the database or not
     * @return
     */
    @Override
    public boolean isDriverInDatabase(String email) {

        String sqlForDriverrAvailability = "SELECT COUNT(*) FROM driver WHERE email = ?";
        Object[] args = new Object[]{email};
        int count = jdbcTemplate.queryForObject(sqlForDriverrAvailability, args, Integer.class);

        return count > 0;
    }

    @Override
    public String sendDriverName(int driver_id) {
        String sqlForDriverName = "SELECT first_name, last_name FROM driver WHERE driver_id=?";
        Object[] args = new Object[]{driver_id};

        DriverName driverName = this.jdbcTemplate.queryForObject(sqlForDriverName, args,
                (resultSet, i) -> {
                    DriverName rowMapper = new DriverName();

                    rowMapper.setFirstName(resultSet.getString("first_name"));
                    rowMapper.setLastName(resultSet.getString("last_name"));

                    return rowMapper;

                });

        return driverName.toString();
    }

    @Override
    public int countOfVerifiedDrivers() {
        String sqlForVerifiedDrivers = "SELECT COUNT(driver_id) FROM driver WHERE driver_status= 1";
        int count = jdbcTemplate.queryForObject(sqlForVerifiedDrivers, Integer.class);

        return count;
    }

    @Override
    public String sendDriverEmail(int driver_id) {
        String sqlForDriverEmail = "SELECT email FROM driver WHERE driver_id=?";
        Object[] args = new Object[]{driver_id};
        String driver_email;
        try {
            driver_email = jdbcTemplate.queryForObject(sqlForDriverEmail, args, String.class);
            return driver_email;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
        }
        return "";
    }
}