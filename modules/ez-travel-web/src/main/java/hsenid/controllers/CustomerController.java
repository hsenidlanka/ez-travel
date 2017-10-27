package hsenid.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hsenid.model.customer.Customer;
import hsenid.model.LoginMapper;
import hsenid.model.customer.SignUp;
import hsenid.util.EncryptPassword;
import hsenid.util.ServerResponseErrorHandler;
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

/**
 * Created by Vidushka on 08/09/17.
 */

@Controller
@RequestMapping("/customer/")
public class CustomerController {

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

        JSONObject loginJson = new JSONObject();
        RestTemplate loginTemplate = new RestTemplate();
        EncryptPassword encryptedPassword = new EncryptPassword();
        loginTemplate.setErrorHandler(new ServerResponseErrorHandler());

        String url = "http://localhost:50000/api/customer/login";
        loginJson.put("email", customer.getEmail());
        loginJson.put("password", encryptedPassword.encryptPassword(customer.getPassword()));

        LoginMapper loginMapper = null;
        try {
            loginMapper = loginTemplate.postForObject(url, loginJson, LoginMapper.class);
        } catch (RestClientException e) {
            model.addAttribute("login_error", "Invalid username or password!!");
            return "login";
        }
        model.addAttribute("name", loginMapper.getMessage());
        session.setAttribute("username", customer.getEmail());
        return "home";
    }

    @GetMapping("signup")
    public String viewSignUp(SignUp signUp){
        return "customerSignup";
    }

    @PostMapping("signup")
    public String createCustomer(@ModelAttribute("signUp") @Valid SignUp signUp, BindingResult result, HttpSession session, Model model){
        if (result.hasErrors()){
            return "customerSignup";
        }
        return "home";
    }

    @GetMapping("password_reset")
    public String forgotPassword(Customer customer) {
        return "resetPassword";
    }
}
