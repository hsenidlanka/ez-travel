package eztravel.model.reply;

/**
 * Created by Vidushika on 12/6/17.
 */
public class DriverDeleteReplyModel {
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

    public boolean isUserDeletion() {
        return userDeletion;
    }

    public void setUserDeletion(boolean userDeletion) {
        this.userDeletion = userDeletion;
    }
}
