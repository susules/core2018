package tr.com.sule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.sule.config.SchedulerService;
import tr.com.sule.service.CommonService;

/**
 * @author scinkir 25.09.2018
 */

//@SpringBootApplication ->> when use they ar unneccasry @EnableAutoConfiguration, @ComponentScan and @SpringBootConfiguration annotation
//It enables the scanning of config classes, files and load them into spring context.
@SpringBootApplication
@RestController
@EnableScheduling//To use Spring’s Scheduler, you need to put @EnableScheduling annotation above any of your class.
public class Core2018Application extends SpringBootServletInitializer {
//We need to extend the class SpringBootServletInitializer to support WAR file deployment
//Spring Boot Servlet Initializer class file allows you to configure the application when it is launched by using Servlet Container.

    @Value("${spring.application.name}")
    private String name;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchedulerService schedulerService;

    public static void main(String[] args) {
        SpringApplication.run(Core2018Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Core2018Application.class);
    }

    @RequestMapping(value = "/")
    public String hello() {
        return "Merhaba Dünya from Tomcat " + name;
    }

    @RequestMapping(value = "/schedule")
    public String schedule() {
        commonService.setScheduleRun(6000);
        return "Merhaba Dünya from schedule " + name;
    }
}
