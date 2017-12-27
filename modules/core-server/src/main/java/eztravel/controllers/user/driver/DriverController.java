package eztravel.controllers.user.driver;


import corelogic.domain.user.driver.Driver;
import corelogic.repository.user.driver.Implementation.DriverImpl;
import eztravel.model.driver.*;
import eztravel.model.reply.customer.CountOfVerifiedDriversReplyModel;
import eztravel.model.reply.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * This Controller class is responsible for actions which affects to Driver.
 * For detailed instruction of this api please refer below spreadsheet
 * https://goo.gl/FQcZMw
 *
 * Here I have used ResponseEntity to create Custom server-side responses,
 * so we could control the Http Response
 *
 * @version 1.0
 * @auther Vidushka
 */
@RequestMapping("/driver")
@RestController
public class DriverController {

    @Autowired
    DriverImpl driverImpl;

    /**
     * This controller method is responsible for driver authorizations
     *
     * @param loginModel - LoginModel model that contains driver's authorization details (Email and Sha256 hashed password)
     * @return - LoginReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginReplyModel> isUserDriverOrNot(@RequestBody LoginModel loginModel) {
        LoginReplyModel loginReplyModel = new LoginReplyModel();
        boolean loginStatus = driverImpl.isDriverAuthenticated(loginModel.getEmail(), loginModel.getPassword());

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
     * This controller method is responsible for add Drivers to database
     *
     * @param driver - DriverRegistrationModel Model that contains Driver's details which need to add into database.
     * @return - DriverRegistrationReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<DriverRegistrationReplyModel> driverRegistration(@RequestBody DriverRegistrationModel driver) {

        DriverRegistrationReplyModel reply = new DriverRegistrationReplyModel();

        String email = driver.getEmail();
        String password = driver.getPassword();
        String first_name = driver.getFirst_name();
        String last_name = driver.getLast_name();
        String contact_number = driver.getContact_number();
        String nic = driver.getNic();
        String gender = driver.getGender();
        Date birthday = Date.valueOf(driver.getBirthday());
        String license_number = driver.getLicense_number();


        boolean registrationStatus = driverImpl.registerDriver(
                email,
                password,
                first_name,
                last_name,
                license_number,
                contact_number,
                birthday,
                gender,
                nic );

        if (registrationStatus) {

            reply.setHttpStatusCode(HttpStatus.CREATED.value());
            reply.setMessage("Driver creation successful");
            reply.setRequestStatus("success");
            reply.setUserCreation(registrationStatus);
            return ResponseEntity.status(HttpStatus.CREATED).body(reply);

        }

        reply.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        reply.setRequestStatus("failed");
        reply.setMessage("Driver creation failed");
        reply.setUserCreation(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reply);

    }

    /**
     * This controller method is responsible for sending Driver's Information.
     *
     * @param model - DriverDetailRequestModel which have email of driver that you need information
     * @return - Driver object wrapped in ResponseEntity
     */
    @PostMapping("/info")
    public ResponseEntity<Driver> sendDriverDate(@RequestBody DriverDetailRequestModel model) {
        if (driverImpl.isDriverInDatabase(model.getEmail())){

            return ResponseEntity.status(HttpStatus.OK).body(driverImpl.sendDriverDetails(model.getEmail()));

        }

        Driver noDriverInThatEmail = new Driver();
        noDriverInThatEmail.setDriver_id(0);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noDriverInThatEmail);
    }

    /**
     * This controller method is responsible for updating driver's password
     *
     * @param model - DriverPasswordUpdateModel model which have current password, New password and email.
     * @return - DriverPasswordUpdateReplyModel model wrapped in ResponseEntity
     */
    @PostMapping("/updatepassword")
    public ResponseEntity<DriverPasswordUpdateReplyModel> passwordUpdateModel(@RequestBody DriverPasswordUpdateModel model)
    {
        boolean isPasswordUpdated=driverImpl.updatePassword(model.getEmail(),model.getCurrentPassword(),model.getNewPassword());
        DriverPasswordUpdateReplyModel driverPasswordUpdateReplyModel=new DriverPasswordUpdateReplyModel();

        if(isPasswordUpdated)
        {
            driverPasswordUpdateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            driverPasswordUpdateReplyModel.setRequestStatus("Updated");
            driverPasswordUpdateReplyModel.setMessage("Password Update Success");
            driverPasswordUpdateReplyModel.setPasswordUpdated(true);

            return ResponseEntity.status(HttpStatus.OK).body(driverPasswordUpdateReplyModel);

        }
        driverPasswordUpdateReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        driverPasswordUpdateReplyModel.setRequestStatus("Failed");
        driverPasswordUpdateReplyModel.setMessage("Password Update Failed");
        driverPasswordUpdateReplyModel.setPasswordUpdated(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(driverPasswordUpdateReplyModel);
    }


    /**
     * This controller method is responsible for self deletion of driver account.
     *
     * @param model DriverDetailRequestModel model which contains email and password (sha256 hashed) of the driver who need to be banned
     * @return DriverDeleteReplyModel model wrapped in ResponseEntity
     *
     */
    @PostMapping("/deleteaccount")
    public ResponseEntity<DriverDeleteReplyModel> deleteDriverAccount(@RequestBody DriverDetailRequestModel model) {

        DriverDeleteReplyModel driverDeleteReplyModel = new DriverDeleteReplyModel();


        if (driverImpl.isDriverDeleted(model.getEmail(),model.getPassword())) {
            driverDeleteReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            driverDeleteReplyModel.setRequestStatus("success");
            driverDeleteReplyModel.setMessage("Driver deletion success!");
            driverDeleteReplyModel.setUserDeletion(true);

            return ResponseEntity.status(HttpStatus.OK).body(driverDeleteReplyModel);

        }

        driverDeleteReplyModel.setHttpStatusCode(500);
        driverDeleteReplyModel.setRequestStatus("failed");
        driverDeleteReplyModel.setMessage("Driver deletion failed!");
        driverDeleteReplyModel.setUserDeletion(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(driverDeleteReplyModel);
    }

    /**
     * This controller method is responsible for updating contact details (First Name, Last Name, Contact Number)
     *
     * @param model - DriverContactsUpdateRequestModel model which have new contact details (email, firstName, lastName, contactNumber)
     * @return - DriverContactUpdateReplyModel model wrapped in ResponseEntity
     */
    @PostMapping("/updatecontacts")
    public ResponseEntity<DriverContactUpdateReplyModel> updateDriverContacts(@RequestBody DriverContactsUpdateRequestModel model)
    {
        boolean isContactsUpdated=driverImpl
                .updateContactDetails(model.getEmail(),model.getFirstName(),model.getLastName(),model.getContactNumber());

        DriverContactUpdateReplyModel contactUpdateReplyModel=new DriverContactUpdateReplyModel();

        if(isContactsUpdated)
        {
            contactUpdateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            contactUpdateReplyModel.setRequestStatus("Updated");
            contactUpdateReplyModel.setMessage("Driver Contact Details Updated successfully");
            contactUpdateReplyModel.setContactUpdated(true);

            return ResponseEntity.status(HttpStatus.OK).body(contactUpdateReplyModel);
        }

        contactUpdateReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        contactUpdateReplyModel.setRequestStatus("Faild");
        contactUpdateReplyModel.setMessage("Driver Contact Details Update Faild");
        contactUpdateReplyModel.setContactUpdated(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contactUpdateReplyModel);

    }

    /**
     * This controller method is responsible for banning customers.
     *
     * @param model DriverBanRequestModel model which contains email of driver who need to be banned
     * @return - DriverBanReplyModel model wrapped in ResponseEntity
     *
     */
    @PostMapping("/bandriver")
    public ResponseEntity<DriverBanReplyModel> driverBanReplyModel(@RequestBody DriverBanRequestModel model)
    {
        DriverBanReplyModel driverBanReplyModel=new DriverBanReplyModel();
        if(driverImpl.banDriver(model.getEmail()))
        {
            driverBanReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            driverBanReplyModel.setRequestStatus("Success");
            driverBanReplyModel.setDriverBanned(true);
            driverBanReplyModel.setMessage("Driver Ban Success");

            return ResponseEntity.status(HttpStatus.OK).body(driverBanReplyModel);

        }

        driverBanReplyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        driverBanReplyModel.setRequestStatus("Faild");
        driverBanReplyModel.setDriverBanned(false);
        driverBanReplyModel.setMessage("Driver Ban Faild");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(driverBanReplyModel);

    }

    @PostMapping("/count")
    public ResponseEntity<CountOfVerifiedDriversReplyModel> countOfVerifiedDrivers() {
        CountOfVerifiedDriversReplyModel replyModel = new CountOfVerifiedDriversReplyModel();

        try {

            int totalCustomer = driverImpl.countOfVerifiedDrivers();

            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Verified Driver Count retrieved");
            replyModel.setCountVerifiedDrivers(totalCustomer);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Verified Driver count retrieve failed");
        replyModel.setCountVerifiedDrivers(0);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }
}