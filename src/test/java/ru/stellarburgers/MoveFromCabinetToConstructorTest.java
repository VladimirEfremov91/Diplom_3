package ru.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
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
    @DisplayName("Открытие ЛК по прямой ссылке")
    @Description("Для ревьюера: Данный тест все время падает, так как он выявляет следующий баг: невозможность перехода на авторизованный личный кабинет по прямой ссылке")
    public void moveToUserPageByURL() {
        UserPage userPage = open(USER_PAGE_URL, UserPage.class);
        userPage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор кликом по кнопке Конструктор")
    public void moveToConstructorByConstructorButtonClick() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        UserPage userPage = page(UserPage.class);
        userPage.clickConstructorButton();
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор кликом на логотип")
    public void moveToConstructorByLogoClick() {
        ConstructorPage constructorPage = open(CONSTRUCTOR_PAGE_URL, ConstructorPage.class);
        constructorPage.clickLoginToAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.clickPersonalCabinetButton();
        UserPage userPage = page(UserPage.class);
        userPage.clickLogo();
        assertTrue(constructorPage.isCreateOrderButtonEnabled());
    }
}
