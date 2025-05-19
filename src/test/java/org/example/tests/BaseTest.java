package org.example.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void beforeEach(){
//        driver = getDriver("yandex");
        driver = getDriver("chrome");
    }


    public WebDriver getDriver(String driverName){
        switch (driverName) {
            case "yandex": {
                System.setProperty("webdriver.chrome.driver", "C:\\yandexdrivers\\yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                return driver;
            }
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                return driver;
            }
        }
        return null;
    }


    @After
    public void after(){
        driver.quit();
    }

}
