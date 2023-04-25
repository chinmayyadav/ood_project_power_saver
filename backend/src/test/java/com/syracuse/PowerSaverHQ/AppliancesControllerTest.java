package com.syracuse.PowerSaverHQ;

import com.jayway.jsonpath.JsonPath;
import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.services.AppliancesService;
import net.datafaker.Faker;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AppliancesControllerTest {
    private static final Faker faker = new Faker();
    static Random random = new Random();

    @Autowired
    AppliancesService appliancesService;
    private WebTestClient webClient;
    public AppliancesControllerTest(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }

    @RepeatedTest(50)
    public void AddHomeApplianceTest(){
        String expression = getExpressionForAddHomeApplianceTest();
        webClient.post()
                .uri("/add-home-appliance")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expression)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.Status").isEqualTo("Success");

        System.out.println("Test Case Passed Successfully");

    }

    @RepeatedTest(50)
    public void getApplianceTest(){
        ApplianceDetails expression = new ApplianceDetails();
        JSONArray array = appliancesService.getApplianceData(expression);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Data",array).toMap().toString();
        System.out.println("Expected: " + expected);
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
                    System.out.println("Actual: " + actual);
                    // Compare the expected and actual JSON arrays using JsonPath
                    assertThat(actual).isEqualTo(expected);
                });
        System.out.println("Test Case Passed Successfully");
    }

    @RepeatedTest(50)
    public void getUserApplianceTest(){
        int userID = random.nextInt(200)+1;
        String expression = String.format("{\"userID\": \"%d\"}", userID);
        UserDetails internalExpression = new UserDetails();
        internalExpression.setUserID(userID);
        JSONArray array = appliancesService.getUserApplianceData(internalExpression);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Data",array).toMap().toString();
        System.out.println(expected);
        webClient.post().uri("/get-appliances-per-user")
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
    public void getAddressApplianceTest(){
        int addID = random.nextInt(200)+1;
        String expression = String.format("{\"addressID\": \"%d\"}", addID);
        UserDetails internalExpression = new UserDetails();
        internalExpression.setAddressID(addID);
        JSONArray array = appliancesService.getAddressApplianceData(internalExpression);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Data",array).toMap().toString();
        System.out.println("Expected: " + expected);
        webClient.post().uri("/get-appliances-per-address")
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
    @Test
    public void deleteApplianceTest(){
        int aam = random.nextInt(200)+1;
        System.out.println(aam);
        String expression = String.format("{\"addressApplianceMappingID\": \"%d\"}", aam);
        ApplianceDetails internalExpression = new ApplianceDetails();
        internalExpression.setAddressApplianceMappingID(aam);
        String array = appliancesService.deleteAppliance(internalExpression);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Data",array).toString();
        System.out.println("Expected: " + expected);
        webClient.post().uri("/delete-appliances-for-address")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expression)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String actualJson = response.getResponseBody();
                    System.out.println("actual: " + actualJson);
                    // Compare the expected and actual JSON arrays using JsonPath
                    assertThat(actualJson).isEqualTo(expected);
                });
        System.out.println("Test Case Passed Successfully");
    }
    static String getExpressionForAddHomeApplianceTest() {
        int addID = random.nextInt(400) + 1;
        int applianceID = random.nextInt(63) + 1;
        int count = random.nextInt(4) + 1;
        int hours =  random.nextInt(24) + 1;
        int days = random.nextInt(30) + 1;
        return String.format("{\"addID\": \"%d\", \"applianceID\": \"%d\", \"count\": \"%d\", \"hours\": \"%d\", \"days\": \"%d\"}", addID, applianceID, count, hours,days);
    }

}
