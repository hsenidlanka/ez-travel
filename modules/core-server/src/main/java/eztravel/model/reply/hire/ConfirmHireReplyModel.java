package eztravel.model.reply.hire;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply confirm hire requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class ConfirmHireReplyModel extends ReplyModel {

    private boolean hirePlaceConfirmed;

    public boolean getHirePlaceConfirmed() {
        return hirePlaceConfirmed;
    }

    public void setHirePlaceConfirmed(boolean hirePlaceConfirmed) {
        this.hirePlaceConfirmed = hirePlaceConfirmed;
    }
}
