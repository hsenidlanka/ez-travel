package eztravel.model.reply.driver;


import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver password update requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class DriverPasswordUpdateReplyModel extends ReplyModel {

    private boolean isPasswordUpdated;

    public boolean isPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setPasswordUpdated(boolean passwordUpdated) {
        isPasswordUpdated = passwordUpdated;
    }
}
