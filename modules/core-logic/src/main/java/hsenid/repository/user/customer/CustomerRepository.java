package hsenid.repository.user.customer;

/**
 * Created by hsenid on 9/6/17.
 */
public interface CustomerRepository {
    boolean isCustomerAuthenticated(String email, String password);
}
