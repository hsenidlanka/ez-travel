package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver login requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class LoginReplyModel extends ReplyModel {


    private boolean authenticated;


    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
