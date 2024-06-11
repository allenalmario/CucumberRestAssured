package stepDefinitions;

import endpoints.APIResources;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import testData.TestDataBuild;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PlaceStepDefinitions extends Utils {

    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(testDataBuild.addPlacePayload(name, language, address));
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
    }

    @Then("the API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @And("{string} in response is {string}")
    public void in_response_is(String keyValue, String exoectedValue) {
       Assert.assertEquals(getJsonPath(response, keyValue), exoectedValue);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // requestSpec
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }
}
