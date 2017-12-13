package com.example.hsenid.taxiapp;

/**
 * Created by hsenid on 12/12/17.
 */

public class RegisterReplyModal {

    private String httpStatusCode;
    private String requestStatus;
    private String userCreation;
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

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "LoginReplyModal{" +
                "httpStatusCode='" + httpStatusCode + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                ", userCreation='" + userCreation + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
