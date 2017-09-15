package hsenid.domain.user.customer;

import hsenid.domain.User;

/**
 * Created by hsenid on 9/14/17.
 */
public class Customer extends User {
    private int customer_id;

    public Customer() {
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
