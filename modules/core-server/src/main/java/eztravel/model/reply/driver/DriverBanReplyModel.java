package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Vidushika on 12/7/17.
 */
public class DriverBanReplyModel extends ReplyModel {
    private boolean isDriverBanned;

    public boolean isDriverBanned() {
        return isDriverBanned;
    }

    public void setDriverBanned(boolean driverBanned) {
        isDriverBanned = driverBanned;
    }
}
