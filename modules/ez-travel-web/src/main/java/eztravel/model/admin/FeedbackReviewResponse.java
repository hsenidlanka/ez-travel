package eztravel.model.admin;

import eztravel.model.ServerResponse;

/**
 * Created by Vidushka.
 */
public class FeedbackReviewResponse extends ServerResponse {
    private boolean feedbackReviewSuccessful;

    public boolean isFeedbackReviewSuccessful() {
        return feedbackReviewSuccessful;
    }

    public void setFeedbackReviewSuccessful(boolean feedbackReviewSuccessful) {
        this.feedbackReviewSuccessful = feedbackReviewSuccessful;
    }
}
