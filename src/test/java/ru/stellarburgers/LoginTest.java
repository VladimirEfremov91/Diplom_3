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
import static ru.stellarburgers.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;
import static ru.stellarburgers.RestorePasswordPage.RESTORE_PASSWORD_PAGE_URL;

@Epic("Работа с пользователями")
@Feature("Логин пользователя")
public class LoginTest extends BrowserSettings {
    static User user;

    @BeforeClass
    public static void setUp() {
        browserPropertySetUp("chrome");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.timeout = 5000;
        Configuration.pollingInterval = 500;
        user = UserGenerator.getValidRandom();
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.registration(user);
    }
    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void userLoginAfterClickAccountButton() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void userLoginAfterClickPersonalCabinetButton() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickPersonalCabinetButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userLoginAfterClickButtonInRegisterPage() {
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.clickLoginPageButtonInBottom();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        ConstructorPage constructorPage = page(ConstructorPage.class);
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userLoginAfterClickButtonInRestorePasswordPage() {
        RestorePasswordPage restorePasswordPage = open(RESTORE_PASSWORD_PAGE_URL, RestorePasswordPage.class);
        restorePasswordPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        ConstructorPage constructorPage = page(ConstructorPage.class);
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
}