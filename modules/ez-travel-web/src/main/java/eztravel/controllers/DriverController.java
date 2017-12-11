package eztravel.controllers;

import eztravel.model.Hire;
import eztravel.model.ResponseMapper;
import eztravel.model.customer.DeleteCustomer;
import eztravel.model.customer.PasswordUpdate;
import eztravel.model.customer.SignUp;
import eztravel.model.driver.DeleteAccount;
import eztravel.model.driver.Driver;
import eztravel.model.driver.PersonalInfo;
import eztravel.model.driver.UpdatePassword;
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

/**
 * Created by Vidushka.
 */

@Controller
@RequestMapping("/driver/")
public class DriverController {
    private JSONObject json;
    private RestTemplate template;
    private EncryptPassword encryptedPassword = new EncryptPassword();
    private String baseUrl = "http://localhost:50000/api/";
    private ResponseMapper responseMapper = null;

    @GetMapping("login")
    public String viewDriverLogin(Driver driver, Hire hire, HttpSession session) {
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "driver/login";
        } else {
            return "driver/home";
        }
    }

    @PostMapping("login")
    public String login(@ModelAttribute("driver") Driver driver, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("login_error", "Invalid username or password!!");
            return "redirect:driver/login";
        }

        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "driver/login";
        json.put("email", driver.getEmail());
        json.put("password", encryptedPassword.encryptPassword(driver.getPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("login_error", "Can't login right now!");
            return "redirect:login";
        }

        if (responseMapper.getRequestStatus().equals("Successful")) {
            session.setAttribute("username", driver.getEmail());
            return "redirect:settings";
        } else {
            model.addAttribute("login_error", "Invalid username or password!!");
            return "redirect:login";
        }
    }

    @GetMapping("settings")
    public String viewInfo(@ModelAttribute("updatePassword") UpdatePassword updatePassword,
                           @ModelAttribute("deleteAccount") DeleteAccount deleteAccount,
                           @ModelAttribute("personalInfo") PersonalInfo personalInfo, HttpSession session, Model model) {
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return "redirect:driver/login";
        } else {
            json = new JSONObject();
            template = new RestTemplate();
            template.setErrorHandler(new ServerResponseErrorHandler());

            String url = baseUrl + "driver/info";
            json.put("email", session.getAttribute("username"));
            try {
                personalInfo = template.postForObject(url, json, PersonalInfo.class);
            } catch (RestClientException e) {
                model.addAttribute("getInfo_error", "Error loading user info!");
                return "driver/login";
            }
            if (personalInfo.getDriver_id() != 0) {
                model.addAttribute("email", personalInfo.getEmail());
                model.addAttribute("first_name", personalInfo.getFirst_name());
                model.addAttribute("last_name", personalInfo.getLast_name());
                model.addAttribute("contact_number", personalInfo.getContact_number());
                //model.addAttribute("user_image", customer.getUserImage());
                return "driver/home";
            }
            return "login";
        }
    }


    @PostMapping("updateInfo")
    public String updateSettings(@ModelAttribute("personalInfo") PersonalInfo personalInfo, BindingResult result,
                                 HttpSession session, Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "driver/updatecontacts";
        json.put("email", session.getAttribute("username"));
        json.put("firstName", personalInfo.getFirst_name());
        json.put("lastName", personalInfo.getLast_name());
        json.put("contactNumber", personalInfo.getContact_number());

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("update_user_error", "Connection problem. Please try again.");
            return "redirect:driver/settings";
        }

        if (responseMapper.getRequestStatus().equals("updated")) {
            model.addAttribute("update_user_status", "Updated successfully successfully!");
            return "redirect:driver/settings";
        } else {
            model.addAttribute("update_user_error", "Can not update this moment!");
            return "redirect:driver/settings";
        }
    }

    @PostMapping("updatePassword")
    public String updatePassword(@ModelAttribute("updatePassword") @Valid PasswordUpdate passwordUpdate, BindingResult result, HttpSession session, Model model) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        if (!(passwordUpdate.getNewPassword().equals(passwordUpdate.getReNewPassword()))) {
            model.addAttribute("password_not-matching_error", "Password not matching!");
            return "redirect:driver/settings";
        }

        String url = baseUrl + "driver/updatepassword";
        json.put("email", session.getAttribute("username"));
        json.put("currentPassword", encryptedPassword.encryptPassword(passwordUpdate.getPassword()));
        json.put("newPassword", encryptedPassword.encryptPassword(passwordUpdate.getNewPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("update_password_error", "Connection problem. Please try again.");
            return "redirect:driver/settings";
        }

        if (responseMapper.getRequestStatus().equals("updated")) {
            model.addAttribute("update_password_error", "Updated successfully successfully!");
            return "redirect:driver/settings";
        } else {
            model.addAttribute("current_password_error", "Incorrect current password!");
            return "redirect:driver/settings";
        }
    }

    @PostMapping("deleteAccount")
    public String deleteAccount(@ModelAttribute("deleteAccount") DeleteCustomer deleteAccount, Model model, HttpSession session) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "driver/deleteaccount";
        json.put("email", session.getAttribute("username"));
        json.put("password", encryptedPassword.encryptPassword(deleteAccount.getPassword()));

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("delete_account_error", "Connection problem. Please try again.");
            return "redirect:driver/settings";
        }

        if (responseMapper.getRequestStatus().equals("success")) {
            model.addAttribute("delete_account_status", "Deletion completed!");
            session.removeAttribute("username");
            return "redirect:driver/login";
        } else {
            model.addAttribute("delete_account_status", "Incorrect password!");
            return "redirect:driver/settings";
        }
    }


    @GetMapping("signup")
    public String viewSignUp(SignUp signUp, HttpSession session) {
        return "driver/signup";
    }

    @PostMapping("signup")
    public String createCustomer(@ModelAttribute("signUp") @Valid SignUp signUp, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "driver/signup";
        }
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "driver/register";
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
            return "redirect:driver/login";
        }
        model.addAttribute("name", responseMapper.getMessage());
        if (responseMapper.getRequestStatus().equals("true")) {
            model.addAttribute("signup_success", "You are signed up successfully!");
            session.setAttribute("username", signUp.getEmail());
            return "redirect:driver/login";
        } else {
            model.addAttribute("signup_error", "User already exist.");
            return "redirect:driver/signup";
        }
    }

    @GetMapping("/logout")
    public String customerLogout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:driver/login";
    }
}
