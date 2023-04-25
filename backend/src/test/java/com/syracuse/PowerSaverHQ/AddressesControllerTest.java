package com.syracuse.PowerSaverHQ;

import com.syracuse.PowerSaverHQ.models.AddressesDetails;
import com.syracuse.PowerSaverHQ.services.AddressServices;
import com.syracuse.PowerSaverHQ.services.AppliancesService;
import net.datafaker.Faker;
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
public class AddressesControllerTest {
    private static final Faker faker = new Faker();
    static Random random = new Random();

    @Autowired
    AddressServices addressServices;

    private WebTestClient webClient;

    public AddressesControllerTest(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }

    @RepeatedTest(50)
    public void getAddressesUserTest(){
        int userID = random.nextInt(250)+1;
        String expression = String.format("{\"userID\": \"%d\"}", userID);
        AddressesDetails internalExpression = new AddressesDetails();
        internalExpression.setUserID(userID);
        JSONArray array = addressServices.getAddressesUser(internalExpression);
        JSONObject jobj = new JSONObject();
        String expected = jobj.put("Data",array).toMap().toString();
        System.out.println(expression);
        System.out.println("Expected: " + expected);
        webClient.post().uri("/get-addresses-per-user")
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

    @RepeatedTest(50)
    public void getAddressesIDTest(){
        int addID = random.nextInt(250)+1;
        String expression = String.format("{\"addressID\": \"%d\"}", addID);
        AddressesDetails internalExpression = new AddressesDetails();
        internalExpression.setAddressID(addID);
        JSONArray array = addressServices.getAddressesID(internalExpression);
        JSONObject jobj = new JSONObject();
        String expected = jobj.put("Data",array).toMap().toString();
        System.out.println(expression);
        System.out.println("Expected: " + expected);
        webClient.post().uri("/get-addresses-by-id")
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
