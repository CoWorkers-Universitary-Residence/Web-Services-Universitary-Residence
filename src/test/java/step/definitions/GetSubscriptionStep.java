package step.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetSubscriptionStep {

    private final String BASE_URL = "http://localhost:8090/api/v1";

    private RequestSpecification requestSpecification;
    private Response response;

    private int id;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("the endpoint subscriptions is available")
    public void theEndpointSubscriptionsIsAvailable() {
        RestAssured.baseURI = BASE_URL;
        requestSpecification = given();
    }

    @And("A subscription is already saved")
    public void aSubscriptionIsAlreadySaved() {
        HashMap subscription = new HashMap();
        subscription.put("startDate", "2022-09-28");
        subscription.put("finishDate", "2022-10-28");
        subscription.put("planId", 1);

        id =
                given()
                        .contentType("application/json")
                        .body(subscription)
                .when()
                        .post("http://localhost:8090/api/v1/subscriptions")
                .then()
                        .extract()
                        .path("id");
    }

    @Given("Call to {string}")
    public void callTo(String url) throws URISyntaxException {
        response = requestSpecification.when().get(new URI(url));
    }

    @Then("Get a response with {string}")
    public void getResponseWith(String statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        String body = response.then().extract().body().asString();
        Assert.assertEquals(statusCode, actualResponseCode + "");
    }

    @Given("Call to the endpoint by Id")
    public void callToTheEndpointById() throws URISyntaxException {
        String url = BASE_URL + "/subscriptions/" + id;
        response = requestSpecification.when().get(new URI(url));
    }

    @Then("Get a code response with {string}")
    public void getCodeResponseWith(String statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        String body = response.then().extract().body().asString();
        Assert.assertEquals(statusCode, actualResponseCode + "");
    }

    @And("Response in the body with {string}, {string}, {string} exists")
    public void responseInTheBodyExists(String startDate, String finishDate, String planId) {
        String responseStartDate = response.then().extract().path("startDate");
        String responseFinishDate = response.then().extract().path("finishDate");
        int responsePlanId = response.then().extract().path("planId");

        Assert.assertEquals(responseStartDate, startDate);
        Assert.assertEquals(responseFinishDate, finishDate);
        Assert.assertEquals(responsePlanId + "", planId);
    }
}