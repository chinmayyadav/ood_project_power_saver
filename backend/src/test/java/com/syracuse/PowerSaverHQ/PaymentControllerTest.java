package com.syracuse.PowerSaverHQ;

import com.syracuse.PowerSaverHQ.models.PaymentDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.PaymentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PaymentControllerTest {
    static Random random = new Random();
    private int userID;

    @Autowired
    PaymentService paymentService;

    private WebTestClient webClient;

    public PaymentControllerTest(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }

    @RepeatedTest(50)
    public void getPaymentTest(){
        int userID = random.nextInt(250)+1;
        String expression = String.format("{\"userID\": \"%d\"}", userID);
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setUserID(userID);
        JSONArray array = paymentService.getCardNumber(paymentDetails);
        JSONObject jobj = new JSONObject();
        String expected = jobj.put("Data",array).toMap().toString();
        System.out.println(expression);
        System.out.println("Expected: " + expected);
        webClient.post().uri("/get-payment-details")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expression)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String actualJson = response.getResponseBody();
                    JSONObject actualJsonObject = new JSONObject(actualJson);
                    String actual = actualJsonObject.put("Data", actualJsonObject.getJSONArray("Data")).toMap().toString();
                    System.out.println("Actual: " + actual);
                    // Compare the expected and actual JSON arrays using JsonPath
                    assertThat(actual).isEqualTo(expected);
                });
        System.out.println("Test Case Passed Successfully");
    }


}
