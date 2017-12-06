package eztravel.controllers;

import eztravel.model.ResponseMapper;
import eztravel.model.customer.Customer;
import eztravel.model.customer.SignUp;
import eztravel.util.EncryptPassword;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vidushka on 08/09/17.
 */

@Controller
@RequestMapping("/customer/")
public class CustomerController {
    private JSONObject json;
    private RestTemplate template;
    private Date birthday;
    private EncryptPassword encryptedPassword = new EncryptPassword();
    private String baseUrl = "http://localhost:50000/api/";
    private ResponseMapper responseMapper = null;

    @GetMapping("login")
    public String viewLogin(Customer customer, HttpSession session) {
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "login";
        } else {
            return "home";
        }
    }

    @PostMapping("login")
    public String login(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()){
            return "login";
        }

        session.setAttribute("username", customer.getEmail());

        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/login";
        json.put("email", customer.getEmail());
        json.put("password", encryptedPassword.encryptPassword(customer.getPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("login_error", "Invalid username or password!!");
            return "login";
        }
        model.addAttribute("name", responseMapper.getMessage());
        session.setAttribute("username", customer.getEmail());
        return "home";
    }

    @GetMapping("signup")
    public String viewSignUp(SignUp signUp){
        return "customerSignup";
    }

    @PostMapping("signup")
    public String createCustomer(@ModelAttribute("signUp") @Valid SignUp signUp, BindingResult result, HttpSession session, Model model){
        System.out.println("1");
        if (result.hasErrors()){
            return "customerSignup";
        }
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/register";
        String dob = (signUp.getYear() + "-" + signUp.getMonth() + "-" + signUp.getDay());
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MMM-dd").parse(dob);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


            json.put("email", signUp.getEmail());
            json.put("password", encryptedPassword.encryptPassword(signUp.getPassword()));
            json.put("first_name", signUp.getFirstName());
            json.put("last_name", signUp.getLastName());
            json.put("contact_number", signUp.getMobileNumber());
            json.put("nic", signUp.getNic());
            json.put("gender", "Male");
            json.put("birthday", sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("signup_error", "Can not create the account right now. Please try again.");
            return "login";
        }
        model.addAttribute("name", responseMapper.getMessage());
        if (responseMapper.getRequestStatus().equals("true")) {
            model.addAttribute("signup_success", "You are signed up successfully!");
            return "home";
        } else {
            model.addAttribute("signup_error", "User already exist.");
            return "customerSignup";
        }
    }

    @GetMapping("settings")
    public String viewInfo(){
        return "settings";
    }

    @PostMapping("settings")
    public String updateSettings(@ModelAttribute("customerInfo") @Valid SignUp customerInfo, BindingResult result, HttpSession session, Model model){
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/info";
        json.put("email", session.getAttribute("name"));
        SignUp customer = null;
        try {
            customer = template.postForObject(url, json, SignUp.class);
        } catch (RestClientException e) {
            model.addAttribute("getInfo_error","Error loading user info!");
            e.printStackTrace();
        }
        System.out.println(customer.getFirstName() + customer.getNic());
        return "settings";
    }

    @GetMapping("password_reset")
    public String forgotPassword(Customer customer) {
        return "resetPassword";
    }
}
