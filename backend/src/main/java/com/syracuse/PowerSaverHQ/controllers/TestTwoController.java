package com.syracuse.PowerSaverHQ.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestTwoController {
    @RequestMapping(value="/test-two")
    public static String Welcome() {
        return "Welcome to  MORE PRONDFILK";
    }
}
