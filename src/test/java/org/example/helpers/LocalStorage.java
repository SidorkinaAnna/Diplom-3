package org.example.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorage {
    public static String getLocalStorageItem(WebDriver driver, String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }
}
