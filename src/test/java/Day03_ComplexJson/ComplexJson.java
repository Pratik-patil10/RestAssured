package Day03_ComplexJson;


import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ComplexJson {

    @Test
    public void verifyScenario() {
// 1
        JsonPath js = new JsonPath(ComplexJsonPayload.coursePrice());
        int countOfCourses = js.getInt("courses.size()");

        System.out.println("Total number of courses present in response: " + countOfCourses);

        int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
// 2
        System.out.println("Total courses purchase amount is:" + totalPurchaseAmount);

// 3: Print title of all courses
        String firstCourseTitle = js.get("courses[0].title");
        System.out.println("First course title is: " + firstCourseTitle);

        System.out.println("==============================================");

// 4. Print all courses title and respective price
        for (int i = 0; i < countOfCourses; i++) {
            String title = js.get("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println("Name of course is: " + title + " | price of course is: " + price);
        }

        System.out.println("==============================================");

// 5. Print no of copies for RPA
        for (int i = 0; i < countOfCourses; i++) {
            String title = js.get("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")) {
                int rpaCopiesCount = js.getInt("courses[" + i + "].copies");
                System.out.println("Copies of RPA course is: " + rpaCopiesCount);
                break;
            }
        }

        System.out.println("==============================================");

// 6. Verify if sum of all courses price matches with purchase amount
        int totalamountofcourses = 0;

        for (int i = 0; i < countOfCourses; i++) {
            int price = js.getInt("courses[" + i + "].price");
            int copiesCount = js.getInt("courses[" + i + "].copies");
            int amount = price * copiesCount;
            totalamountofcourses = totalamountofcourses + amount;
        }

        System.out.println("Total Amount is:-"+totalamountofcourses);

        Assert.assertEquals(totalamountofcourses, totalPurchaseAmount);


    }

}





