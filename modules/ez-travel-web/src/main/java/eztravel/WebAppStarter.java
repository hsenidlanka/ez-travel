package eztravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Vidushka on 08/09/17.
 */

@SpringBootApplication
//@ComponentScan({"eztravel.controllers", "eztravel.model", "eztravel.core", "corelogic.domain", "corelogic.config", "corelogic.repository"})
@ComponentScan({"eztravel.controllers", "eztravel.model", "eztravel.util"})
//@ComponentScan({"controllers", "eztravel.repository", "eztravel.domain", "eztravel.config"})
public class WebAppStarter {
    public static void main(String[] args) {
        SpringApplication.run(WebAppStarter.class, args);
    }
}