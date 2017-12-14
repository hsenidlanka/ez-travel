package eztravel.util;

/**
 * Created by Vidu on 13/12/2017.
 * <p>
 * Wrap Json objects which are send as response of a ajax request
 */
public class StringWrapper {
    private String response;

    public StringWrapper(String r) {
        this.response = r;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
