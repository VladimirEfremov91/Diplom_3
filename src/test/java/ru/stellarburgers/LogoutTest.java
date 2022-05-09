package ru.stellarburgers;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.model.BrowserSettings;
import ru.model.User;
import ru.model.UserGenerator;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static ru.stellarburgers.LoginPage.LOGIN_PAGE_URL;
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;

@Epic("Работа с пользователями")
@Feature("Выход пользователя")
public class LogoutTest extends BrowserSettings {
    static User user;
    static RegisterPage registerPage = page(RegisterPage.class);
    ConstructorPage constructorPage = page(ConstructorPage.class);
    LoginPage loginPage = page(LoginPage.class);
    UserPage userPage = page(UserPage.class);

    @BeforeClass
    public static void setUpUser() {
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
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void userLogout() {
        open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        userPage.clickExitButton();
        assertTrue(loginPage.isEmailLoginInputDisplayed());
    }
}