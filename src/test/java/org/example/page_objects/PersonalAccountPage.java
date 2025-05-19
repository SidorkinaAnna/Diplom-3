package org.example.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PersonalAccountPage {
    private WebDriver driver;

    private By constructor = By.xpath("//p[text() = 'Конструктор']");
    private By logo = By.xpath("//a[@href='/' and not(.//p[text()='Конструктор'])]");
    private By exit = By.xpath("//button[text()=\"Выход\"]");
    private By profileBtn = By.xpath("//a[@href=\"/account/profile\"]");


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку конструктор")
    public MainPage clickConstructor(){
        WebElement element = driver.findElement(constructor);
        element.click();
        return new MainPage(driver);
    }

    @Step("Нажимаем на логотип")
    public MainPage clickLogo(){
        WebElement element = driver.findElement(logo);
        element.click();
        return new MainPage(driver);
    }

    @Step("Нажимаем на кнопку выход")
    public LoginPage clickExit(){
        WebElement element = driver.findElement(exit);
        element.click();
        return new LoginPage(driver);
    }

    @Step("Нажимаем на кнопку выход")
    public boolean existButtonProfile(){
        List<WebElement> elements = driver.findElements(profileBtn);
        return !elements.isEmpty();
    }




}
