package com.syracuse.PowerSaverHQ.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value="/")
    public static String Welcome() {
        return "Welcome to Spring Boot \n" +
                "Remember to subscribe and leave a comment";
    }
}
