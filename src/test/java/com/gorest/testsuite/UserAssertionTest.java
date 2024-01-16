package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
}

    //1. Verify if the total record is 20
    @Test
    public void Test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify if the name of id = 5914155 is equal to ”Bhishma Joshi”
    @Test
    public void Test002() {
        response.body("[0].name",equalTo("Bhishma Joshi"));
    }

    //3. Check the single ‘Name’ in the Array list (Dandapaani Agarwal)
    @Test
    public void Test003() {
        response.body("[1].name",equalTo("Dandapaani Agarwal"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Anshula Joshi, Somu Pillai, Avantika Kaur)
    @Test
    public void Test004() {
        response.body("name", hasItems("Anshula Joshi","Somu Pillai","Avantika Kaur"));
    }

    //5. Verify the email of userid = 5914150 is equal “avantika_kaur@welch.test”
    @Test
    public void Test005() {
        response.body("[5].email", equalTo("avantika_kaur@welch.test"));
    }

    //6. Verify the status is “Active” of user name is “Tanirika Ahluwalia Sr.”
    @Test
    public void Test006() {
        response.body("[7].status", equalTo("active"));
    }

    //7. Verify the Gender = male of user name is “Somu Pillai”
    @Test
    public void Test007() {
        response.body("[4].gender", equalTo("male"));
    }
}