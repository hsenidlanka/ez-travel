package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver account delete requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class DriverDeleteReplyModel extends ReplyModel {

    private boolean userDeletion;

      public boolean isUserDeletion() {
        return userDeletion;
    }

    public void setUserDeletion(boolean userDeletion) {
        this.userDeletion = userDeletion;
    }
}
