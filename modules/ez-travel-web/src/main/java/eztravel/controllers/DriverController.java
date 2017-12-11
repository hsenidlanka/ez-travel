package eztravel.controllers;

import eztravel.model.driver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vidushka.
 */

@Controller
@RequestMapping("/driver/")
public class DriverController {

    @GetMapping("login")
    public String driverLogin(Login login) {
        System.out.println("hello driver");
        return "driver/login";
    }

}
