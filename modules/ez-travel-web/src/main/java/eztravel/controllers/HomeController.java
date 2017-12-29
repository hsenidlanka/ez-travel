package eztravel.controllers;

import eztravel.model.Hire;
import eztravel.model.customer.CustomerCountResponse;
import eztravel.model.driver.DriverCountResponse;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vidushka on 25/09/17.
 */

@Controller
public class HomeController {
    private JSONObject json;
    private RestTemplate template;
    private String baseUrl = "http://localhost:50000/api/";

    @GetMapping("/")
    public String getIndexPage(Model model) {
        Hire hire = new Hire();
        model.addAttribute(hire);
        return "index";
    }

    @GetMapping("/admin")
    public String viewAdmin(Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/count";
        CustomerCountResponse response = template.postForObject(url, json, CustomerCountResponse.class);
        if (response.getRequestStatus().equals("success")) {
            model.addAttribute("customerCount", response.getCountOfUnbanCustomer());
        } else {
            model.addAttribute("customerCount", "...");
        }

        String driverUrl = baseUrl + "driver/count";
        DriverCountResponse driverResponse = template.postForObject(driverUrl, json, DriverCountResponse.class);
        if (driverResponse.getRequestStatus().equals("success")) {
            model.addAttribute("driverCount", driverResponse.getCountVerifiedDrivers());
        } else {
            model.addAttribute("driverCount", "...");
        }
        return "admin/home";
    }
}
