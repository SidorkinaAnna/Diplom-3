package org.example.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MainPage {
    private WebDriver driver;

    /**
     * кнопка входа в личный кабинет
     */
    private By privateAccount = By.xpath("//a[@href=\"/account\"]");
    private By loginButton = By.xpath("//button[text()=\"Войти в аккаунт\"]");
    private String buttonSectionSelector = "//span[text() = '%s']";
    private String titleSectionSelector = "//h2[text() = '%s']";
    private By currentSectionTitle = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку профиля")
    public PersonalAccountPage clickAccountIfAuthenticated(){
        WebElement element = driver.findElement(privateAccount);
        element.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(urlContains("/account/profile"));
        assertTrue("Profile page by clicking account button we can reach only when user is authenticated",
                driver.getCurrentUrl().contains("/account/profile"));
        return new PersonalAccountPage(driver);
    }

    @Step("Нажимаем на кнопку профиля")
    public LoginPage clickAccountIfNoAuthenticated(){
        WebElement element = driver.findElement(privateAccount);
        element.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(urlContains("/login"));
        assertTrue("Login page by clicking account button we can reach only when user is not authenticated",
                driver.getCurrentUrl().contains("/login"));
        return new LoginPage(driver);
    }

    @Step("Нажимаем на кнопку Войти в аккаунт")
    public LoginPage clickEnterAccountButton(){
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new LoginPage(driver);
    }

    @Step("Переход в секцию {name}")
    public void clickSection(String name) {
        String selector = String.format(buttonSectionSelector, name);
        WebElement element = driver.findElement(By.xpath(selector));
        element.click();
    }

    @Step("Проверяем, что кнопка выбора секции {sectionName} выбрана")
    public boolean sectionTitleIsSelected(String sectionName) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.textToBe(currentSectionTitle, sectionName));
        return Objects.equals(driver.findElement(currentSectionTitle).getText(), sectionName);
    }

    @Step("Проверяем, что видна секция - {sectionName}")
    public boolean sectionIsVisibleCheck(String sectionName) {
        String selector = String.format(titleSectionSelector, sectionName);
        WebElement element = driver.findElement(By.xpath(selector));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
}
