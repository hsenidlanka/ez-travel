package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply customer account delete requests
 *
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerDeleteReplyModel extends ReplyModel {

    private boolean userDeletion;

    public boolean getUserDeletion() {
        return userDeletion;
    }

    public void setUserDeletion(boolean userDeletion) {
        this.userDeletion = userDeletion;
    }
}
