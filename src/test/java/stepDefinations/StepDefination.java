package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	String res;
	static String place_Id;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		requestSpec = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Constructor will be called with the value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = requestSpec.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = requestSpec.when().get(resourceAPI.getResource());
		else
			response = requestSpec.when().put(resourceAPI.getResource());
	}

	@Then("the API call got susscee with status code {int}")
	public void the_api_call_got_susscee_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expvalue) {
		assertEquals(getValueFromResponse(response, key), Expvalue);

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
		 place_Id = getValueFromResponse(response, "place_id");
		 requestSpec = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		 user_calls_with_http_request(resource, "GET");
		 String actName = getValueFromResponse(response, "name");
		 assertEquals(expName, actName);
	}
	
	@Given("Delete place PayLoad")
	public void delete_place_pay_load() throws IOException {
		requestSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	}
	
}
