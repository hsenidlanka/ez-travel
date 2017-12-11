package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * Created by hsenid on 12/7/17.
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
