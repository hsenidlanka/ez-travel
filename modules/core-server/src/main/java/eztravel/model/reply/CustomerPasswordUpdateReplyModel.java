package eztravel.model.reply;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerPasswordUpdateReplyModel {
    private int httpStatusCode;
    private String Message;
    private String requestStatus;
    private boolean isPasswordUpdated;

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

    public boolean getIsPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setIsPasswordUpdated(boolean passwordUpdated) {
        isPasswordUpdated = passwordUpdated;
    }
}
