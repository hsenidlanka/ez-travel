package eztravel.model.reply.feedback;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/18/17.
 */
public class AddCustomerFeedbackReplyModel extends ReplyModel{
    boolean isCustomerFeedbackSucceed;

    public boolean getIsCustomerFeedbackSucceed() {
        return isCustomerFeedbackSucceed;
    }

    public void setCustomerFeedbackSucceed(boolean customerFeedbackSucceed) {
        isCustomerFeedbackSucceed = customerFeedbackSucceed;
    }
}
