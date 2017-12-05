package hsenid.model.customer;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Vidushka on 02/10/17.
 */
public class SignUp {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]{2,30}$")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]{2,30}$")
    private String lastName;
    @Pattern(regexp = "^07[125678][0-9]{7}$")
    private String mobileNumber;
    @Pattern(regexp = "^[0-9]{9}[V]$")
    private String nic;
    @NotEmpty
    @Email
    private String email;
    @Size(min = 6)
    private String password;

    private String rePassword;
    private String year;
    private String month;
    private String day;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
