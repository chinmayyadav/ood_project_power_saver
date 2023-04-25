package com.syracuse.PowerSaverHQ;

import com.syracuse.PowerSaverHQ.models.BillingDetails;
import com.syracuse.PowerSaverHQ.services.BillingService;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BillingControllerTest {
    private static final Faker faker = new Faker();
    static Random random = new Random();
    private int addID;
    private Date fromDate;
    private Date toDate;

    @Autowired
    BillingService billingService;

    private WebTestClient webClient;

    public BillingControllerTest(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }

    @RepeatedTest(50)
    public void getBillTest(){
        String expression = getBillTestData();
        System.out.println(expression);
        BillingDetails billingDetails = new BillingDetails();
        billingDetails.setAddID(addID);
        billingDetails.setFromDate(fromDate);
        billingDetails.setToDate(toDate);
        JSONArray array = billingService.getBillData(billingDetails);
        JSONObject jobj = new JSONObject();
        String expected = jobj.put("Data",array).toMap().toString();
        System.out.println(expression);
        System.out.println("Expected: " + expected);
        webClient.post().uri("/get-bill")
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

    public String getBillTestData(){
        addID = random.nextInt(250)+1;
        Faker faker = new Faker();
        LocalDateTime startDate = faker.date().past(5000, java.util.concurrent.TimeUnit.DAYS)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = faker.date().past(2000, java.util.concurrent.TimeUnit.DAYS)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // check if startDate is greater than or equal to endDate
        if (startDate.isAfter(endDate)) {
            LocalDateTime temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        String from = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String to = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDate localDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDate localDate1 = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        fromDate = Date.valueOf(localDate);
        toDate = Date.valueOf(localDate1);
        String output = String.format("{\"addID\": \"%d\",\"fromDate\": \"%s\",\"toDate\": \"%s\"}", addID,fromDate,toDate);

        return output;
    }

}
