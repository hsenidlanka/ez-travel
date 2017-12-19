package eztravel.model.admin;

/**
 * Created by Menuka on 12/18/17.
 */
public class CustomerFeedbackReviewRequestModel {

    private int feedback_id;
    private String admin_email;

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }
}
