package controllers.user.customer;

import hsenid.domain.user.customer.IsAuthenticatedUser;
import hsenid.repository.user.customer.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hsenid on 9/6/17.
 */
@RestController
public class CustomerController {
    @Autowired
    CustomerImpl customer;

    @PostMapping("/eztravel/customer")
    @ResponseBody
    public IsAuthenticatedUser isUserCustomerOrNot(HttpServletRequest request){
        IsAuthenticatedUser isAuthenticatedUser = new IsAuthenticatedUser();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        isAuthenticatedUser.setUser(customer.isCustomerAuthenticated(email, password));

        return isAuthenticatedUser;
    }
}
