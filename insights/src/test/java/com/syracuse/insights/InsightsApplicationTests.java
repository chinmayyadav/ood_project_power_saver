package com.syracuse.insights;

import com.syracuse.insights.models.InsightsModel;
import com.syracuse.insights.services.InsightsModelService;
import com.syracuse.insights.utils.Constants;
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

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class InsightsApplicationTests {
	static Random random = new Random();
	private int userID;

	@Autowired
	InsightsModelService insightsModelService;

	private WebTestClient webClient;

	public InsightsApplicationTests(){
		this.webClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+8082).build();
	}

	@RepeatedTest(150)
	public void compareNeighbourBills(){
		int userID = random.nextInt(250)+1;
		String expression = String.format("{\"userID\": \"%d\"}", userID);
		InsightsModel insightsModel = new InsightsModel();
		insightsModel.setUserID(userID);
		JSONArray currentUser = insightsModelService.currenUserData(insightsModel);
		JSONArray neighbours = insightsModelService.neighbourData(insightsModel);
		JSONObject jsObj = new JSONObject();
		if(!currentUser.isEmpty() && !neighbours.isEmpty()) {
			jsObj.put("Data", Constants.STATUS_SUCCESS).toMap();
			jsObj.put("CurrentUser", currentUser).toMap();
			jsObj.put("Neighbours", neighbours).toMap();
		}else {
			jsObj.put("Data", Constants.STATUS_ERROR).toMap().toString();
		}
		String expected = jsObj.toString();
		System.out.println("Expected: "+ expected);
		webClient.post().uri("/compare-with-neighbours-energy")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(expression)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.consumeWith(response -> {
					String actualJson = response.getResponseBody();
					System.out.println("Actual: " + actualJson);
					assertThat(actualJson).isEqualTo(expected);
				});
	}
	@RepeatedTest(50)
	public void compareNeighbourAppliances(){
		int userID = random.nextInt(250)+1;
		String expression = String.format("{\"userID\": \"%d\"}", userID);
		InsightsModel insightsModel = new InsightsModel();
		insightsModel.setUserID(userID);
		JSONObject jsObj = new JSONObject();
		JSONArray data = insightsModelService.compareAppliances(insightsModel);
		if(!data.isEmpty()) {
			jsObj.put("Data", Constants.STATUS_SUCCESS);
			jsObj.put("Appliances", data);
		}else {
			jsObj.put("Data", Constants.STATUS_ERROR);
		}
		String expected = jsObj.toString();
		System.out.println("Expected: "+ expected);
		webClient.post().uri("/compare-with-neighbours-appliances")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(expression)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.consumeWith(response -> {
					String actualJson = response.getResponseBody();
					System.out.println("Actual: " + actualJson);
					assertThat(actualJson).isEqualTo(expected);
				});
	}



}
