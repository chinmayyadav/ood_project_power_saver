package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @RequestMapping(value = "/register-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public String customerInformation(@RequestBody UserDetails user) {
        UserService userDetails = new UserService();
        System.out.println(userDetails.add());

    }
}
