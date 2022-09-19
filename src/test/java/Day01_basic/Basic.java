package Day01_basic;

import _Files_Common.AllPlacePayload;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {

        @Test
        public void verify(){


        RestAssured.baseURI="https://rahulshettyacademy.com";

        given().
                queryParam("key", "qaclick123").
                header("Content-Type","application/json")
                .body(AllPlacePayload.AddPlace()).
        when().
                post("/maps/api/place/add/json").
        then().
                log().all().
                assertThat().statusCode(200).
                body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)");
    }
}
