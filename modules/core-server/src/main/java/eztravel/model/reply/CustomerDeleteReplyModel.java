package eztravel.model.reply;

/**
 * Model class to send result of deletion of customer
 *
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerDeleteReplyModel {
    private int httpStatusCode;
    private String Message;
    private String requestStatus;
    private boolean userDeletion;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean getUserDeletion() {
        return userDeletion;
    }

    public void setUserDeletion(boolean userDeletion) {
        this.userDeletion = userDeletion;
    }
}
