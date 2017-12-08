package hsenid.controllers;

import hsenid.model.Hire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Contended;

import javax.validation.Valid;

/**
 * Created by Vidushka on 25/09/17.
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndexPage(Model model) {
        //return new ModelAndView("index", "hire", new Hire());
        Hire hire = new Hire();
        model.addAttribute(hire);
        return "index";
    }
}
