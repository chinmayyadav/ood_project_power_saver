package com.syracuse.PowerSaverHQ.controllers;

import com.syracuse.PowerSaverHQ.models.NotificationPreferance;
import com.syracuse.PowerSaverHQ.models.PaymentDetails;
import com.syracuse.PowerSaverHQ.services.PaymentService;
import com.syracuse.PowerSaverHQ.utils.hashingFunction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    com.syracuse.PowerSaverHQ.utils.hashingFunction hashingFunction;

    @RequestMapping(value = "/save-payment-details",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> savePayment(@RequestBody PaymentDetails paymentDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", paymentService.savePreference(paymentDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
    @RequestMapping(value = "/get-payment-details",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> getPayment(@RequestBody PaymentDetails paymentDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Data", paymentService.getCardNumber(paymentDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }
}
