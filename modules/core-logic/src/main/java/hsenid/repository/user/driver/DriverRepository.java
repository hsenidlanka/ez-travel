package hsenid.repository.user.driver;

import java.sql.Date;
import java.sql.Driver;
import java.util.List;

/**
 * Created by hsenid on 9/6/17.
 */
public interface DriverRepository {
    boolean isDriverAuthenticate(String username, String password);


   boolean registerDriver(String email, String password, String first_name, String last_name,
                                  String license_number, String contact_number,
                                  Date birthday, String gender, Integer driver_status, Integer confirmed_by,String nic);

   void sendDriverDelails();
}





