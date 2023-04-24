package com.syracuse.PowerSaverHQ;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserTests {
    private WebTestClient webClient;
    public UserTests(){
        this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8080).build();
    }
    @Test
    void registerUserTest() {
        String expression = getExpression();

        webClient.post()
                .uri("/register-user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expression)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.Result").isEqualTo("Success");
    }
    private static final Faker faker = new Faker();

    static String getExpression() {
        return faker.expression(
                "{\"firstName\": \"#{Name.first_name}\", \"lastName\": \"#{Name.last_name}\"," +
                        "\"email\": \"#{Internet.emailAddress}\", \"password\": \"#{Internet.password}\"}"
        );
    }
}











    @RequestMapping(value = "/save-address-for-user",headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> saveAddress(@RequestBody AddressesDetails addressesDetails) {
        JSONObject jsObj = new JSONObject();
        jsObj.put("Status", addressServices.saveAddressUser(addressesDetails));
        return new ResponseEntity<>(jsObj.toMap(), HttpStatus.OK);