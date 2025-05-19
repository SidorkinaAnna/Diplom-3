package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dto.RequestDTO;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    static {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Step("POST request create new user with data: {request}")
    public static Response registration(RequestDTO requestDTO){
        return given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/api/auth/register");
    }
    @Step("Delete user with token {token}")
    public static Response deleteUser(String token){
        return given()
                .header("Authorization", token)
                .when()
                .delete("/api/auth/user");
    }
    @Step("Login user with data {request}")
    public static Response authorization(RequestDTO requestDTO){
        return given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/api/auth/login");
    }
}
