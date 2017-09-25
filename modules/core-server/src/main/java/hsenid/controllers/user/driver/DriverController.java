package hsenid.controllers.user.driver;

import hsenid.domain.user.driver.IsAuthenticatedDriver;
import hsenid.enums.HttpStatusCodes;
import hsenid.model.ReplyFromServer;
import hsenid.repository.user.driver.DriverImpl;

import hsenid.repository.user.driver.domain.Driver;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static java.sql.Date.valueOf;

/**
 * Created by hsenid on 9/8/17.
 */
@RestController
public class DriverController {

    @Autowired
    DriverImpl driver;

    @PostMapping("/driver/login")
    @ResponseBody
    public ReplyFromServer isAuthenticatedDriverOrNot(HttpServletRequest httpServletRequest) throws JSONException {
        IsAuthenticatedDriver isAuthenticatedDriver = new IsAuthenticatedDriver();
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        isAuthenticatedDriver.setDriver(driver.isDriverAuthenticate(email, password));

        ReplyFromServer replyFromServer = new ReplyFromServer();

        boolean loginStatus = driver.isDriverAuthenticate(email, password);

        if (loginStatus) {
            replyFromServer.setMessage("Valid credentials");
            replyFromServer.setHttpStatusCode(HttpStatusCodes.OK.getValue());
            replyFromServer.setRequestStatus("Successful");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("RequestStatus", true);

            replyFromServer.addData(jsonObject);
            return replyFromServer;
        }
        replyFromServer.setMessage("Invalid Credentials");
        replyFromServer.setHttpStatusCode(HttpStatusCodes.UNAUTHORIZED.getValue());
        replyFromServer.setRequestStatus("Faild");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("RequestStatus",false);
        replyFromServer.addData(jsonObject);
        return replyFromServer;


    }



    @PostMapping("/driver/register")
    @ResponseBody
    public ResponseEntity<ReplyFromServer> driverRegistration(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String first_name = httpServletRequest.getParameter("first_name");
        String last_name = httpServletRequest.getParameter("last_name");
        String license_number = httpServletRequest.getParameter("license_number");
        String contact_number = httpServletRequest.getParameter("contact_number");
        Date birthday = valueOf(httpServletRequest.getParameter("birthday"));
        String gender = httpServletRequest.getParameter("gender");
        Integer driver_status = Integer.valueOf(httpServletRequest.getParameter("driver_status"));
        Integer confirmed_by = Integer.valueOf(httpServletRequest.getParameter("confirmed_by"));
        String nic = httpServletRequest.getParameter("nic");
        ReplyFromServer replyFromServer=new ReplyFromServer();
        boolean registrationStatus=driver.registerDriver(new Driver(email, password, first_name, last_name, license_number, contact_number, (java.sql.Date) birthday, gender, driver_status, confirmed_by, nic));
        if (registrationStatus)
        {
            replyFromServer.setHttpStatusCode(HttpStatusCodes.CREATED.getValue());
            replyFromServer.setMessage("Driver Successfully Created");
            replyFromServer.setRequestStatus("Success");
            JSONObject jsonObjectDriverCreated=new JSONObject();
            jsonObjectDriverCreated.put("isDriverCreated",true);
            replyFromServer.addData(jsonObjectDriverCreated);
            return ResponseEntity.ok(replyFromServer);
        }
        replyFromServer.setHttpStatusCode(HttpStatusCodes.INTERNAL_SERVER_ERROR.getValue());
        replyFromServer.setRequestStatus("Failed");
        replyFromServer.setMessage("Driver Creation Failed");
        JSONObject jsonObjectDriverCreated=new JSONObject();
        jsonObjectDriverCreated.put("isDriverCreated",false);
        replyFromServer.addData(jsonObjectDriverCreated);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyFromServer);

    }

}
