package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * Model class to send result of deletion of customer
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
