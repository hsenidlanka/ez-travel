package eztravel.controllers;

import eztravel.model.Hire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Vidushka on 25/09/17.
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndexPage(Model model) {
        Hire hire = new Hire();
        model.addAttribute(hire);
        return "index";
    }
}
