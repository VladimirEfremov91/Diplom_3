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
import static ru.stellarburgers.RegisterPage.REGISTER_PAGE_URL;

@Epic("Работа с пользователями")
@Feature("Создание пользователя")
public class RegistrationTest extends BrowserSettings {
    User user;
    RegisterPage registerPage = page(RegisterPage.class);
    LoginPage loginPage = page(LoginPage.class);

    @BeforeClass
    public static void getReady() {
        browserPropertySetUp();
        testConfigurationSetUp();
    }
    @Before
    public void setUp() {
        open(REGISTER_PAGE_URL, RegisterPage.class);
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
        registerPage.registration(user);
        assertTrue(loginPage.isLoginButtonDisplayed());
        assertTrue(loginPage.isEmailLoginInputDisplayed());
    }
    @Test
    @DisplayName("Регистрация нового пользователя c некорректным паролем")
    public void newUserRegistrationIncorrectPassword() {
        user = UserGenerator.getInvalidPasswordRandom();
        registerPage.registration(user);
        assertTrue(registerPage.isPasswordIncorrectErrorDisplayed());
    }
}