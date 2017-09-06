package hsenid.repository.user.driver;

/**
 * Created by hsenid on 9/6/17.
 */
public interface DriverRepository {
    boolean isDriverAuthenticate(String username, String password);
}
