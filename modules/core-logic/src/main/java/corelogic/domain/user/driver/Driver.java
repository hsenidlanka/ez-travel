package corelogic.domain.user.driver;

import corelogic.domain.UserDriver;


import java.sql.Date;

/**
 * Created by Vidushika on 12/5/17.
 */
public class Driver extends UserDriver {

    private int driver_id;


    public Driver() {
    }

    public Driver(int id, String name, String email, Date created_date) {
        super();
    }


    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }
}
