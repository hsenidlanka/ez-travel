package hsenid.repository.user.customer;

/**
 * Created by hsenid on 9/6/17.
 */
public interface CustomerRepository {
    boolean isCustomerAuthenticated(String email, String password);

    boolean registerCustomer(String email, String password, String first_name, String last_name);
}
