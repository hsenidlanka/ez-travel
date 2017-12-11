package corelogic.repository.user.customer.repository;

import corelogic.domain.user.customer.Customer;

import java.sql.Date;


/**
 * @version 1.0
 * @auther Vidushka
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

    Customer sendCustomerDetails(String email);

    boolean updatePassword(String email, String currentPassword, String newPassword);

    boolean updateContactDetails(String email, String firstName, String lastName, String contactNumber);

    boolean banCustomer(String email);

    boolean isCustomerDeleted(String email, String password);

    boolean isCustomerInDatabase(String email);

}
