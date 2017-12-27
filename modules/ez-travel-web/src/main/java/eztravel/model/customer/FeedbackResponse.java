package eztravel.model.customer;

/**
 * Created by Vidushka.
 */
public class FeedbackResponse {
    private String httpStatusCode;
    private String requestStatus;
    private String isCustomerFeedbackSucceed;
    private String message;

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getIsCustomerFeedbackSucceed() {
        return isCustomerFeedbackSucceed;
    }

    public void setIsCustomerFeedbackSucceed(String isCustomerFeedbackSucceed) {
        this.isCustomerFeedbackSucceed = isCustomerFeedbackSucceed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
