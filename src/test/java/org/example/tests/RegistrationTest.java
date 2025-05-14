package org.example.tests;

import io.restassured.response.Response;
import org.example.dto.RequestDTO;
import org.example.page_objects.MainPage;
import org.example.page_objects.RegistrationPage;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.steps.ApiSteps;

import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest{

    private String accessToken;

    @Test
    public void successfulRegistration(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        RegistrationPage registrationPage = new MainPage(driver)
                .clickAccountIfNoAuthenticated()
                .clickRegistration();

        String name = "Eva" + System.currentTimeMillis();
        String email = name + "@kot.ru";
        String password = "123456";
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);

        registrationPage.clickRegister();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("/login"));
        assertEquals("https://stellarburgers.nomoreparties.site/login", driver.getCurrentUrl());

        Response authorization = ApiSteps.authorization(new RequestDTO(email, password));
        accessToken = authorization.jsonPath().getString("accessToken");
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    public void unsuccessfulRegistration_ShortPassword(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        RegistrationPage registrationPage = new MainPage(driver)
                .clickAccountIfNoAuthenticated()
                .clickRegistration();

        String password = "12345";
        registrationPage.setPassword(password);
        registrationPage.clickRegister();
        assertTrue(registrationPage.checkWrongPasswordAlertIs());
    }

    @Test
    public void successRegistration_PasswordEnoughLength(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        RegistrationPage registrationPage = new MainPage(driver)
                .clickAccountIfNoAuthenticated()
                .clickRegistration();

        String password = "123456";
        registrationPage.setPassword(password);
        registrationPage.clickRegister();
        assertFalse(registrationPage.checkWrongPasswordAlertIs());

    }


}
