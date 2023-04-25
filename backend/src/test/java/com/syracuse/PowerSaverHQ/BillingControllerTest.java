package com.syracuse.PowerSaverHQ;

import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingControllerTest {
    @Test
    public void getApplianceTest(){
        ApplianceDetails expression = new ApplianceDetails();
        JSONArray array = appliancesService.getApplianceData(expression);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Data",array).toMap().toString();
//        System.out.println(expected);
        webClient.post().uri("/get-all-appliances")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{}")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String actualJson = response.getResponseBody();
                    JSONObject actualJsonObject = new JSONObject(actualJson);
                    String actual = actualJsonObject.put("Data", actualJsonObject.getJSONArray("Data")).toMap().toString();
//                    System.out.println("actual: " + actual);
                    // Compare the expected and actual JSON arrays using JsonPath
                    assertThat(actual).isEqualTo(expected);
                });
        System.out.println("Test Case Passed Successfully");
    }

}
