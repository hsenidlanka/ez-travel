package hsenid.model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsenid on 9/11/17.
 */
public class ReplyFromServer {
    private int httpStatusCode;
    private String Message;
    private String requestStatus;
    private List<JSONObject> data = new ArrayList<JSONObject>();

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

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }


    public void addData(JSONObject jsonObject) {
        data.add(jsonObject);
    }


}
