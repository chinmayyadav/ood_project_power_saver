package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public String customerInformation(@RequestBody UserDetails user) {
        Boolean result = userService.RegisterUser(user);
        System.out.println("result " + result);

        return "Customer information saved successfully ::." + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
    }
}
