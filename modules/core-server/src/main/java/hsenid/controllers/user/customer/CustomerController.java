package hsenid.controllers.user.customer;

import hsenid.enums.HttpStatusCodes;
import hsenid.model.ReplyFromServer;
import hsenid.model.customer.LoginModel;
import hsenid.model.reply.LoginReplyModel;
import hsenid.repository.user.customer.implementation.CustomerImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Menuka on 9/6/17.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerImpl customer;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginReplyModel> isUserCustomerOrNot(@RequestBody LoginModel loginModel) {

        System.out.println("email =>" + loginModel.getEmail());
        LoginReplyModel loginReplyModel = new LoginReplyModel();

        boolean loginStatus = customer.isCustomerAuthenticated(loginModel.getEmail(), loginModel.getPassword());

        if (loginStatus) {
            loginReplyModel.setMessage("Valid credentials");
            loginReplyModel.setHttpStatusCode(HttpStatusCodes.OK.getValue());
            loginReplyModel.setRequestStatus("Successful");
            loginReplyModel.setAuthenticated(true);
            return ResponseEntity.ok(loginReplyModel);
        }

        loginReplyModel.setMessage("Invalid credentials");
        loginReplyModel.setHttpStatusCode(HttpStatusCodes.UNAUTHORIZED.getValue());
        loginReplyModel.setRequestStatus("Failed");
        loginReplyModel.setAuthenticated(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginReplyModel);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ReplyFromServer> customerRegistration(HttpServletRequest request) {

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
            return ResponseEntity.ok(reply);
        }
//        RequestStatus requestStatus = new RequestStatus();
//        requestStatus.setRequestStatus(registrationStatus);

        reply.setHttpStatusCode(HttpStatusCodes.INTERNAL_SERVER_ERROR.getValue());
        reply.setRequestStatus("failed");
        reply.setMessage("User creation failed");
        JSONObject userCreated = new JSONObject();
        userCreated.put("isUserCreated", false);
        reply.addData(userCreated);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reply);

    }

    @PostMapping("/customer/info")
    @ResponseBody
    public void sendCustomerData(HttpServletRequest request) {
        String email = request.getParameter("email");


    }

}
