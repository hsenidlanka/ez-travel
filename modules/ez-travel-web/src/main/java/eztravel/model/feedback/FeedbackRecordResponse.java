package eztravel.model.feedback;

import eztravel.model.ServerResponse;

import java.util.List;

/**
 * Created by Vidushka.
 */
public class FeedbackRecordResponse extends ServerResponse {
    private List<FeedbackRecord> feedbackRecords;

    public List<FeedbackRecord> getFeedbackRecords() {
        return feedbackRecords;
    }

    public void setFeedbackRecords(List<FeedbackRecord> feedbackRecords) {
        this.feedbackRecords = feedbackRecords;
    }
}
