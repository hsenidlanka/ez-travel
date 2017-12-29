package eztravel.model.driver;

/**
 * Created by Vidushka.
 */
public class DriverCountResponse {
    private String httpStatusCode;
    private String requestStatus;
    private int countVerifiedDrivers;
    private String messsage;

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

    public int getCountVerifiedDrivers() {
        return countVerifiedDrivers;
    }

    public void setCountVerifiedDrivers(int countVerifiedDrivers) {
        this.countVerifiedDrivers = countVerifiedDrivers;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
