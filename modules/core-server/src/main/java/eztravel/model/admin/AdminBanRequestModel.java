package eztravel.model.admin;

/**
 * Created by Menuka on 12/20/17.
 */
public class AdminBanRequestModel {
    private String super_admin_email;
    private int admin_id;

    public String getSuper_admin_email() {
        return super_admin_email;
    }

    public void setSuper_admin_email(String super_admin_email) {
        this.super_admin_email = super_admin_email;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
