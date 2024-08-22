package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class UserManagementSteps {

    private Response response;
    private String baseUrl = "https://petstore.swagger.io/v2";
    private String userEndpoint = "/user";
    private JSONObject userJson;

    @Given("I post a user with id {string} and username {string}")
    public void i_post_a_user_with_id_and_username(String id, String username) {
        userJson = new JSONObject();
        userJson.put("id", Integer.parseInt(id));
        userJson.put("username", username);
        userJson.put("firstName", "Test");
        userJson.put("lastName", "User");
        userJson.put("email", "testuser@example.com");
        userJson.put("password", "password");
        userJson.put("phone", "1234567890");
        userJson.put("userStatus", 1);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(userJson);

        response = RestAssured.given()
            .contentType("application/json")
            .body(jsonArray.toString())
            .post(baseUrl + "/user/createWithArray");
    }

    @When("I get the user with username {string}")
    public void i_get_the_user_with_username(String username) {
        response = RestAssured.given()
            .get(baseUrl + userEndpoint + "/" + username);
    }

    @Then("the response should contain user with id {string}")
    public void the_response_should_contain_user_with_id(String expectedId) {
        JSONObject responseJson = new JSONObject(response.getBody().asString());
        Assert.assertEquals(Integer.parseInt(expectedId), responseJson.getInt("id"));
    }

    @Then("the user should have the username {string}")
    public void the_user_should_have_the_username(String expectedUsername) {
        JSONObject responseJson = new JSONObject(response.getBody().asString());
        Assert.assertEquals(expectedUsername, responseJson.getString("username"));
    }

    @Then("the user should have the email {string}")
    public void the_user_should_have_the_email(String expectedEmail) {
        JSONObject responseJson = new JSONObject(response.getBody().asString());
        Assert.assertEquals(expectedEmail, responseJson.getString("email"));
    }
}
