package tr.com.sule;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.sule.config.PersistenceConfig;
import tr.com.sule.config.SchedulerService;
import tr.com.sule.domain.Student;
import tr.com.sule.service.CommonService;
import tr.com.sule.service.IStudentService;
import tr.com.sule.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author scinkir 25.09.2018
 */

//@SpringBootApplication ->> when use they ar unneccasry @EnableAutoConfiguration, @ComponentScan and @SpringBootConfiguration annotation
//It enables the scanning of config classes, files and load them into spring context.
@SpringBootApplication
@RestController
@EnableScheduling//To use Spring’s Scheduler, you need to put @EnableScheduling annotation above any of your class.
@EnableAutoConfiguration(exclude = {  DataSourceAutoConfiguration.class })
public class Core2018Application extends SpringBootServletInitializer {
//We need to extend the class SpringBootServletInitializer to support WAR file deployment
//Spring Boot Servlet Initializer class file allows you to configure the application when it is launched by using Servlet Container.
public static final String MKS_URL = "https://mks.turksat.com.tr";
    @Value("${spring.application.name}")
    private String name;
    @Autowired
    private CommonService commonService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    @Qualifier("casUserService")
    private AuthenticationUserDetailsService casUserService;

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

    @RequestMapping(value = "/student")
    public String student() {
        Student student = new Student();
        student.setName("sule");
        student.setPassportNumber("1");
        studentService.insert(student);
        return studentService.getAllStudent().stream().toString();
    }
}
