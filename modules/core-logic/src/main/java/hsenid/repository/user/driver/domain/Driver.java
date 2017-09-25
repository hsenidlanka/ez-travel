package hsenid.repository.user.driver.domain;

import java.sql.Date;

public class Driver {
    private final String email;
    private final String password;
    private final String first_name;
    private final String last_name;
    private final String license_number;
    private final String contact_number;
    private final Date birthday;
    private final String gender;
    private final int driver_status;
    private final int confirmed_by;
    private final String nic;

    public Driver(String email, String password, String first_name, String last_name, String license_number,
                  String contact_number, Date birthday, String gender, Integer driver_status, Integer confirmed_by,
                  String nic) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.license_number = license_number;
        this.contact_number = contact_number;
        this.birthday = birthday;
        this.gender = gender;
        this.driver_status = driver_status;
        this.confirmed_by = confirmed_by;
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public String getContact_number() {
        return contact_number;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public Integer getDriver_status() {
        return driver_status;
    }

    public Integer getConfirmed_by() {
        return confirmed_by;
    }

    public String getNic() {
        return nic;
    }

    /*@Override
    public String toString() {
        return "Driver{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", license_number='" + license_number + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", driver_status=" + driver_status +
                ", confirmed_by=" + confirmed_by +
                ", nic='" + nic + '\'' +
                '}';
    }*/
}
