package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver contact update requests
 * @version 1.0
 * @auther Vidushka
 */
public class DriverContactUpdateReplyModel extends ReplyModel {

private boolean isContactUpdated;

    public boolean isContactUpdated() {
        return isContactUpdated;
    }

    public void setContactUpdated(boolean contactUpdated) {
        isContactUpdated = contactUpdated;
    }
}
