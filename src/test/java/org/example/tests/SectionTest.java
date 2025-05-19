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
    private final String nameFrom;
    private final String nameTo;

    public SectionTest(String nameFrom, String nameTo) {
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"Начинки", "Соусы"}, {"Соусы", "Начинки"}, {"Начинки", "Булки"}});
    }

    @Test
    public void transitionSection() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSection(nameFrom);
        mainPage.clickSection(nameTo);
        assertTrue(mainPage.sectionIsVisibleCheck(nameTo));
        assertTrue(mainPage.sectionTitleIsSelected(nameTo));
    }
}
