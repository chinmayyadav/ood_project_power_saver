package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserDetails user) {
        JSONObject jsObj = new JSONObject();
        String result = userService.RegisterUser(user);
        jsObj.put("Result",result);
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
