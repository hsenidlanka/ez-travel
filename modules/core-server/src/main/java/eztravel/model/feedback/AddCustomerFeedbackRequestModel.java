package eztravel.model.feedback;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class AddCustomerFeedbackRequestModel {
    private String description;
    private String customer_email ;
    private int driver_id;
    private int hire_id;

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getHire_id() {
        return hire_id;
    }

    public void setHire_id(int hire_id) {
        this.hire_id = hire_id;
    }

}
