package com.syracuse.insights.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class InsightsController {
    @RequestMapping(value = "/health",headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> saveNotificationPreference() throws JSONException {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", "");
        return new ResponseEntity<>(jsObj.toString(), HttpStatus.OK);
    }
}
