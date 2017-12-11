package eztravel.model.reply;

/**
 * This is the base modal for all reply models
 *
 * @version 1.0
 * @auther Vidushka
 *
 */

public class ReplyModel {

    private int httpStatusCode;
    private String Message;
    private String requestStatus;

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
}
