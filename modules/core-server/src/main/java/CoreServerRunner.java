import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @version 1.0
 * @auther Vidushka
 */

@SpringBootApplication
//
@ComponentScan({"eztravel.controllers", "eztravel.model", "eztravel.core", "corelogic.domain", "corelogic.config", "corelogic.repository"})
public class CoreServerRunner {

    /**
     * The Main class of Core server which responsible for starting the Server (Spring boot application)
     */
    public static void main(String[] args) {

        SpringApplication.run(CoreServerRunner.class, args);

    }

}
