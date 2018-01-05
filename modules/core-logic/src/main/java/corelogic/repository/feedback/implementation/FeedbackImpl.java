package corelogic.repository.feedback.implementation;

import corelogic.domain.feedback.FeedbackRecord;
import corelogic.repository.feedback.Repository.FeedbackRepository;
import corelogic.repository.user.customer.implementation.CustomerImpl;
import corelogic.repository.user.driver.Implementation.DriverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 12/18/17.
 */

@Repository
public class FeedbackImpl implements FeedbackRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DriverImpl driverImpl;

    @Autowired
    private CustomerImpl customerImpl;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public boolean addCustomerFeedback(String description, String customer_email, int driver_id, int hire_id) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            String sqlForGetCustomerId = "SELECT customer_id FROM customer WHERE email = ?";
            Object[] args = new Object[]{customer_email};

            String customer_id = jdbcTemplate.queryForObject(sqlForGetCustomerId, args, String.class);

            String sqlForAddCustomerFeedback =
                    "INSERT INTO feedback (description, customer_id, driver_id, hire_id, feedback_status) VALUES (?, ?, ?, ?, 0)";

            Object[] arge = new Object[]{description, Integer.parseInt(customer_id), driver_id, hire_id};
            jdbcTemplate.update(sqlForAddCustomerFeedback, arge);

            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public boolean addDriverFeedback(String description, String driver_email, int customer_id, int hire_id) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            String sqlForGetDriverId = "SELECT driver_id FROM driver WHERE email = ?";
            Object[] args = new Object[]{driver_email};

            String driver_id = jdbcTemplate.queryForObject(sqlForGetDriverId, args, String.class);

            String sqlForAddDriverFeedback =
                    "INSERT INTO driver_feedback (description, customer_id, driver_id, hire_id, feedback_status) VALUES (?, ?, ?, ?, 0)";

            Object[] arge = new Object[]{description, customer_id, Integer.parseInt(driver_id), hire_id};
            jdbcTemplate.update(sqlForAddDriverFeedback, arge);

            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public boolean customerFeedbackReviewed(int feedback_id, String admin_email) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{admin_email};

            String admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, String.class);

            String sqlForUpdateCustomerFeedback = "UPDATE feedback set feedback_status = ?,  confirmed_by = ? where feedback_id = ?";
            Object[] argsForCustomerFeedbackUpdate = new Object[]{1, Integer.parseInt(admin_id), feedback_id};

            boolean isUpdated = jdbcTemplate.update(sqlForUpdateCustomerFeedback, argsForCustomerFeedbackUpdate) == 1;

            if (!isUpdated) {
                throw new Exception("No feedback in that ID in database!!!");
            }

            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);

        }

        return false;
    }

    @Override
    public boolean driverFeedbackReviewed(int feedback_id, String admin_email) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{admin_email};

            String admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, String.class);

            String sqlForUpdateDriverFeedback = "UPDATE driver_feedback set feedback_status = ?,  confirmed_by = ? where feedback_id = ?";
            Object[] argsForDriverFeedbackUpdate = new Object[]{1, Integer.parseInt(admin_id), feedback_id};

            boolean isUpdated = jdbcTemplate.update(sqlForUpdateDriverFeedback, argsForDriverFeedbackUpdate) == 1;

            if (!isUpdated) {
                throw new Exception("No feedback in that ID in database!!!");
            }

            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public List<FeedbackRecord> sendFeedbackRecords(String admin_email) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        List<FeedbackRecord> feedbackRecords = new ArrayList<>();

        try {
            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{admin_email};
            Integer admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, Integer.class);

            if (admin_id == 0) {
                throw new Exception("No admin found in that email");
            }

            String sqlForCustomerHireRecords = "SELECT * FROM feedback WHERE feedback_status=0";

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlForCustomerHireRecords);

            for (Map row : rows) {
                FeedbackRecord record = new FeedbackRecord();

                int driver_id = (Integer) row.get("driver_id");
                int customer_id = (Integer) row.get("customer_id");

                record.setFeedback_id((Integer) row.get("feedback_id"));
                record.setDescription((String) row.get("description"));
                record.setCustomer_id(customer_id);
                record.setDriver_id(driver_id);
                record.setHire_id((Integer) row.get("hire_id"));
                record.setDriver_email(driverImpl.sendDriverEmail(driver_id));
                record.setCustomer_email(customerImpl.sendCustomerEmail(customer_id));
                feedbackRecords.add(record);
            }

            transactionManager.commit(status);

            return feedbackRecords;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return feedbackRecords;
    }
}
