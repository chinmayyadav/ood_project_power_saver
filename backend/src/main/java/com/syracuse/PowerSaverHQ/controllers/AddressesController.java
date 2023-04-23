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
    public ResponseEntity<Object> getAddressesUser(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", addressServices.getAddressesUser(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/get-addresses-by-id",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getAddressesID(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", addressServices.getAddressesID(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/save-address-for-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> saveAddress(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", addressServices.saveAddressUser(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit-address-for-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> editAddress(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", addressServices.editAddress(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/delete-address-for-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> deleteAddress(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", addressServices.deleteAddress(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
