package tr.com.sule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author scinkir 25.09.2018
 */

//@SpringBootApplication ->> when use they ar unneccasry @EnableAutoConfiguration, @ComponentScan and @SpringBootConfiguration annotation
@SpringBootApplication
@RestController
public class Core2018Application extends SpringBootServletInitializer {
//We need to extend the class SpringBootServletInitializer to support WAR file deployment
//Spring Boot Servlet Initializer class file allows you to configure the application when it is launched by using Servlet Container.

    @Value("${spring.application.name}")
    private String name;

    public static void main(String[] args) {
        SpringApplication.run(Core2018Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Core2018Application.class);
    }

    @RequestMapping(value = "/")
    public String hello() {
        return "Merhaba Dünya from Tomcat "+name;
    }
}
