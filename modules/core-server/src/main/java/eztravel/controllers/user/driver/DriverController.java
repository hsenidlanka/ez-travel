package eztravel.controllers.user.driver;


import corelogic.domain.user.driver.Driver;
import corelogic.repository.user.driver.Implementation.DriverImpl;
import eztravel.model.driver.DriverDetailRequestModel;
import eztravel.model.driver.DriverPasswordUpdateModel;
import eztravel.model.driver.DriverRegistrationModel;
import eztravel.model.driver.LoginModel;

import eztravel.model.reply.DriverDeleteReplyModel;
import eztravel.model.reply.DriverPasswordUpdateReplyModel;
import eztravel.model.reply.DriverRegistrationReplyModel;
import hsenid.model.reply.LoginReplyModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Created by Vidushika on 9/8/17.
 */
@RequestMapping("/driver/")
@RestController
public class DriverController {

    @Autowired
    DriverImpl driverImpl;

    @PostMapping("login")
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
        String confirmed_by = driver.getConfirmed_by();


        boolean registrationStatus = driverImpl.registerDriver(
                email,
                password,
                first_name,
                last_name,
                license_number,
                contact_number,
                birthday,
                gender,
                confirmed_by,
                nic );
        if (registrationStatus) {
            reply.setHttpStatusCode(HttpStatus.CREATED.value());
            reply.setMessage("Driver creation successful");
            reply.setRequestStatus("success");
            reply.setUserCreation(registrationStatus);
            return ResponseEntity.status(HttpStatus.CREATED).body(reply);
        }

        reply.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        reply.setRequestStatus("failed");
        reply.setMessage("Driver creation failed");
        reply.setUserCreation(false);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reply);


    }

    @PostMapping("info")
    @ResponseBody
    public Driver sendDriverDate(@RequestBody DriverDetailRequestModel model) {
        return driverImpl.sendDriverDetails(model.getEmail());
    }

    @PostMapping("updatepassword")
    @ResponseBody
    public DriverPasswordUpdateReplyModel passwordUpdateModel(@RequestBody DriverPasswordUpdateModel model)
    {
        boolean isPasswordUpdated=driverImpl.updatePassword(model.getEmail(),model.getCurrentPassword(),model.getNewPassword());
        DriverPasswordUpdateReplyModel driverPasswordUpdateReplyModel=new DriverPasswordUpdateReplyModel();

        if(isPasswordUpdated)
        {
            driverPasswordUpdateReplyModel.setHttpStatusCode(204);
            driverPasswordUpdateReplyModel.setRequestStatus("Updated");
            driverPasswordUpdateReplyModel.setMessage("Password Update Success");
            driverPasswordUpdateReplyModel.setPasswordUpdated(true);
            return driverPasswordUpdateReplyModel;

        }
        driverPasswordUpdateReplyModel.setHttpStatusCode(500);
        driverPasswordUpdateReplyModel.setRequestStatus("Failed");
        driverPasswordUpdateReplyModel.setMessage("Password Update Failed");
        driverPasswordUpdateReplyModel.setPasswordUpdated(false);

        return driverPasswordUpdateReplyModel;
    }
    @PostMapping("deleteaccount")
    @ResponseBody
    public DriverDeleteReplyModel deleteDriverAccount(@RequestBody DriverDetailRequestModel model) {

        DriverDeleteReplyModel driverDeleteReplyModel = new DriverDeleteReplyModel();


        if (driverImpl.isDriverDeleted(model.getEmail())) {
            driverDeleteReplyModel.setHttpStatusCode(204);
            driverDeleteReplyModel.setRequestStatus("success");
            driverDeleteReplyModel.setMessage("Driver deletion success!");
            driverDeleteReplyModel.setUserDeletion(true);

            return driverDeleteReplyModel;
        }

        driverDeleteReplyModel.setHttpStatusCode(500);
        driverDeleteReplyModel.setRequestStatus("failed");
        driverDeleteReplyModel.setMessage("Driver deletion failed!");
        driverDeleteReplyModel.setUserDeletion(false);

        return driverDeleteReplyModel;
    }

}