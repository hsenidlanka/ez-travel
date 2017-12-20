package corelogic.repository.user.customer.implementation;

import corelogic.domain.user.customer.Customer;
import corelogic.repository.user.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation class for Customer related Database activities.
 * This use for autowire
 *
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class CustomerImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public CustomerImpl() {
    }

    /**
     * this method is used for authentication
     * It will check whether user is in the database and not banned.
     *
     * @param email    - customer's email
     * @param password - customer's password
     * @return - boolean
     */
    public boolean isCustomerAuthenticated(String email, String password) {

        String sql = "SELECT COUNT(*)FROM customer WHERE email = ? AND password = ? AND user_status = 1";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
        return count > 0;
    }

    /**
     * This method is responsible for adding new customer to database
     *
     * @param email          - customer's email
     * @param password       - customers password (sha256 hashed)
     * @param first_name     - customer's first name
     * @param last_name      - customer's last name
     * @param birthday       - customer's birthday
     * @param contact_number - customer's contact number
     * @param nic            customer's nic
     * @param gender         customer's gender
     * @return - boolean
     */
    public boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday, String contact_number, String nic, String gender) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql = "INSERT INTO customer (email, password, first_name, last_name, birthday, contact_number, nic, gender, user_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1);";
            Object[] args = new Object[]{email, password, first_name, last_name, birthday, contact_number, nic, gender};
            jdbcTemplate.update(sql, args);

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
     * This method is responsible for sending customer details as json.
     *
     * @param email - customer email which you need details
     * @return - Customer data type
     */
    public Customer sendCustomerDetails(String email) {

        String sqlForCustomerDetails = "SELECT customer_id, email, first_name, last_name, birthday, contact_number, nic, gender, user_status FROM customer WHERE email=?";
        Object[] args = new Object[]{email};

        return this.jdbcTemplate.queryForObject(sqlForCustomerDetails, args,

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
    }

    /**
     * This methode is responsible for upating the customer's password
     *
     * @param email           - customer's password
     * @param currentPassword - password which is being used currently
     * @param newPassword     - New password you need to add
     * @return - boolean
     */
    @Override
    public boolean updatePassword(String email, String currentPassword, String newPassword) {

        String sqlForUpdateCustomer = "UPDATE customer set password = ? where email = ? AND password = ?;";
        Object[] args = new Object[]{newPassword, email, currentPassword};
        boolean isPasswordUpdated = (jdbcTemplate.update(sqlForUpdateCustomer, args) == 1);

        return isPasswordUpdated;
    }


    /**
     * This method is responsible for updating customer's first name, last name and contact number
     *
     * @param email         - customer's email you want to details updated
     * @param firstName     - customer's new first name
     * @param lastName      - customer's new last name
     * @param contactNumber - customer's new contact number
     * @return - boolean
     */
    @Override
    public boolean updateContactDetails(String email, String firstName, String lastName, String contactNumber) {

        CompletableFuture.completedFuture(null);

        String sqlForUpdateCustomerContacts = "UPDATE customer set first_name = ?, last_name=? , contact_number=? where email = ?";
        Object[] args = new Object[]{firstName, lastName, contactNumber, email};
        boolean isContactsUpdated = (jdbcTemplate.update(sqlForUpdateCustomerContacts, args) == 1);

        return isContactsUpdated;
    }

    /**
     * This method is resposible for banning the customers.
     *
     * @param email - email of customer who is going to be banned
     * @return
     */
    @Override
    public boolean banCustomer(String email) {
        String sqlForBanningCustomer = "UPDATE customer set user_status = 0 where email = ?";
        ;
        Object[] args = new Object[]{email};
        boolean isBansucces = (jdbcTemplate.update(sqlForBanningCustomer, args) == 1);

        return isBansucces;
    }


    /**
     * This method is responsible for deleting customer account record from database.
     *
     * @param email - this is the unique data we use to identify customer
     * @return - boolean sa
     */
    @Override
    public boolean isCustomerDeleted(String email, String password) {

        String sqlForDeleteCustomer = "DELETE FROM customer WHERE email=? AND password = ?";
        Object[] args = new Object[]{email, password};
        boolean isDeleted = (jdbcTemplate.update(sqlForDeleteCustomer, args) == 1);

        return isDeleted;
    }

    /**
     * This method is responsible for checking whether customer in the database or not
     * used as error handling method
     *
     * @param email - customer's email
     * @return - boolean
     */
    @Override
    public boolean isCustomerInDatabase(String email) {

        String sqlForCustomerAvailability = "SELECT COUNT(*) FROM customer WHERE email = ?";
        Object[] args = new Object[]{email};
        int count = jdbcTemplate.queryForObject(sqlForCustomerAvailability, args, Integer.class);
        return count > 0;

    }

}
