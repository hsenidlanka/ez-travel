package eztravel.controllers;

import eztravel.model.admin.AdminSignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Vidushka.
 */

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("signup")
    public String viewSignUp(HttpSession session, Model model, @ModelAttribute("signUp") AdminSignUp signup) {
        return "admin/signUp";
    }
}
