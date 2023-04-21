package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.AddressesDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.AddressServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AddressesController {
    @Autowired
    private AddressServices addressServices;

    @RequestMapping(value = "/get-addresses-per-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getAddressesUser(@RequestBody UserDetails userDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", addressServices.getAddressesUser(userDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/save-address-for-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> saveAddress(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", addressServices.saveAddressUser(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

}
