package org.example.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage {
    private WebDriver driver;

    private By nameSelector = By.xpath("//label[text()=\"Имя\"]/following-sibling::input");
    private By emailSelector = By.xpath("//label[text()=\"Email\"]/following-sibling::input");
    private By passwordSelector = By.xpath("//label[text()=\"Пароль\"]/following-sibling::input");
    private By buttonRegistrationSelector = By.xpath("//button[text()=\"Зарегистрироваться\"]");
    private By uncorrectPasswordAlert = By.xpath("//p[text() = 'Некорректный пароль']");
    private By buttonEnterAccount = By.xpath("//a[@href=\"/login\"]");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя - {name}")
    public void setName(String name){
        WebElement element = driver.findElement(nameSelector);
        element.click();
        element.sendKeys(name);
    }

    @Step("Ввести email - {email}")
    public void setEmail(String email){
        WebElement element = driver.findElement(emailSelector);
        element.click();
        element.sendKeys(email);
    }

    @Step("Ввести пароль - {password}")
    public void setPassword(String password){
        WebElement element = driver.findElement(passwordSelector);
        element.click();
        element.sendKeys(password);
    }

    @Step("Нажать регистрация")
    public void clickRegister(){
        WebElement element = driver.findElement(buttonRegistrationSelector);
        element.click();
    }

    @Step("Проверка на появление надписи \"Некорректный пароль\"")
    public boolean checkWrongPasswordAlertIs(){
        List<WebElement> elements = driver.findElements(uncorrectPasswordAlert);
        return !elements.isEmpty();
    }

    @Step("Нажать Войти")
    public LoginPage clickEnterAccount(){
        WebElement element = driver.findElement(buttonEnterAccount);
        element.click();
        return new LoginPage(driver);
    }


}
