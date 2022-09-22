package Day06_Serialization_POJO.Day05_Deserialization_POJO;

import Day05_Deserialization_POJO._GetPlace;
import _Files_Common.AllPlacePayload;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlacePOJO {
    @Test
    public void verifyAddPlacePOJO(){

        //Below is the method which tells the implementations of serialiazation of payload using POJO

//Location is object so new to create obj of the class and hence using that need to add the values.
        Location loc=new Location();
        loc.setLatitude(-38.383494);
        loc.setLongitude(33.427362);

//As similar to location need to create the obj of AddPlace
        AddPlace ap=new AddPlace();

        ap.setLocation(loc);        //set the location
        ap.setAccuracy(50);
        ap.setName("Frontline house");
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setAddress("29, side layout, cohen 09");

//As the type is array in payload we need to create a List to store the value in array.
//Array or Collection any one is fine but prefer Collection as it is dyanamic
        List<String> typesList=new ArrayList<String>();
        typesList.add("shoe park");
        typesList.add("shop");

        ap.setTypes(typesList);

        ap.setWebsite("http://google.com");
        ap.setLanguage("French-IN");

//After setting all the payload data now heat post request

        RestAssured.baseURI="https://rahulshettyacademy.com";

        given().
                log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(ap).
        when().
                post("/maps/api/place/add/json").
        then().
                assertThat().
                statusCode(200).log().all();

        System.out.println("Serialization Acheived Successfully, ====== Payload Posted !");


        //now using the obj name of getplace from Day05 we can fetch anything from the response as per below.
//that is deserialization

        _GetPlace gp =
                given().
                        queryParam("key", "qaclick123").
                        queryParam("place_id", "4eb7d94884b8786e222de3b79fa03c3f").
                        expect().defaultParser(Parser.JSON).
                        when().
                        get("/maps/api/place/get/json").as(_GetPlace.class);

        System.out.println("Below are some of response from request body fetched by using De-Serialization");

        System.out.println("====================================================");
        System.out.println("Accuracy from response: "+gp.getAccuracy());
        System.out.println("====================================================");
        System.out.println("latitude from response: "+gp.getLocation().getLatitude());
        System.out.println("====================================================");
        System.out.println("Longitude from response: "+gp.getLocation().getLongitude());
        System.out.println("====================================================");
        System.out.println("Address from response: "+gp.getAddress());
        System.out.println("====================================================");

        System.out.println("De-Serialization Acheived Successfully==All Test Completed!");

    }
}
