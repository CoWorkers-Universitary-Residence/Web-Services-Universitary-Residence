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

public class GetPublicationStep {

    private final String BASE_URL = "http://localhost:8090/api/v1";
    
    private RequestSpecification requestSpecification;
    private Response response;

    private int id;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("the endpoint publications is available")
    public void theEndpointPublicationsIsAvailable() {
        RestAssured.baseURI = BASE_URL;
        requestSpecification = given();
    }
    
    @And("A publication is already stored")
    public void aPublicationIsAlreadyStored() {
        HashMap publication = new HashMap();
        publication.put("about", "This is a new publication");
        publication.put("price", 2500);
        publication.put("escrow", 1000);
        publication.put("extra_expenses", "No required");
        publication.put("gender", "Women");
        publication.put("availability", true);
        publication.put("rooms", 3);
        publication.put("bathrooms", "3 bathrooms");
        publication.put("width", 15);
        publication.put("height", 24);
        publication.put("country", "Perú");
        publication.put("city", "Lima");
        publication.put("address", "Calle Nueva Democracia");
        publication.put("visit", true);
        publication.put("views", 0);

        id =
        given()
                .contentType("application/json")
                .body(publication)
        .when()
                .post("http://localhost:8090/api/v1/publications")
                .then()
                .extract().path("id");
    }

    @Given("Get call to {string}")
    public void getCallTo(String url) throws URISyntaxException {
        response = requestSpecification.when().get(new URI(url));
    }

    @Then("Response is {string}")
    public void responseIs(String statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        String body = response.then().extract().body().asString();
        Assert.assertEquals(statusCode, actualResponseCode + "");
    }

    @Given("Get call to endpoint by Id")
    public void getCallToEndpointById() throws URISyntaxException {
        String url = BASE_URL + "/publications/" + id;
        response = requestSpecification.when().get(new URI(url));
    }

    @Then("Status code response is {string}")
    public void statusCodeResponseIs(String statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        String body = response.then().extract().body().asString();
        Assert.assertEquals(statusCode, actualResponseCode + "");
    }

    @And("Response with {string}, {string}, {string}, {string} exists")
    public void responseWithExists(String width, String height, String rooms, String bathrooms) {
        float responseWidth = response.then().extract().path("width");
        float responseHeight = response.then().extract().path("height");
        int responseRooms = response.then().extract().path("rooms");
        String responseBathrooms = response.then().extract().path("bathrooms");

        Assert.assertEquals(responseWidth + "", width);
        Assert.assertEquals(responseHeight + "", height);
        Assert.assertEquals(responseRooms + "", rooms);
        Assert.assertEquals(responseBathrooms, bathrooms);
    }
}