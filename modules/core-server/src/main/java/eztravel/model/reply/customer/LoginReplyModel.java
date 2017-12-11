package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * This model is to create json reply for login.
 * Purpose is to inform fron-end if login was successful or not.
 *
 * @version 1.0
 * @auther Vidushka
 */


public class LoginReplyModel extends ReplyModel {

    private boolean authenticated;

    public boolean getIsAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
