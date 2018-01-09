package eztravel.controllers;

import eztravel.model.admin.AdminSignUp;
import eztravel.model.admin.AdminSignUpResponse;
import eztravel.model.admin.BanDriverResponse;
import eztravel.model.admin.FeedbackReviewResponse;
import eztravel.model.customer.SignUp;
import eztravel.model.feedback.FeedbackRecord;
import eztravel.util.EncryptPassword;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Vidushka.
 */

@Controller
@RequestMapping("/admin/")
public class AdminController {
    private JSONObject json;
    private RestTemplate template;
    private EncryptPassword encryptedPassword = new EncryptPassword();
    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping("signup")
    public String viewSignUp(HttpSession session, Model model, @ModelAttribute("signUp") AdminSignUp signup) {
        return "admin/signUp";
    }

    @PostMapping("signup")
    public String createCustomer(@ModelAttribute("signUp") @Valid SignUp signUp, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "customerSignup";
        }
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());
        AdminSignUpResponse response;

        String url = baseUrl + "admin/add";
        String dob = (signUp.getYear() + "-" + signUp.getMonth() + "-" + signUp.getDay());
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MMM-dd").parse(dob);
            String date = new java.sql.Date(utilDate.getTime()).toString();

            json.put("email", signUp.getEmail());
            json.put("password", encryptedPassword.encryptPassword(signUp.getPassword()));
            json.put("first_name", signUp.getFirstName());
            json.put("last_name", signUp.getLastName());
            json.put("birthday", date);
            json.put("contact_number", signUp.getMobileNumber());
            json.put("nic", signUp.getNic());
            json.put("gender", "Male");
            json.put("super_admin_email", "superadmin@mail.com");

            response = template.postForObject(url, json, AdminSignUpResponse.class);
            model.addAttribute("name", response.getMessage());
            if (response.getRequestStatus().equals("success")) {
                model.addAttribute("signup_success", "You are signed up successfully!");
                session.setAttribute("username", signUp.getEmail());
                return "redirect:login";
            } else {
                model.addAttribute("signup_error", "User already exist.");
                return "redirect:signup";
            }

        } catch (ParseException e) {
            e.printStackTrace();
            model.addAttribute("signup_error", "Can not create the account right now. Please try again.");
            return "redirect:login";
        } catch (RestClientException e) {
            e.printStackTrace();
            model.addAttribute("signup_error", "Can not create the account right now. Please try again.");
            return "redirect:login";
        }
    }

    @PostMapping("/banDriver")
    @ResponseBody
    public String banAdmin(FeedbackRecord request) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());
        String url = baseUrl + "driver/bandriver";
        BanDriverResponse response;
        json.put("email", "vidu@vidya.csom");

        try {
            response = template.postForObject(url, json, BanDriverResponse.class);
            if (response.getRequestStatus().equals("Success")) {
                return "Driver banded successfully";
            }
            return "unable to ban driver";
        } catch (RestClientException e) {
            e.printStackTrace();
            return "Unable to ban driver";
        }
    }

    @PostMapping("/reviewCustomerFeedback")
    @ResponseBody
    public String reviewCustomerFeedback(FeedbackRecord request, HttpSession session) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());
        String url = baseUrl + "admin/reviewcustomerfeedback";
        FeedbackReviewResponse response;
        json.put("feedback_id", request.getFeedback_id());
        json.put("admin_email", "superadmin@mail.com");

        try {
            response = template.postForObject(url, json, FeedbackReviewResponse.class);
            if (response.getRequestStatus().equals("success")) {
                return "Customer feedback reviewed successfully";
            }
            return "unable to review feedback";
        } catch (RestClientException e) {
            e.printStackTrace();
            return "Unable to review feedback";
        }
    }
}
