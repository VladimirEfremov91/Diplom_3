package ru.stellarburgers;
import io.qameta.allure.Description;
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
import static ru.stellarburgers.UserPage.USER_PAGE_URL;

@Epic("Личный кабинет пользователя")
@Feature("Переход из ЛК в конструктор")
public class MoveFromCabinetToConstructorTest extends BrowserSettings {
    static User user;
    static RegisterPage registerPage = page(RegisterPage.class);
    ConstructorPage constructorPage = page(ConstructorPage.class);
    LoginPage loginPage = page(LoginPage.class);
    UserPage userPage = page(UserPage.class);

    @BeforeClass
    public static void setUp() {
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
    @DisplayName("Открытие ЛК по прямой ссылке")
    @Description("Для ревьюера: Данный тест все время падает, так как он выявляет следующий баг: невозможность перехода на авторизованный личный кабинет по прямой ссылке")
    public void moveToUserPageByURL() {
        open(USER_PAGE_URL, UserPage.class);
        userPage.clickConstructorButton();
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор кликом по кнопке Конструктор")
    public void moveToConstructorByConstructorButtonClick() {
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        userPage.clickConstructorButton();
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор кликом на логотип")
    public void moveToConstructorByLogoClick() {
        open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        userPage.clickLogo();
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
}