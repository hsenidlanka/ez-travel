package hsenid.domain.user.customer;

import hsenid.domain.User;

import java.sql.Date;

/**
 * Created by hsenid on 9/14/17.
 */
public class Customer extends User {
    private int customer_id;

    public Customer() {
    }

    public Customer(int id, String name, String email, Date created_date) {
        super();
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
