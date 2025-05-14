package org.example.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecoveryPasswordPage {
    private WebDriver driver;

    private By emailSelector = By.xpath("//label[text()=\"Email\"]/following-sibling::input");
    private By buttonEnterAccount = By.xpath("//a[@href=\"/login\"]");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Ввести email - {email}")
    public void setEmail(String email){
        WebElement element = driver.findElement(emailSelector);
        element.click();
        element.sendKeys(email);
    }

    @Step("Нажать Войти")
    public LoginPage clickEnterAccount(){
        WebElement element = driver.findElement(buttonEnterAccount);
        element.click();
        return new LoginPage(driver);
    }


}
