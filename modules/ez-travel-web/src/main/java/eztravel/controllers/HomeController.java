package eztravel.controllers;

import eztravel.model.Hire;
import eztravel.model.customer.CustomerCountResponse;
import eztravel.model.driver.DriverCountResponse;
import eztravel.model.feedback.FeedbackRecord;
import eztravel.model.feedback.FeedbackRecordResponse;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Vidushka on 25/09/17.
 */

@Controller
public class HomeController {
    private JSONObject json;
    private RestTemplate template;
    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        Hire hire = new Hire();
        model.addAttribute(hire);
        return "index";
    }

    @GetMapping("/admin")
    public String viewAdmin(Model model, HttpSession session) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        model.addAttribute("admin_email", session.getAttribute("username"));
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
        model.addAttribute("feedback_list", viewCustomerFeedback());
        return "admin/home";
    }


    public List<FeedbackRecord> viewCustomerFeedback() {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());
        String url = baseUrl + "feedback/sendfeedbacks";

        json.put("admin_email", "superadmin@mail.com");
        try {
            FeedbackRecordResponse response = template.postForObject(url, json, FeedbackRecordResponse.class);

            if (response.getRequestStatus().equals("success")) {
                return response.getFeedbackRecords();
            } else {
                return null;
            }
        } catch (RestClientException e) {
            System.out.println("rest client error");
            e.printStackTrace();
            return null;
        }
    }
}
