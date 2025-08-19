package com.v.testingrest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TestingRestControllerApplicationTests {

	@Test
	@DisplayName("User can be created")
	void testCreateUser_whenValidDetailsProvided_createUser() throws JSONException {

		//Arrange
		JSONObject userDetailsRequestJSON = new JSONObject();
		userDetailsRequestJSON.put("firstName","Bruce");
		userDetailsRequestJSON.put("lastName","Wayne");
		userDetailsRequestJSON.put("email","im@Batman");
		userDetailsRequestJSON.put("password","gotham");
		userDetailsRequestJSON.put("repeatPassword","gotham");

	}

}
