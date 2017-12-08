package hsenid.model;

/**
 * Created by Vidushka on 13/09/17.
 */
public class LoginMapper {
    private GeoCoordinate httpStatusCode;
    private GeoCoordinate requestStatus;
    private GeoCoordinate authenticated;
    private GeoCoordinate message;

    public GeoCoordinate getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(GeoCoordinate httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public GeoCoordinate getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(GeoCoordinate requestStatus) {
        this.requestStatus = requestStatus;
    }

    public GeoCoordinate getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(GeoCoordinate authenticated) {
        this.authenticated = authenticated;
    }

    public GeoCoordinate getMessage() {
        return message;
    }

    public void setMessage(GeoCoordinate message) {
        this.message = message;
    }

}

