package corelogic.repository.user.driver.Repository;



import corelogic.domain.user.driver.Driver;

import java.sql.Date;

/**
 * Created by hsenid on 12/5/17.
 */
public interface DriverRepository {
    boolean isDriverAuthenticated(String email,String password);
    boolean registerDriver(String email, String password, String first_name, String last_name, String license_number,
                           String contact_number, Date birthday, String gender, String confirmed_by, String nic);


    Driver sendDriverDetails(String email);
    boolean isDriverDeleted(String email);

    boolean updatePassword(String email, String currentPassword, String newPassword);
}
