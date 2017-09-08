package hsenid.domain.user.customer;

/**
 * Created by hsenid on 9/6/17.
 */
public class IsAuthenticatedUser {
    private boolean requestStatus;

    public boolean isUser() {
        return requestStatus;
    }

    public void setUser(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
}
