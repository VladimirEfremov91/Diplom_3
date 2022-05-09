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
import static ru.stellarburgers.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;

@Epic("Личный кабинет пользователя")
@Feature("Переход в личный кабинет по кнопке")
public class MoveToCabinetTest extends BrowserSettings {
    static User user;
    static RegisterPage registerPage = page(RegisterPage.class);
    ConstructorPage constructorPage = page(ConstructorPage.class);
    LoginPage loginPage = page(LoginPage.class);
    UserPage userPage = page(UserPage.class);

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
    @DisplayName("Переход в личный кабинет по клику на Личный кабинет в конструкторе")
    public void moveToCabinet() {
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        assertTrue(userPage.isInformationTextDisplayed());
    }
}