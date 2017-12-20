package corelogic.domain.user.admin;

import corelogic.domain.User;

/**
 * Created by Menuka on 12/20/17.
 */
public class Admin extends User {
    private int admin_id;
    private int confirmed_by_admin;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getConfirmed_by_admin() {
        return confirmed_by_admin;
    }

    public void setConfirmed_by_admin(int confirmed_by_admin) {
        this.confirmed_by_admin = confirmed_by_admin;
    }
}
