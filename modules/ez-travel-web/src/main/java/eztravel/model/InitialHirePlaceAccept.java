package eztravel.model;

/**
 * Created by Vidushka.
 */
public class InitialHirePlaceAccept {
    private int httpStatusCode;
    private String Message;
    private String requestStatus;
    private boolean isInitialHirePlaceSuccess;
    private InitialHireModel hireData;

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

    public boolean isInitialHirePlaceSuccess() {
        return isInitialHirePlaceSuccess;
    }

    public void setInitialHirePlaceSuccess(boolean initialHirePlaceSuccess) {
        isInitialHirePlaceSuccess = initialHirePlaceSuccess;
    }

    public InitialHireModel getHireData() {
        return hireData;
    }

    public void setHireData(InitialHireModel hireData) {
        this.hireData = hireData;
    }
}
