package eztravel.model.reply.feedback;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/18/17.
 */
public class AddDriverFeedbackReplyModel extends ReplyModel {

    private boolean isDriverFeedbackSucceed;

    public boolean isDriverFeedbackSucceed() {
        return isDriverFeedbackSucceed;
    }

    public void setDriverFeedbackSucceed(boolean driverFeedbackSucceed) {
        isDriverFeedbackSucceed = driverFeedbackSucceed;
    }
}
