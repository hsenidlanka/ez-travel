package eztravel.model.reply.admin;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/26/17.
 */
public class DriverConfirmReplyModel extends ReplyModel {
    private boolean isDriverConfirmed;

    public boolean getIsDriverConfirmed() {
        return isDriverConfirmed;
    }

    public void setDriverConfirmed(boolean driverConfirmed) {
        isDriverConfirmed = driverConfirmed;
    }
}
