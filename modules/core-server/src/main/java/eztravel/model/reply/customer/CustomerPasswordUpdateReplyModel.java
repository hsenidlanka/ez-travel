package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply customer password update requests
 *
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerPasswordUpdateReplyModel extends ReplyModel {

    private boolean isPasswordUpdated;

    public boolean getIsPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setIsPasswordUpdated(boolean passwordUpdated) {
        isPasswordUpdated = passwordUpdated;
    }
}
