package eztravel.model.reply.hire;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver location update requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class DriverLocationUpdateReplyModel extends ReplyModel{

    boolean isLocationUpdated;

    public boolean getIsLocationUpdated() {
        return isLocationUpdated;
    }

    public void setLocationUpdated(boolean locationUpdated) {
        isLocationUpdated = locationUpdated;
    }
}
