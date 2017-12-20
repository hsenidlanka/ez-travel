package corelogic.domain.user.driver;

/**
 * Created by Menuka on 12/20/17.
 */
public class DriverName {
    private String firstName;
    private String lastName;

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

    @Override
    public String toString() {
        StringBuilder fullName = new StringBuilder();
        return (fullName.append(firstName).append(" ").append(lastName).toString());
    }

}
