package Day02_APIChaining;

import _Files_Common.AllPlacePayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;


public class API_Chaining {


    public static String placeId;
    public static String newAddress = "702 winter walk, USA";

    @Test
    public void verifyAPI() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // hit the post request

        String response =
        given().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(AllPlacePayload.AddPlace()).
        when().
                post("/maps/api/place/add/json").
        then().
                assertThat().statusCode(200).
                body("scope", equalTo("APP")).
                header("Server", "Apache/2.4.41 (Ubuntu)").
                extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);

        // extract place id
        placeId = js.getString("place_id");

        System.out.println("Place Id from response is:- "+placeId);
        System.out.println("Request POST successfully completed!");
        System.out.println("==========================================================");



        // update api - pass place id in extracted request

        given().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(AllPlacePayload.UpdatePlace()).
        when().
                put("/maps/api/place/update/json").
        then().
                assertThat().
                statusCode(200).
                body("msg", equalTo("Address successfully updated"));

        System.out.println("Request UPDATE successfully completed!");
        System.out.println("==========================================================");

        // get request

        String response1 =
        given().
                queryParam("key", "qaclick123").
                queryParam("place_id",placeId).
        when().
                get("/maps/api/place/get/json").
        then().
                assertThat().log().all().
                statusCode(200).
                extract().response().asString();

        JsonPath js1 = new JsonPath(response1);

        String address = js1.getString("address");

        System.out.println("Address from response is:- "+address);

        Assert.assertEquals(address,newAddress);//this will check whether address changed or not
        System.out.println("Request GET successfully completed!");
        System.out.println("==========================================================");


        // delete

        given().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json")
                .body(AllPlacePayload.deleteAPIPayload()).
        when().
                delete("/maps/api/place/delete/json").
        then().
                assertThat().log().all().
                statusCode(200).
                body("status", equalTo("OK"));

        System.out.println("Request DELETE successfully completed!");
        System.out.println("==========================================================");

        System.out.println("CRUD Test Passed Successful]y!");


    }

}





