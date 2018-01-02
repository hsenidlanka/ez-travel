package eztravel.model.reply.feedback;

import corelogic.domain.feedback.FeedbackRecord;
import eztravel.model.reply.ReplyModel;

import java.util.List;

/**
 * Created by Menuka on 12/27/17.
 */
public class FeedbackRecordsReplyModel extends ReplyModel {
    private List<FeedbackRecord> feedbackRecords;

    public List<FeedbackRecord> getFeedbackRecords() {
        return feedbackRecords;
    }

    public void setFeedbackRecords(List<FeedbackRecord> feedbackRecords) {
        this.feedbackRecords = feedbackRecords;
    }
}
