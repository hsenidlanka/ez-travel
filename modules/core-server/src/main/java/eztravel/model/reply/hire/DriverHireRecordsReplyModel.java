package eztravel.model.reply.hire;

import corelogic.domain.hire.DriverHireRecord;
import eztravel.model.reply.ReplyModel;

import java.util.List;

/**
 * Created by Menuka on 1/3/18.
 */
public class DriverHireRecordsReplyModel extends ReplyModel {

    private List<DriverHireRecord> feedbackRecords;

    public List<DriverHireRecord> getFeedbackRecords() {
        return feedbackRecords;
    }

    public void setFeedbackRecords(List<DriverHireRecord> feedbackRecords) {
        this.feedbackRecords = feedbackRecords;
    }
}
