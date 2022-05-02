package ru.stellarburgers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.model.BrowserSettings;
import ru.model.User;
import ru.model.UserGenerator;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;

@Epic("Работа с пользователями")
@Feature("Создание пользователя")
public class RegistrationTest extends BrowserSettings {
    User user;

    @BeforeClass
    public static void setUp() {
        browserPropertySetUp("chrome");
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
    @Test
    @DisplayName("Регистрация нового пользователя с корректными данными")
    public void newUserRegistration() {
        user = UserGenerator.getValidRandom();
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.registration(user);
        LoginPage loginPage = page(LoginPage.class);
        assertTrue(loginPage.isLoginButtonDisplayed());
        assertTrue(loginPage.isEmailLoginInputDisplayed());
    }
    @Test
    @DisplayName("Регистрация нового пользователя c некорректным паролем")
    public void newUserRegistrationIncorrectPassword() {
        user = UserGenerator.getInvalidPasswordRandom();
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.registration(user);
        assertTrue(registerPage.isPasswordIncorrectErrorDisplayed());
    }
}