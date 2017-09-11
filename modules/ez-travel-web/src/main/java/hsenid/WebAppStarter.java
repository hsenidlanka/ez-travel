package hsenid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Vidushka on 08/09/17.
 */
@SpringBootApplication
@ComponentScan("hsenid")
//@ComponentScan({"controllers", "hsenid.repository", "hsenid.domain", "hsenid.config"})
public class WebAppStarter {
    public static void main(String[] args) {
        SpringApplication.run(WebAppStarter.class, args);
    }
}
