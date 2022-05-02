package ru.stellarburgers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import io.qameta.allure.selenide.AllureSelenide;
import ru.model.BrowserSettings;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static ru.stellarburgers.ConstructorPage.CONSTRUCTOR_PAGE_URL;

@Epic("Реализация сборки бургера")
@Feature("Переходы к разделам в блоке ингредиентов бургера")
public class ConstructorTest extends BrowserSettings {

    @BeforeClass
    public static void startUp() {
        browserPropertySetUp("chrome");
    }

    @Before
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.timeout = 5000;
        Configuration.pollingInterval = 500;
        clearBrowserCookies();
    }
    @Test
    @DisplayName("Переход к соусам")
    public void moveToSauceTest() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickSauceButton();
        assertEquals("Соусы", constructorPage.getTextClickedButton());
    }
    @Test
    @DisplayName("Переход к начинкам")
    public void moveToFillingTest() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickFillingButton();
        assertEquals("Начинки", constructorPage.getTextClickedButton());
    }
    @Test
    @DisplayName("Переход к булкам")
    public void moveToBunsTest() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickSauceButton();
        constructorPage.clickBunsButton();
        assertEquals("Булки", constructorPage.getTextClickedButton());
    }
}