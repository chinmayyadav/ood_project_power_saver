package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.AppliancesService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AppliancesController {
    @Autowired
    private AppliancesService appliancesService;
    @RequestMapping(value = "/get-all-appliances",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getAllApplicances(@RequestBody ApplianceDetails applianceDetails) {
        JSONObject jsObj = new JSONObject();
        JSONArray array = appliancesService.getApplianceData(applianceDetails);
        jsObj.put("Data",array);
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-home-appliance",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> addHomeAppliance(@RequestBody ApplianceDetails applianceDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", appliancesService.addAppliances(applianceDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-appliances-per-user", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getUserAppliance(@RequestBody UserDetails userDetails){
        JSONObject jsObj = new JSONObject();
        jsObj.put("UserApplianceData",appliancesService.getUserApplianceData(userDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
