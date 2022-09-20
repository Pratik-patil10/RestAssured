package Day05_Deserialization_POJO;

import _Files_Common.AllPlacePayload;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddPlacePOJO {


    @Test
    public void verifyAddPlacePOJO(){

        RestAssured.baseURI="https://rahulshettyacademy.com";

        //below lines mean creat a obj of add place class and fetch the respose as per below mentioned
        AddPlace ap=
        given().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(AllPlacePayload.AddPlace()).
                expect().defaultParser(Parser.JSON).    //this means fetch the response in json format
        when().
                post("/maps/api/place/add/json").
                as(AddPlace.class);  //this mean fetch the response per specified class.


        //now using the obj name we can fetch anythinf from the response as per below.

        System.out.println("PlaceID from response is:- "+ap.getPlace_id());
        System.out.println("ID from response is:- "+ap.getId());
        System.out.println("ReferenceID from response is:- "+ap.getReference());
        System.out.println("ScopeID from response is:- "+ap.getScope());;
        System.out.println("Status from response is:- "+ap.getStatus());;



    }
}
