package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Resources.APIResource;
import Resources.TestDataBuild;
import Resources.Utils;
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

public class stepDefination extends Utils {
//	RequestSpecification RequestSpec;
	ResponseSpecification ResponseSpec;
	RequestSpecification req;
	Response reqResponse;
	static String placeId;
	TestDataBuild data= new TestDataBuild();
	@Given("AddPlace Payload is available with {string} {string} {string}")
	public void add_place_payload_is_available_with(String name, String language, String address) throws IOException {
//		RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		req = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}

	@When("{string} api is called with {string} http request")
	public void api_is_called_with_post_http_request(String resource,String httpMethod) {
		// Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource which you pass
		APIResource resourceAPI= APIResource.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		ResponseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
		if(httpMethod.equalsIgnoreCase("post"))
		reqResponse= req
				.when().post(resourceAPI.getResource())
				.then().spec(ResponseSpec)
				.extract().response();
				
		else if(httpMethod.equalsIgnoreCase("get"))		
		reqResponse= req
		.when().get(resourceAPI.getResource())
		.then().spec(ResponseSpec)
		.extract().response();
	}

	@Then("Api call success with statuscode {int}")
	public void api_call_success_with_statuscode(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(reqResponse.getStatusCode(), int1);;	
		
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(reqResponse, keyValue), expectedValue);
	}
	@Then("{string} is called and check created place id maps to the {string}")
	public void is_called_and_check_created_place_id_maps_to_the(String resource, String nameExpected) throws IOException {
	
		placeId= getJsonPath(reqResponse,"place_id");
		req= given().spec(requestSpecification()).queryParam("place_id", placeId);
		api_is_called_with_post_http_request(resource,"get");
		String nameActual= getJsonPath(reqResponse, "name");
		System.out.println(nameActual);
		assertEquals(nameExpected,nameActual);
	}
	@Given("Delete Place API Payload is available")
	public void delete_place_api_payload_is_available() throws IOException {
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

}
