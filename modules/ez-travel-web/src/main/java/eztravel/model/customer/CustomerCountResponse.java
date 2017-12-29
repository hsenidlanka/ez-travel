package eztravel.model.customer;

/**
 * Created by Vidushka.
 */
public class CustomerCountResponse {
    private String httpStatusCode;
    private String requestStatus;
    private int countOfUnbanCustomer;
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

    public int getCountOfUnbanCustomer() {
        return countOfUnbanCustomer;
    }

    public void setCountOfUnbanCustomer(int countOfUnbanCustomer) {
        this.countOfUnbanCustomer = countOfUnbanCustomer;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
