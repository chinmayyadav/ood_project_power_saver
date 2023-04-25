package com.syracuse.PowerSaverHQ;

import com.syracuse.PowerSaverHQ.models.NotificationPreferance;
import com.syracuse.PowerSaverHQ.services.NotificationServices;
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
public class NotificationControllerTest {
    private int addID;
    private boolean email, phone, service;
    static Random random = new Random();

    @Autowired
    NotificationServices notificationServices;

    private WebTestClient webClient;

    public NotificationControllerTest(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }

    @RepeatedTest(5)
    public void saveNotificationPreferenceTest(){
        addID = random.nextInt(250)+1;
        email = random.nextBoolean();
        phone = random.nextBoolean();
        service = random.nextBoolean();
        String expression = String.format("{\"addressID\": \"%d\",\"emailNotification\":\"%b\",\"phoneNotification\":\"%b\",\"servicePhoneCalls\":\"%b\"}", addID,email,phone,service);
        NotificationPreferance notificationPreferance = new NotificationPreferance();
        notificationPreferance.setAddressID(addID);
        notificationPreferance.setEmailNotification(email);
        notificationPreferance.setPhoneNotification(phone);
        notificationPreferance.setServicePhoneCalls(service);
        JSONObject jsObj = new JSONObject();
        String expected = jsObj.put("Status", notificationServices.savePreference(notificationPreferance)).toMap().toString();
        System.out.println(expression);
        System.out.println("Expected: " + expected);
        webClient.post()
                .uri("/save-notification-preference")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expression)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.Status").isEqualTo("Success");
        System.out.println("Test Case Passed Successfully");
    }
}
