package corelogic.domain.user.customer;

import corelogic.domain.User;

import java.sql.Date;

/**
 * This is the domain class for Customer extends from User.
 * This is used for json object serialize.
 *
 * @version 1.0
 * @auther Vidushka
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
