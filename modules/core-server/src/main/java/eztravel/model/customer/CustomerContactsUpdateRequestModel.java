package eztravel.model.customer;

/**
 * Model used to request Customer contact details update request
 *
 * @version 1.0
 * @auther Vidushka
 */

public class CustomerContactsUpdateRequestModel {

    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
