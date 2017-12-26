package eztravel.model.admin;

/**
 * Created by Menuka on 12/26/17.
 */
public class DriverConfirmRequestModel {
    private String admin_email;
    private String driver_id;

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }
}
