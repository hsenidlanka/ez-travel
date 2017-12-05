package hsenid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.Contended;

/**
 * Created by Vidushka on 25/09/17.
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }
}
