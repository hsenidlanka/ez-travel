package hsenid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vidushka on 08/09/17.
 */

@Controller
public class ApplicationController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/viewLogin", method = RequestMethod.GET)
    public String viewLogin() {
        return "login";
    }
}
