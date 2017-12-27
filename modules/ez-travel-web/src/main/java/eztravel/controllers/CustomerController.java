package eztravel.controllers;

import eztravel.model.Hire;
import eztravel.model.HireRecordResponseMapper;
import eztravel.model.ResponseMapper;
import eztravel.model.customer.*;
import eztravel.util.EncryptPassword;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
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
 * Created by Vidushka
 */

@Controller
@RequestMapping("/customer/")
public class CustomerController {
    private JSONObject json;
    private RestTemplate template;
    private EncryptPassword encryptedPassword = new EncryptPassword();
    private String baseUrl = "http://localhost:50000/api/";
    private ResponseMapper responseMapper = null;

    @GetMapping("login")
    public String viewLogin(Customer customer, Hire hire, HttpSession session,
                            @RequestParam(value = "pickup", required = false) String pickup,
                            @RequestParam(value = "drop", required = false) String drop,
                            @RequestParam(value = "length", required = false) String distance,
                            @RequestParam(value = "vehicleType", required = false) String vehicleType) {

        session.setAttribute("pickup", pickup);
        session.setAttribute("drop", drop);
        session.setAttribute("distance", distance);
        session.setAttribute("vehicleType", vehicleType);
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            System.out.println("not logged in");
            return "login";
        } else {
            System.out.println("already logged in");
            return "home";
        }
    }

    @PostMapping("login")
    public String login(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("login_error", "Invalid username or password!!");
            System.out.println("invalid username and password");
            return "login";
        }

        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/login";
        json.put("email", customer.getEmail());
        json.put("password", encryptedPassword.encryptPassword(customer.getPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("login_error", "Can't login right now!");
            System.out.println("login fail. rest call exception");
            return "login";
        }

        if (responseMapper.getRequestStatus().equals("Successful")) {
            model.addAttribute("name", responseMapper.getMessage());
            session.setAttribute("username", customer.getEmail());
            Hire hire = new Hire();
            if (session.getAttribute("pickup") == "" || session.getAttribute("pickup") == null) {
                model.addAttribute(hire);
                System.out.println("logged in to place hire");
                return "home";
            } else {
                hire.setPickup(session.getAttribute("pickup").toString());
                hire.setDrop(session.getAttribute("drop").toString());
                hire.setDistance(session.getAttribute("distance").toString());
                hire.setVehicleType(session.getAttribute("vehicleType").toString());
                model.addAttribute(hire);
                System.out.println("login success");
                return "home";
            }

        } else {
            model.addAttribute("login_error", "Invalid username or password!!");
            System.out.println("login fail invalid credentials");
            return "redirect:login";
        }
    }

    @GetMapping("signup")
    public String viewSignUp(SignUp signUp, HttpSession session) {
        return "customerSignup";
    }

    @PostMapping("signup")
    public String createCustomer(@ModelAttribute("signUp") @Valid SignUp signUp, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
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
            return "redirect:login";
        }
        model.addAttribute("name", responseMapper.getMessage());
        if (responseMapper.getRequestStatus().equals("true")) {
            model.addAttribute("signup_success", "You are signed up successfully!");
            session.setAttribute("username", signUp.getEmail());
            return "redirect:login";
        } else {
            model.addAttribute("signup_error", "User already exist.");
            return "redirect:signup";
        }
    }

    @GetMapping("settings")
    public String viewInfo(@ModelAttribute("updatePassword") PasswordUpdate updatePassword,
                           @ModelAttribute("deleteAccount") DeleteCustomer deleteCustomer,
                           @ModelAttribute("personalInfo") CustomerInfo customer, HttpSession session, Model model) {
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "redirect:login";
        } else {
            json = new JSONObject();
            template = new RestTemplate();
            template.setErrorHandler(new ServerResponseErrorHandler());

            String url = baseUrl + "customer/info";
            json.put("email", session.getAttribute("username"));

            try {
                customer = template.postForObject(url, json, CustomerInfo.class);
            } catch (RestClientException e) {
                model.addAttribute("getInfo_error", "Error loading user info!");
                return "home";
            }

            if (customer.getCustomer_id() != 0) {
                model.addAttribute("email", customer.getEmail());
                model.addAttribute("first_name", customer.getFirst_name());
                model.addAttribute("last_name", customer.getLast_name());
                model.addAttribute("birthday", customer.getBirthday());
                model.addAttribute("contact_number", customer.getContact_number());
                model.addAttribute("nic", customer.getNic());
                model.addAttribute("gender", customer.getGender());
                return "settings";
            } else {
                return "home";
            }
        }
    }

    @PostMapping("updateInfo")
    public String updateSettings(@ModelAttribute("personalInfo") @Valid CustomerInfo customerInfo, BindingResult result,
                                 HttpSession session, Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/updatecontacts";
        json.put("email", session.getAttribute("username"));
        json.put("firstName", customerInfo.getFirst_name());
        json.put("lastName", customerInfo.getLast_name());
        json.put("contactNumber", customerInfo.getContact_number());

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("update_user_error", "Connection problem. Please try again.");
            return "redirect:settings";
        }

        if (responseMapper.getRequestStatus().equals("updated")) {
            model.addAttribute("update_user_status", "Updated successfully successfully!");
            return "redirect:settings";
        } else {
            model.addAttribute("update_user_error", "Can not update this moment!");
            return "redirect:settings";
        }
    }

    @PostMapping("updatePassword")
    public String updatePassword(@ModelAttribute("updatePassword") @Valid PasswordUpdate passwordUpdate, BindingResult result, HttpSession session, Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        if (!(passwordUpdate.getNewPassword().equals(passwordUpdate.getReNewPassword()))) {
            model.addAttribute("password_not-matching_error", "Password not matching!");
            return "redirect:settings";
        }

        String url = baseUrl + "customer/updatepassword";
        json.put("email", session.getAttribute("username"));
        json.put("currentPassword", encryptedPassword.encryptPassword(passwordUpdate.getPassword()));
        json.put("newPassword", encryptedPassword.encryptPassword(passwordUpdate.getNewPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("update_password_error", "Connection problem. Please try again.");
            return "redirect:settings";
        }

        if (responseMapper.getRequestStatus().equals("updated")) {
            model.addAttribute("update_password_error", "Updated successfully successfully!");
            return "redirect:settings";
        } else {
            model.addAttribute("current_password_error", "Incorrect current password!");
            return "redirect:settings";
        }
    }

    @PostMapping("deleteAccount")
    public String deleteAccount(@ModelAttribute("deleteAccount") DeleteCustomer deleteAccount, Model model, HttpSession session) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "customer/deleteaccount";
        json.put("email", session.getAttribute("username"));
        json.put("password", encryptedPassword.encryptPassword(deleteAccount.getPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("delete_account_error", "Connection problem. Please try again.");
            return "redirect:settings";
        }

        if (responseMapper.getRequestStatus().equals("success")) {
            model.addAttribute("delete_account_status", "Deletion completed!");
            session.removeAttribute("username");
            return "redirect:login";
        } else {
            model.addAttribute("delete_account_status", "Incorrect password!");
            return "settings";
        }
    }

    @GetMapping("myTrips")
    public String viewMyTrips(HttpSession session, Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "redirect:login";
        } else {
            try {

                json.put("customer_email", session.getAttribute("username"));
                String url = baseUrl + "hire/customerrecords";
                HireRecordResponseMapper[] mapper = template.postForObject(url, json, HireRecordResponseMapper[].class);

                model.addAttribute("hires", mapper);
                return "myTrips";
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }
        return "login";
    }

    @PostMapping("feedback")
    @ResponseBody
    public String addFeedback(HttpSession session, FeedbackRequest feedback) {

        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "redirect:login";
        }

        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        json.put("customer_email", session.getAttribute("username"));
        json.put("driver_id", feedback.getDriver_id());
        json.put("hire_id", feedback.getHire_id());
        json.put("description", feedback.getFeedbackDescription());

        String url = baseUrl + "feedback/addcustomerfeedback";
        FeedbackResponse response = template.postForObject(url, json, FeedbackResponse.class);
        if (response.getIsCustomerFeedbackSucceed().equals("true")) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/logout")
    public String customerLogout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:login";
    }
}
