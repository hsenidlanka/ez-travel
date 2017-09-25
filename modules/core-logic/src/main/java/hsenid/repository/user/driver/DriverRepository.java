package hsenid.repository.user.driver;

/**
 * Created by hsenid on 9/6/17.
 */
public interface DriverRepository {
    boolean isDriverAuthenticate(String username, String password);


    /**
     *
     * @param driver
     * @return
     */
   boolean registerDriver(hsenid.repository.user.driver.domain.Driver driver);

   void sendDriverDelails();
}





