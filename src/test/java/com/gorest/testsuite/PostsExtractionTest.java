package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
}

    //1. Extract the title
    @Test
    public void test001() {
        List<Integer> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of  all title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        int record = response.extract().path("size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of record is : " + record);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[15].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<?> userId = response.extract().path("user_id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records is : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<?> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5914254
    @Test
    public void test006() {
        List<?> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id is 5914254 : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 94000
    @Test
    public void test007() {
        List<?> body = response.extract().path("body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id is 94000 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}