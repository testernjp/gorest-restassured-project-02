package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

public class UserCRUDTest extends TestBase {


    static String name = TestUtils.getRandomValue() + "Newtester";
    static String email = TestUtils.getRandomValue() + "abc@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int id;


    @Test
    public void aVerifyUserCreateSuccessfully() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(name);
        postsPojo.setEmail(email);
        postsPojo.setGender(gender);
        postsPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .body(postsPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void bVerifyUserCreatedSuccessfully() {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then().statusCode(200);
        HashMap<String, Object> studentMap = response.extract()
                .path(s1 + name + s2);
        response.body(s1 + name + s2, hasValue(name));
        id = (int) studentMap.get("id");


    }
    @Test
    public void cVerifyUserUpdatedSuccessfully() {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(name);
        postsPojo.setEmail(email);
        postsPojo.setGender(gender);
        postsPojo.setStatus("inactive");

        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
//                .header("Content-Type", "application/json")


                .pathParam("id", id)
                .when()
                .body(postsPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void dVerifyUserDeleteSuccessfully(){
        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .delete("/users/{id}");
        response.prettyPrint();
        response.then().statusCode(204);

        given()
                .pathParam("id",id)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(404);

    }
}