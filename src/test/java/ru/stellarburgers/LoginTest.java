package ru.stellarburgers;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
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
    static RegisterPage registerPage = page(RegisterPage.class);
    ConstructorPage constructorPage = page(ConstructorPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RestorePasswordPage restorePasswordPage = page(RestorePasswordPage.class);

    @BeforeClass
    public static void getReady() {
        browserPropertySetUp();
        testConfigurationSetUp();
        user = UserGenerator.getValidRandom();
        open(REGISTER_PAGE_URL, RegisterPage.class);
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
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void userLoginAfterClickPersonalCabinetButton() {
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickPersonalCabinetButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userLoginAfterClickButtonInRegisterPage() {
        open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.clickLoginPageButtonInBottom();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userLoginAfterClickButtonInRestorePasswordPage() {
        open(RESTORE_PASSWORD_PAGE_URL, RestorePasswordPage.class);
        restorePasswordPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
}