package hsenid.controllers;

import hsenid.model.Hire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vidushka on 12/7/17.
 */
@Controller
@RequestMapping("/")
public class HireController {
    @PostMapping("hire")
    public String hire(@ModelAttribute("hire") Hire hire, BindingResult result, ModelMap model){
        // TODO: 12/8/17 Handling the booking
        return "hiresuccess";// TODO: 12/7/17 Return whatever the view name
    }
}
