package hsenid.repository.user.customer;

import java.sql.Date;

/**
 * Created by hsenid on 9/6/17.
 */
public interface CustomerRepository {
    boolean isCustomerAuthenticated(String email, String password);

    boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday, String contact_number, String nic, String gender);
}
