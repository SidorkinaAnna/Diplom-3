package org.example.tests;

import org.example.page_objects.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class SectionTest extends BaseTest {
    private final String name;

    public SectionTest(String name) {
        this.name = name;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"Булки"}, {"Соусы"}, {"Начинки"}});
    }

    @Test
    public void transitionSection() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSection(name);
        assertTrue(mainPage.sectionIsVisibleCheck(name));
        assertTrue(mainPage.sectionTitleIsSelected(name));
    }
}
