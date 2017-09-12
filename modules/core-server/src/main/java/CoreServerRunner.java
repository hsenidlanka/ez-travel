import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by hsenid on 9/6/17.
 */

@SpringBootApplication
@ComponentScan({"hsenid.controllers", "hsenid.repository", "hsenid.domain", "hsenid.config"})
public class CoreServerRunner {
    public static void main(String[] args) {
        SpringApplication.run(CoreServerRunner.class, args);
    }
}
