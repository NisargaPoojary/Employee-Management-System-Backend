package com.example.EMS_backend.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class example {

    @GetMapping("/hello")
    public String sayHello() {
        log.info("Hello endpoint was called");
        log.error("This is an error log");
        return "Hello, World!";
    }
}
