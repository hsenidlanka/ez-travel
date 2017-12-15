package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply customer contact update requests
 * @version 1.0
 * @auther Vidushka
 */

public class CustomerContactUpdateReplyModel extends ReplyModel {

    private boolean isContactUpdated;

    public boolean getIsContactUpdated() {
        return isContactUpdated;
    }

    public void setContactUpdated(boolean contactUpdated) {
        isContactUpdated = contactUpdated;
    }


}
