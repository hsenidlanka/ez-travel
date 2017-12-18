package corelogic.repository.feedback.Repository;

/**
 * Created by Menuka on 12/18/17.
 */
public interface FeedbackRepository {
    boolean addCustomerFeedback(String description, String customer_email, int driver_id, int hire_id);
}
