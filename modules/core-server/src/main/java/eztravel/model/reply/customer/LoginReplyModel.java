package eztravel.model.reply.customer;

/**
 * This model is to create json reply for login.
 * Purpose is to inform fron-end if login was successful or not.
 *
 * @version 1.0
 * @auther Vidushka
 */


public class LoginReplyModel extends ReplyModel {


    /*//    Http code which relate to login attempt
    private int httpStatusCode;
    //    Message regard to login attempt
    private String Message;
    private String requestStatus;*/
    private boolean authenticated;

//    public int getHttpStatusCode() {
//        return httpStatusCode;
//    }

    /*public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }*/

    /*public String getMessage() {
        return Message;
    }*/

    /*public void setMessage(String message) {
        Message = message;
    }*/

    /*public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }*/

    public boolean getIsAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
