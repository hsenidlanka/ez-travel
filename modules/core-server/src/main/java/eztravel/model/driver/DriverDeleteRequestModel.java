package eztravel.model.driver;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class DriverDeleteRequestModel {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
