package org.example.tests;

import org.example.helpers.LocalStorage;
import io.restassured.response.Response;
import org.example.dto.RequestDTO;
import org.example.page_objects.LoginPage;
import org.example.page_objects.MainPage;
import org.example.page_objects.RecoveryPasswordPage;
import org.example.page_objects.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.steps.ApiSteps;

import static org.junit.Assert.*;

public class AuthenticationTest extends BaseTest{

    private String accessToken;
    private String email;
    private String password;

    @Before
    public void beforeEach() {
        String name = "Eva" + System.currentTimeMillis();
        email = name + "@kot.ru";
        password = "123456";
        ApiSteps.registration(new RequestDTO(email, password, name));
        Response authorization = ApiSteps.authorization(new RequestDTO(email, password));
        accessToken = authorization.jsonPath().getString("accessToken");

        super.beforeEach();
    }

    @Test
    public void successfulLogin_FromMainPage_ClickAccount(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        LoginPage loginPage = new MainPage(driver)
                .clickAccountIfNoAuthenticated();

        testSuccessfulLogin(loginPage);
    }

    @Test
    public void successfulLogin_FromMainPage_ClickEnterAccountButton(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        LoginPage loginPage = new MainPage(driver)
                .clickEnterAccountButton();

        testSuccessfulLogin(loginPage);
    }

    @Test
    public void successfulLogin_FromRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
        LoginPage loginPage = new RegistrationPage(driver)
                .clickEnterAccount();

        testSuccessfulLogin(loginPage);
    }

    @Test
    public void successfulLogin_FromPasswordRecoveryPage(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        LoginPage loginPage = new RecoveryPasswordPage(driver)
                .clickEnterAccount();

        testSuccessfulLogin(loginPage);
    }

    private void testSuccessfulLogin(LoginPage loginPage) {
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());

        String accessTokenLocalStorage = LocalStorage.getLocalStorageItem(driver, "accessToken");
        assertNotNull("Токен должен быть записан в localStorage", accessTokenLocalStorage);
        assertFalse("Токен должен быть не пуст", accessTokenLocalStorage.isBlank());
    }

    @After
    public void after() {
        super.after();
        ApiSteps.deleteUser(accessToken);
    }
}
