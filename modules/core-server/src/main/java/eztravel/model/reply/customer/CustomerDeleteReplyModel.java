package eztravel.model.reply.customer;

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
