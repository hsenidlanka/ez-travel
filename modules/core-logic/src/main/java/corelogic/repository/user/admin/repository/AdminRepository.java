package corelogic.repository.user.admin.repository;

import java.util.Date;

/**
 * Created by Menuka on 12/20/17.
 */
public interface AdminRepository {
    boolean registerAdmin(String email,
                          String password,
                          String first_name,
                          String last_name,
                          Date birthday,
                          String gender,
                          String nic,
                          String contact_number,
                          String super_admin_email);

    boolean banAdmin(String super_admin_email, int admin_id);

    boolean confirmDriver(String admin_email, String driver_id);

    boolean isAdminAuthenticated(String admin_email, String admin_password);


}
