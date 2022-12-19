package org.zkoss.zkspringboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@EnableJpaRepositories("org.zkoss.zkspringboot.repository")
@EntityScan("org.zkoss.zkspringboot.entity")
@ComponentScan("org.zkoss.zkspringboot")
@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/carta")
    public String view() {
        return "carta";
    }

    @GetMapping("/secure/{page}")
    public String secure(@PathVariable String page) {
        return "secure/" + page;
    }
}