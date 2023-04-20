package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import com.syracuse.PowerSaverHQ.services.AppliancesService;
import com.syracuse.PowerSaverHQ.services.UserService;
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
    @RequestMapping(value = "/get-all-appliances",headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllApplicances(@RequestParam("rating") float rating) {
        JSONObject jsObj = new JSONObject();
        JSONArray array = appliancesService.getApplianceData(rating);
        jsObj.put("Data",array);
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-home-appliance",headers = "Accept=application/json", method = RequestMethod.GET)
    public boolean addHomeAppliance(@RequestBody ApplianceDetails applianceDetails) {
        return appliancesService.addAppliances(applianceDetails);
    }

    @RequestMapping(value = "/get-appliances-per-user", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getUserAppliance(@RequestParam("userID") int id){
        JSONObject jsObj = new JSONObject();
        jsObj.put("UserApplianceData",appliancesService.getUserApplianceData(id));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
