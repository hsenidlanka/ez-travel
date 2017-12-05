package hsenid.model.customer;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;


/**
 * Created by Vidushka on 12/09/17.
 */

public class Customer {

    @NotEmpty(message = "Empty email.")
    @Email(message = "Incorrect email.")
    private String email;

    @Size(min = 8,message = "Incorrect password(should be 8 characters or more).")
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
