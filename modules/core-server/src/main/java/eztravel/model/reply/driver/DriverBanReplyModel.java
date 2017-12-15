package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver ban request
 *
 * @version 1.0
 * @auther Vidushka
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
