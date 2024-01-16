package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
}

    //1. Verify if the total record is 25
    @Test
    public void Test001() {
        response.body("size()", equalTo(25));
    }

    //2. Verify if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void Test002() {
        response.body("[2].title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void Test003() {
        response.body("user_id", hasItem(5914249));
    }

    //4. Check the multiple ids in the ArrayList (5914251, 5914251, 5914251)
    @Test
    public void Test004() {

        response.body("user_id", hasItems(5914251, 5914251, 5914251));
    }
    //5. Verify the body of userid = 5914254 is equal “Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”

    @Test
    public void Test005(){
        response.body("[0].body", equalTo("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
    }
}