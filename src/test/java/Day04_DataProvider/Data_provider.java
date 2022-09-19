package Day04_DataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;


import static io.restassured.RestAssured.*;

public class Data_provider {


    @Test(dataProvider = "BookData")
    public void addBook(String isbnv, String aislev) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response =
                given().
                        header("Content-Type", "application/json").
                        body(AddLibraryPayload.AddLibrary(isbnv, aislev)).
                when().
                        post("/Library/Addbook.php").
                then().
                        log().all().
                        assertThat().
                        statusCode(200).
                        extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(response);

        String bookId = js.get("ID");

        System.out.println("Book ID From response is:- "+bookId);
        System.out.println("===========================================");

                given().
                        body(AddLibraryPayload.DeleteBookD(bookId)).
                when().
                        delete("/Library/DeleteBook.php").
                then().
                        log().all().
                        assertThat().
                        statusCode(200);

        System.out.println("Completed");
        System.out.println("===========================================");

    }

    @DataProvider(name = "BookData")
    public Object[][] getData() {
        Object[][] x = { { "testing6", "100006" }, { "testing7", "100007" }, { "testing8", "100008" } };
        return x;
    }

}
