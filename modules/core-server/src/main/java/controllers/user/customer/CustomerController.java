package controllers.user.customer;

import hsenid.domain.user.customer.IsAuthenticatedUser;
import hsenid.domain.user.customer.RequestStatus;
import hsenid.repository.user.customer.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by hsenid on 9/6/17.
 */
@RestController
public class CustomerController {
    @Autowired
    CustomerImpl customer;

    @PostMapping("/customer/login")
    @ResponseBody
    public IsAuthenticatedUser isUserCustomerOrNot(HttpServletRequest request){
        IsAuthenticatedUser isAuthenticatedUser = new IsAuthenticatedUser();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        isAuthenticatedUser.setUser(customer.isCustomerAuthenticated(email, password));

        return isAuthenticatedUser;
    }

    @PostMapping("/customer/register")
    @ResponseBody
    public RequestStatus customerRegistration(HttpServletRequest request) {

        String email = request.getParameter("email");
        ;
        String password = request.getParameter("password");
        ;
        String first_name = request.getParameter("first_name");
        ;
        String last_name = request.getParameter("last_name");
        ;
        String contact_number = request.getParameter("contact_number");
        ;
        String nic = request.getParameter("nic");
        ;
        String gender = request.getParameter("gender");
        String birthdayString = request.getParameter("birthday"); //Should be like "2015-03-31"


        Date birthday = Date.valueOf(birthdayString);//converting string into sql date
        boolean registrationStatus = customer.registerCustomer(email, password, first_name, last_name, birthday, contact_number, nic, gender);

        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setRequestStatus(registrationStatus);

        return requestStatus;


    }



}
