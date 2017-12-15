package eztravel.model.customer;

/**
 * This model used for Login request
 *
 * @version 1.0
 * @auther Vidushka
 */
public class LoginModel {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
