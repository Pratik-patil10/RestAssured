package Day05_Deserialization_POJO;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _GetPlacePOJO {

    @Test
    public void verifyGetPlacePojo() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        _GetPlace gp =
                given().
                        queryParam("key", "qaclick123").
                        queryParam("place_id", "4eb7d94884b8786e222de3b79fa03c3f").
                        expect().defaultParser(Parser.JSON).
                when().
                        get("/maps/api/place/get/json").as(_GetPlace.class);

        System.out.println("Accuracy from response: "+gp.getAccuracy());
        System.out.println("latitude from response: "+gp.getLocation().getLatitude());
        System.out.println("Longitude from response: "+gp.getLocation().getLongitude());
        System.out.println("Address from response: "+gp.getAddress());
        System.out.println("====================================================");

        System.out.println("All Test Completed!");

    }

}
