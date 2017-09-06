package controllers;


import hsenid.repository.user.customer.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hsenid on 9/6/17.
 */

@RestController
public class TestJDBC {
    @Autowired
    CustomerImpl customer;

    @GetMapping("/test")
    public boolean test(){

//        CustomerImpl test = new CustomerImpl();
        System.out.println("/ is called");
//        String tttt = customer.isCustomerAuthenticated("tets", "edd");
        return customer.isCustomerAuthenticated("test@email.com", "test123");
    }

   /* private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }*/
}
