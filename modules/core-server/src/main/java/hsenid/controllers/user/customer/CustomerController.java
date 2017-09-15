package hsenid.controllers.user.customer;

import hsenid.enums.HttpStatusCodes;
import hsenid.model.ReplyFromServer;
import hsenid.repository.user.customer.CustomerImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReplyFromServer isUserCustomerOrNot(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ReplyFromServer replyFromServer = new ReplyFromServer();

        boolean loginStatus = customer.isCustomerAuthenticated(email, password);

        if (loginStatus) {
            replyFromServer.setMessage("Valid credentials");
            replyFromServer.setHttpStatusCode(HttpStatusCodes.OK.getValue());
            replyFromServer.setRequestStatus("Successful");
            JSONObject data = new JSONObject();
            data.put("requestStatus", true);
            replyFromServer.addData(data);
            return replyFromServer;
        }

        replyFromServer.setMessage("Invalid credentials");
        replyFromServer.setHttpStatusCode(HttpStatusCodes.UNAUTHORIZED.getValue());
        replyFromServer.setRequestStatus("Failed");
        JSONObject data = new JSONObject();
        data.put("requestStatus", false);
        replyFromServer.addData(data);

        return replyFromServer;
//        return isAuthenticatedUser;
    }

    @PostMapping("/customer/register")
    @ResponseBody
    public ReplyFromServer customerRegistration(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String contact_number = request.getParameter("contact_number");
        String nic = request.getParameter("nic");
        String gender = request.getParameter("gender");
        String birthdayString = request.getParameter("birthday"); //Should be like "2015-03-31"

        ReplyFromServer reply = new ReplyFromServer();

        Date birthday = Date.valueOf(birthdayString);//converting string into sql date
        boolean registrationStatus = customer.registerCustomer(email, password, first_name, last_name, birthday, contact_number, nic, gender);
        if (registrationStatus) {
            reply.setHttpStatusCode(HttpStatusCodes.CREATED.getValue());
            reply.setMessage("User creation successful");
            reply.setRequestStatus("success");
            JSONObject userCreated = new JSONObject();
            userCreated.put("isUserCreated", true);
            reply.addData(userCreated);
            return reply;
        }
//        RequestStatus requestStatus = new RequestStatus();
//        requestStatus.setRequestStatus(registrationStatus);

        reply.setHttpStatusCode(HttpStatusCodes.INTERNAL_SERVER_ERROR.getValue());
        reply.setRequestStatus("failed");
        reply.setMessage("User creation failed");
        JSONObject userCreated = new JSONObject();
        userCreated.put("isUserCreated", false);
        reply.addData(userCreated);

        return reply;

    }

    @PostMapping("/customer/info")
    @ResponseBody
    public void sendCustomerData(HttpServletRequest request) {
        String email = request.getParameter("email");


    }

}
