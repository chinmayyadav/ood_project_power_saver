package com.syracuse.insights.controllers;

import com.syracuse.insights.models.InsightsModel;
import com.syracuse.insights.services.InsightsModelService;
import com.syracuse.insights.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class InsightsController {
    @Autowired
    InsightsModelService insightsModelService;
    @RequestMapping(value = "/compare-with-neighbours-energy",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> compareNeighbourBills(@RequestBody InsightsModel insightsModel) throws JSONException {
        JSONObject jsObj = new JSONObject();
        JSONArray currentUser = insightsModelService.currenUserData(insightsModel);
        JSONArray neighbours = insightsModelService.neighbourData(insightsModel);
        if(currentUser!=null && neighbours!=null) {
            jsObj.put("Data", Constants.STATUS_SUCCESS);
            jsObj.put("CurrentUser", currentUser);
            jsObj.put("Neighbours", neighbours);
        }else {
            jsObj.put("Data", Constants.STATUS_ERROR);
        }

        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/compare-with-neighbours-appliances",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> compareNeighbourAppliances(@RequestBody InsightsModel insightsModel) throws JSONException {
        JSONObject jsObj = new JSONObject();
        JSONArray data = insightsModelService.compareAppliances(insightsModel);
        if(data!=null) {
            jsObj.put("Data", Constants.STATUS_SUCCESS);
            jsObj.put("Appliances", data);
        }else {
            jsObj.put("Data", Constants.STATUS_ERROR);
        }

        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);
    }

}
