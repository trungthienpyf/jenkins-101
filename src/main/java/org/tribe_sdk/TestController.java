package org.tribe_sdk;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TestController {

        public static void main(String[] args) {
            SpringApplication.run(TestController.class, args);
        }


    @GetMapping("/")
    public String test() {
      return "test";
    }
}
