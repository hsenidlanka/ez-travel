package controllers.user.customer.Driver;

import hsenid.domain.user.customer.IsAuthenticatedUser;
import hsenid.repository.user.driver.DriverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hsenid on 9/6/17.
 */
@RestController
public class DriverController {
    @Autowired
    DriverImpl driver;
    @PostMapping("/eztravel/driver")
    @ResponseBody
    public IsAuthenticatedUser isAuthenticatedDriverOrNot(HttpServletRequest httpServletRequest)
    {
        IsAuthenticatedUser isAuthenticatedUser=new IsAuthenticatedUser();
        String email=httpServletRequest.getParameter("email");
        String password=httpServletRequest.getParameter("password");
        isAuthenticatedUser.setUser(driver.isDriverAuthenticate(email,password));
        return isAuthenticatedUser;
    }
}
