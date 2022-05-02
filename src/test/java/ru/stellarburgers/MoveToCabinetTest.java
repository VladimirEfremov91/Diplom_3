package ru.stellarburgers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.model.BrowserSettings;
import ru.model.User;
import ru.model.UserGenerator;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static ru.stellarburgers.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;

@Epic("Личный кабинет пользователя")
@Feature("Переход в личный кабинет по кнопке")
public class MoveToCabinetTest extends BrowserSettings {
    static User user;

    @BeforeClass
    public static void setUpUser() {
        browserPropertySetUp("chrome");
        user = UserGenerator.getValidRandom();
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.registration(user);
    }

    @Before
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.timeout = 5000;
        Configuration.pollingInterval = 500;
    }
    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на Личный кабинет в конструкторе")
    public void moveToCabinet() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        UserPage userPage = page(UserPage.class);
        assertTrue(userPage.isInformationTextDisplayed());
    }
}