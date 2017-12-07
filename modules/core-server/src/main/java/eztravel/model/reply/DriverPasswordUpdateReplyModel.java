package eztravel.model.reply;



/**
 * Created by hsenid on 12/6/17.
 */
public class DriverPasswordUpdateReplyModel  {
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

    public boolean isPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setPasswordUpdated(boolean passwordUpdated) {
        isPasswordUpdated = passwordUpdated;
    }
}
