package hsenid.domain.user.driver;

/**
 * Created by hsenid on 9/8/17.
 */
public class IsAuthenticatedDriver {
    private boolean isDriver;

    public IsAuthenticatedDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }
}


