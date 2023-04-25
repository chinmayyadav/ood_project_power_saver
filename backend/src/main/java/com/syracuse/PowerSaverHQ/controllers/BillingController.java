package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.BillingDetails;
import com.syracuse.PowerSaverHQ.models.PaymentDetails;
import com.syracuse.PowerSaverHQ.services.BillingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BillingController {
    @Autowired
    BillingService billingService;

    @RequestMapping(value = "/get-bill",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getBill(@RequestBody BillingDetails billingDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", billingService.getBillData(billingDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/get-unpaid-bill",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getUnpaidBill(@RequestBody BillingDetails billingDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", billingService.getUnpaidBill(billingDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/pay-bill",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getBillID(@RequestBody BillingDetails billingDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", billingService.getUnpaidBill(billingDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

}
