package eztravel.model.admin;

/**
 * Created by Vidushka.
 */
public class AdminSignUpResponse {
    private int httpStatusCode;
    private String requestStatus;
    private boolean isAdminAddSuccess;
    private String message;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean isAdminAddSuccess() {
        return isAdminAddSuccess;
    }

    public void setAdminAddSuccess(boolean adminAddSuccess) {
        isAdminAddSuccess = adminAddSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
