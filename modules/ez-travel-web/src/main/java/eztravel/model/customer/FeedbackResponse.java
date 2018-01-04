package eztravel.model.customer;

import eztravel.model.ServerResponse;

/**
 * Created by Vidushka.
 */
public class FeedbackResponse extends ServerResponse {
    private String isCustomerFeedbackSucceed;

    public String getIsCustomerFeedbackSucceed() {
        return isCustomerFeedbackSucceed;
    }

    public void setIsCustomerFeedbackSucceed(String isCustomerFeedbackSucceed) {
        this.isCustomerFeedbackSucceed = isCustomerFeedbackSucceed;
    }

}
