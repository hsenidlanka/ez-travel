package eztravel.util;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * Created by Vidushka on 20/09/17.
 */
public class ServerResponseErrorHandler extends DefaultResponseErrorHandler {

    private int statusCode;

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        statusCode = response.getRawStatusCode();
    }
}
