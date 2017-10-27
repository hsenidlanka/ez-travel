import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * Created by Menuka on 9/6/17.
 */

@SpringBootApplication
@ComponentScan({"hsenid.controllers", "hsenid.repository", "hsenid.domain", "hsenid.config"})
public class CoreServerRunner {
//    final Logger logger = LoggerFactory.getLogger(CoreServerRunner.class);

    /*@Autowired
    DataSource dataSource;*/

    public static void main(String[] args) {
//        logger.error("Logger test ======+++++");
        SpringApplication.run(CoreServerRunner.class, args);

    }


   /* public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

    }*/
}
