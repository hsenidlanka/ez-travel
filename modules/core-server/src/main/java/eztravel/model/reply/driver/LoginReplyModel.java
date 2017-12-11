package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * Created by hsenid on 12/5/17.
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
