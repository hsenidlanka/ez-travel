package corelogic.domain;

import java.sql.Date;

/**
 *  @version 1.0
 *  @auther Vidushka
 */

    public class UserDriver {
        private String email;

        private String first_name;
        private String last_name;
        private String license_number;
        private String contact_number;
        private Date birthday;
        private String gender;
        private int driver_status;
        private int confirmed_by;
        private String nic;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDriver_status() {
        return driver_status;
    }

    public void setDriver_status(int driver_status) {
        this.driver_status = driver_status;
    }

    public int getConfirmed_by() {
        return confirmed_by;
    }

    public void setConfirmed_by(int confirmed_by) {
        this.confirmed_by = confirmed_by;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}

