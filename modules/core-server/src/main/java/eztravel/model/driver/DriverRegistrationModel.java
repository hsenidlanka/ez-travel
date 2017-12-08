package eztravel.model.driver;

/**
 * Created by hsenid on 12/5/17.
 */
public class DriverRegistrationModel {

  private String email;
  private String password;
  private String first_name;
  private String last_name;
  private String license_number;
  private String contact_number;
  private String birthday;
  private String gender;
//  private String confirmed_by;
  private String  nic;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public String getConfirmed_by() {
//        return confirmed_by;
//    }
//
//    public void setConfirmed_by(String confirmed_by) {
//        this.confirmed_by = confirmed_by;
//    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
