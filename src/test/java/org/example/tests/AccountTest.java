package org.example.tests;

import io.restassured.response.Response;
import org.example.dto.RequestDTO;
import org.example.page_objects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.steps.ApiSteps;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class AccountTest extends BaseTest {

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
    public void successfulTransitionOnPersonalAccount() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        PersonalAccountPage personalAccountPage = new MainPage(driver)
                .clickAccountIfAuthenticated();
        assertTrue(personalAccountPage.existButtonProfile());

    }

    @Test
    public void successfulTransitionToMainPage_ByConstructor() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        PersonalAccountPage personalAccountPage = new MainPage(driver)
                .clickAccountIfAuthenticated();
        personalAccountPage.clickConstructor();
        new WebDriverWait(driver, 10).until(urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());

    }

    @Test
    public void successfulTransitionToMainPage_ByLogo() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        PersonalAccountPage personalAccountPage = new MainPage(driver)
                .clickAccountIfAuthenticated();
        personalAccountPage.clickLogo();
        new WebDriverWait(driver, 10).until(urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());

    }

    @Test
    public void successfulExit() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        PersonalAccountPage personalAccountPage = new MainPage(driver)
                .clickAccountIfAuthenticated();
        personalAccountPage.clickExit();
        new WebDriverWait(driver, 10).until(urlToBe("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("https://stellarburgers.nomoreparties.site/login", driver.getCurrentUrl());

    }


    @After
    public void after() {
        super.after();
        ApiSteps.deleteUser(accessToken);
    }
}
