package eztravel.model.admin;

/**
 * Created by Menuka on 1/8/18.
 */
public class AdminLoginRequestModel {
    private String admin_email;
    private String admin_password;

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
}
