package org.example.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private By registrationButton = By.xpath("//a[@href=\"/register\"]");
    private By emailSelector = By.xpath("//label[text()=\"Email\"]/following-sibling::input");
    private By passwordSelector = By.xpath("//label[text()=\"Пароль\"]/following-sibling::input");
    private By loginButton = By.xpath("//button[text()=\"Войти\"]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку регистрации")
    public RegistrationPage clickRegistration(){
        WebElement element = driver.findElement(registrationButton);
        element.click();
        return new RegistrationPage(driver);
    }

    @Step("Ввести email - {email}")
    public void setEmail(String email){
        WebElement element = driver.findElement(emailSelector);
        element.click();
        element.sendKeys(email);
    }

    @Step("Ввести Password - {Password}")
    public void setPassword(String password){
        WebElement element = driver.findElement(passwordSelector);
        element.click();
        element.sendKeys(password);
    }

    @Step("Нажать Войти")
    public void clickLogin(){
        WebElement element = driver.findElement(loginButton);
        element.click();
    }
}
