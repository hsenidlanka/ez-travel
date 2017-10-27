package hsenid.repository.user.customer.repository;

import hsenid.domain.user.customer.Customer;

import java.sql.Date;

/**
 * Created by Menuka on 9/6/17.
 */
public interface CustomerRepository {
    /**
     * @param email
     * @param password
     * @return
     */
    boolean isCustomerAuthenticated(String email, String password);

    boolean registerCustomer(String email, String password, String first_name, String last_name, Date birthday,
                             String contact_number, String nic, String gender);

    Customer sendCustomerDetails(String emai);
}
