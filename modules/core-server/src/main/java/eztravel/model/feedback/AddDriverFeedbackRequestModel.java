package eztravel.model.feedback;

/**
 * Created by Menuka on 12/18/17.
 */
public class AddDriverFeedbackRequestModel {

    private String description;
    private String driver_email ;
    private int customer_id;
    private int hire_id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriver_email() {
        return driver_email;
    }

    public void setDriver_email(String driver_email) {
        this.driver_email = driver_email;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getHire_id() {
        return hire_id;
    }

    public void setHire_id(int hire_id) {
        this.hire_id = hire_id;
    }
}
