package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @RequestMapping(value = "/register-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public String customerInformation(@RequestBody UserDetails user) {

        return "Customer information saved successfully ::." + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
    }
}
