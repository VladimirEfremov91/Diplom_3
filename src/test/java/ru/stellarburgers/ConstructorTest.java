package ru.stellarburgers;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.model.BrowserSettings;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static ru.stellarburgers.ConstructorPage.CONSTRUCTOR_PAGE_URL;

@Epic("Реализация сборки бургера")
@Feature("Переходы к разделам в блоке ингредиентов бургера")
public class ConstructorTest extends BrowserSettings {
    ConstructorPage constructorPage = page(ConstructorPage.class);

    @BeforeClass
    public static void getReady() {
        browserPropertySetUp();
        testConfigurationSetUp();
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
    }
    @After
    public void tearDown() {
        refresh();
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Переход к соусам")
    public void moveToSauceTest() {
        constructorPage.clickSauceButton();
        assertEquals("Соусы", constructorPage.getTextClickedButton());
    }
    @Test
    @DisplayName("Переход к начинкам")
    public void moveToFillingTest() {
        constructorPage.clickFillingButton();
        assertEquals("Начинки", constructorPage.getTextClickedButton());
    }
    @Test
    @DisplayName("Переход к булкам")
    public void moveToBunsTest() {
        constructorPage.clickSauceButton();
        sleep(1000);
        constructorPage.clickBunsButton();
        assertEquals("Булки", constructorPage.getTextClickedButton());
    }
}