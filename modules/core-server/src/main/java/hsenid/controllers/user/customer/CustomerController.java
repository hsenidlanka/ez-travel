package hsenid.controllers.user.customer;

import hsenid.domain.user.customer.Customer;
import hsenid.model.customer.CustomerDetailRequestModel;
import hsenid.model.customer.CustomerRegistrationModel;
import hsenid.model.customer.LoginModel;
import hsenid.model.reply.CustomerRegistrationReplyModel;
import hsenid.model.reply.LoginReplyModel;
import hsenid.repository.user.customer.implementation.CustomerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    CustomerImpl customerImpl;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginReplyModel> isUserCustomerOrNot(@RequestBody LoginModel loginModel) {
        logger.error("Logger test ======+++++");
//        System.out.println("email =>" + loginModel.getEmail());
        LoginReplyModel loginReplyModel = new LoginReplyModel();

        boolean loginStatus = customerImpl.isCustomerAuthenticated(loginModel.getEmail(), loginModel.getPassword());

        if (loginStatus) {
            logger.info("Test info");

            loginReplyModel.setMessage("Valid credentials");
            loginReplyModel.setHttpStatusCode(HttpStatus.OK.value());
            loginReplyModel.setRequestStatus("Successful");
            loginReplyModel.setAuthenticated(true);
            return ResponseEntity.ok(loginReplyModel);
        }

        loginReplyModel.setMessage("Invalid credentials");
        loginReplyModel.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
        loginReplyModel.setRequestStatus("Failed");
        loginReplyModel.setAuthenticated(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginReplyModel);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<CustomerRegistrationReplyModel> customerRegistration(@RequestBody CustomerRegistrationModel customer) {

        CustomerRegistrationReplyModel reply = new CustomerRegistrationReplyModel();

        String email = customer.getEmail();
        String password = customer.getPassword();
        String first_name = customer.getFirst_name();
        String last_name = customer.getLast_name();
        String contact_number = customer.getContact_number();
        String nic = customer.getNic();
        String gender = customer.getGender();
        Date birthday = Date.valueOf(customer.getBirthday());

        boolean registrationStatus = customerImpl.registerCustomer(email,
                password,
                first_name,
                last_name,
                birthday,
                contact_number,
                nic,
                gender);

        if (registrationStatus) {
            reply.setHttpStatusCode(HttpStatus.CREATED.value());
            reply.setMessage("User creation successful");
            reply.setRequestStatus("success");
            reply.setUserCreation(registrationStatus);
            return ResponseEntity.status(HttpStatus.CREATED).body(reply);
        }

        reply.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        reply.setRequestStatus("failed");
        reply.setMessage("User creation failed");
        reply.setUserCreation(false);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reply);

    }

    @PostMapping("/info")
    @ResponseBody
    public Customer sendCustomerData(@RequestBody CustomerDetailRequestModel model) {
//        String email = request.getParameter("email");
        return customerImpl.sendCustomerDetails(model.getEmail());

    }

}

