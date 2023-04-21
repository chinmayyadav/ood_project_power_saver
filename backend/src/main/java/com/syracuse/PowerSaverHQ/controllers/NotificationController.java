package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.AddressesDetails;
import com.syracuse.PowerSaverHQ.models.NotificationPreferance;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.NotificationServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    NotificationServices notificationServices;

    @RequestMapping(value = "/save-notification-preference",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> saveNotificationPreference(@RequestBody NotificationPreferance notificationPreferance) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", notificationServices.savePreference(notificationPreferance));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/get-notification-preference",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getNotificationPreference(@RequestBody UserDetails userDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", notificationServices.getPreference(userDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
