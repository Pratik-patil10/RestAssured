package Day04_DataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJsonDemo {

    public static String bookId;

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response =
                given().
                        header("Content-Type","application/json").
                        body(AddLibraryPayload.AddLibrary("testing1","100000")).
                when().
                        post("/Library/Addbook.php").
                then().
                        assertThat().
                        statusCode(200).
                        extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(response);

        bookId = js.get("ID");
        System.out.println(bookId);


                given().
                        body(AddLibraryPayload.DeleteBook()).
                when().
                        delete("/Library/DeleteBook.php").
                then().
                        log().all().
                        assertThat().
                        statusCode(200);

        System.out.println("Completed");

    }
}
