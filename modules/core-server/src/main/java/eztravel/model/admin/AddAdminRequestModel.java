package eztravel.model.admin;

import java.sql.Date;

/**
 * Created by Menuka on 12/20/17.
 */
public class AddAdminRequestModel {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private Date birthday;
    private String contact_number;
    private String nic;
    private String gender;
    private String super_admin_email;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSuper_admin_email() {
        return super_admin_email;
    }

    public void setSuper_admin_email(String super_admin_email) {
        this.super_admin_email = super_admin_email;
    }
}
