package eztravel.model.reply.admin;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/19/17.
 */
public class DriverFeedbackReviewReplyModel extends ReplyModel {

    boolean isFeedbackReviewSuccessful;

    public boolean getIsFeedbackReviewSuccessful() {
        return isFeedbackReviewSuccessful;
    }

    public void setFeedbackReviewSuccessful(boolean feedbackReviewSuccessful) {
        isFeedbackReviewSuccessful = feedbackReviewSuccessful;
    }
}
