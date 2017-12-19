package eztravel.controllers.user.customer;


import corelogic.domain.user.customer.Customer;
import corelogic.repository.user.customer.implementation.CustomerImpl;
import eztravel.model.customer.*;
import eztravel.model.reply.customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


/**
 * This Controller class is responsible for actions which affects to customer.
 * For detailed instruction of this api please refer below spreadsheet
 * https://goo.gl/FQcZMw
 *
 * Here I have used ResponseEntity to create custome server-side responses,
 * so we could control the Http Response
 *
 * @version 1.0O
 * @auther Vidushka
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerImpl customerImpl;

    /**
     * This controller method is responsible for customer authorizations
     *
     * @param loginModel - LoginModel model that contains customer's authorization details (Email and Sha256 hashed password)
     * @return - LoginReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<LoginReplyModel> isUserCustomerOrNot(@RequestBody LoginModel loginModel) {

        LoginReplyModel loginReplyModel = new LoginReplyModel();

        boolean loginStatus = customerImpl.isCustomerAuthenticated(loginModel.getEmail(), loginModel.getPassword());

        if (loginStatus) {

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

    /**
     * This controller method is responsible for add Customers to database
     *
     * @param customer  CustomerRegistrationModel Model that contains Customer's details which need to add into database.
     * @return - CustomerRegistrationReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/register")
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

        reply.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        reply.setRequestStatus("failed");
        reply.setMessage("User creation failed");
        reply.setUserCreation(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reply);

    }

    /**
     * This controller method is responsible for sending Customer Information.
     *
     * @param model - CustomerDetailRequestModel which have email of customer that you need information
     * @return - Customer object wrapped in ResponseEntity
     */
    @PostMapping("/info")
    public ResponseEntity<Customer> sendCustomerData(@RequestBody CustomerDetailRequestModel model) {

        if (customerImpl.isCustomerInDatabase(model.getEmail())){

            return ResponseEntity.status(HttpStatus.OK).body(customerImpl.sendCustomerDetails(model.getEmail()));

        }

        Customer noCustomerInThatEmail = new Customer();
        noCustomerInThatEmail.setCustomer_id(0);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noCustomerInThatEmail);


    }

    /**
     * This controller method is responsible for updating contact details (First Name, Last Name, Contact Number)
     *
     * @param model - CustomerContactsUpdateRequestModel model which have new contact details (email, firstName, lastName, contactNumber)
     * @return - CustomerContactUpdateReplyModel model wrapped in ResponseEntity
     */
    @PostMapping("/updatecontacts")
    public ResponseEntity<CustomerContactUpdateReplyModel> updateCustomerContacts(@RequestBody CustomerContactsUpdateRequestModel model) {

        boolean isContactsUpdated = customerImpl.updateContactDetails(model.getEmail(), model.getFirstName(), model.getLastName(), model.getContactNumber());

        CustomerContactUpdateReplyModel contactUpdateReplyModel = new CustomerContactUpdateReplyModel();

        if (isContactsUpdated) {

            contactUpdateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            contactUpdateReplyModel.setRequestStatus("updated");
            contactUpdateReplyModel.setMessage("Customer contact details successfully updated");
            contactUpdateReplyModel.setContactUpdated(true);

            return ResponseEntity.status(HttpStatus.OK).body(contactUpdateReplyModel);
        }


        contactUpdateReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        contactUpdateReplyModel.setRequestStatus("failed");
        contactUpdateReplyModel.setMessage("Customer contact details updation failed!");
        contactUpdateReplyModel.setContactUpdated(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contactUpdateReplyModel);

    }

    /**
     * This controller method is responsible for updating customer's password
     *
     * @param model - CustomerPasswordUpdateModel model which have current password, New password and email.
     * @return - CustomerPasswordUpdateReplyModel model wrapped in ResponseEntity
     */
    @PostMapping("/updatepassword")
    public ResponseEntity<CustomerPasswordUpdateReplyModel> updateCustomerPassword(@RequestBody CustomerPasswordUpdateModel model) {

        boolean isPasswordUpdated = customerImpl.updatePassword(model.getEmail(), model.getCurrentPassword(), model.getNewPassword());
        CustomerPasswordUpdateReplyModel passwordUpdateReplyModel = new CustomerPasswordUpdateReplyModel();
        if (isPasswordUpdated) {
            passwordUpdateReplyModel.setHttpStatusCode(HttpStatus.OK.value());
            passwordUpdateReplyModel.setRequestStatus("updated");
            passwordUpdateReplyModel.setMessage("Password update successful!");
            passwordUpdateReplyModel.setIsPasswordUpdated(true);

            return ResponseEntity.status(HttpStatus.OK).body(passwordUpdateReplyModel);
        }

        passwordUpdateReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        passwordUpdateReplyModel.setRequestStatus("failed");
        passwordUpdateReplyModel.setMessage("Password update failed!");
        passwordUpdateReplyModel.setIsPasswordUpdated(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(passwordUpdateReplyModel);

    }

    /**
     * This controller method is responsible for banning customers.
     *
     * @param model CustomerBanRequestModel model which contains email of customer who need to be banned
     * @return - CustomerBanReplyModel model wrapped in ResponseEntity
     *
     */
    @PostMapping("/bancustomer")
    public ResponseEntity<CustomerBanReplyModel> banCustomer(@RequestBody CustomerBanRequestModel model) {
        CustomerBanReplyModel banReplyModel = new CustomerBanReplyModel();

        if (customerImpl.banCustomer(model.getEmail())) {

            banReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            banReplyModel.setRequestStatus("success");
            banReplyModel.setCustomerBanned(true);
            banReplyModel.setMessage("Customer banned successful!");

            return ResponseEntity.status(HttpStatus.OK).body(banReplyModel);
        }

        banReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        banReplyModel.setRequestStatus("failed");
        banReplyModel.setMessage("Customer banning failed!");
        banReplyModel.setCustomerBanned(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(banReplyModel);

    }

    /**
     * This controller method is responsible for self deletion of customer account.
     *
     * @param model CustomerDeleteRequestModel model which contains email and password (sha256 hashed) of the customer who need to be banned
     * @return CustomerDeleteReplyModel model wrapped in ResponseEntity
     *
     */
    @PostMapping("/deleteaccount")
    public ResponseEntity<CustomerDeleteReplyModel> deleteCustomerAccount(@RequestBody CustomerDeleteRequestModel model) {

        CustomerDeleteReplyModel deleteReplyModel = new CustomerDeleteReplyModel();


        if (customerImpl.isCustomerDeleted(model.getEmail(), model.getPassword())) {
            deleteReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            deleteReplyModel.setRequestStatus("success");
            deleteReplyModel.setMessage("Customer deletion success!");
            deleteReplyModel.setUserDeletion(true);

            return ResponseEntity.status(HttpStatus.OK).body(deleteReplyModel);
        }

        deleteReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        deleteReplyModel.setRequestStatus("failed");
        deleteReplyModel.setMessage("Customer deletion failed!");
        deleteReplyModel.setUserDeletion(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deleteReplyModel);
    }


}

