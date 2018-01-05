package corelogic.repository.user.driver.Repository;


import corelogic.domain.user.driver.Driver;

import java.sql.Date;

/**
 * Repository class for Driver related Database activities.
 *
 * @version 1.0
 * @auther Vidushka
 */
public interface DriverRepository {
    boolean isDriverAuthenticated(String email, String password);

    boolean registerDriver(String email, String password, String first_name, String last_name, String license_number,
                           String contact_number, Date birthday, String gender, String nic);


    Driver sendDriverDetails(String email);

    boolean banDriver(String email);

    boolean isDriverDeleted(String email, String password);

    boolean updatePassword(String email, String currentPassword, String newPassword);

    boolean updateContactDetails(String email, String firstName, String lastName, String contactNumber);

    boolean isDriverInDatabase(String email);

    String sendDriverName(int driver_id);

    int countOfVerifiedDrivers();

    String sendDriverEmail(int driver_id);
}
