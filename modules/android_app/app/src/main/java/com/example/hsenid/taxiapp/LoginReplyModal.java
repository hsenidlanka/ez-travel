package com.example.hsenid.taxiapp;

/**
 * Created by hsenid on 12/12/17.
 */

public class LoginReplyModal {
    private String httpStatusCode;
    private String requestStatus;
    private String isAuthentcated;
    private String message;

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

    public String getIsAuthentcated() {
        return isAuthentcated;
    }

    public void setIsAuthentcated(String isAuthentcated) {
        this.isAuthentcated = isAuthentcated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
