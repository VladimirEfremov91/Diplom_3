package ru.stellarburgers;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.model.User;

public class RegisterPage {
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

//Кнопка Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    public SelenideElement loginPageButtonInBottom;
    @Step("Нажатие на кнопку Личный кабинет")
    public void clickLoginPageButtonInBottom() {
        loginPageButtonInBottom.click();
    }
//Поле Имени
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    public SelenideElement fillNameInput;
    @Step("Заполнение поля имени")
    public void fillNameInput(String userName) {
        fillNameInput.sendKeys(userName);
    }
//Поле email
    @FindBy(how = How.XPATH, using = "(//input[@name='name'])[2]")
    public SelenideElement emailInput;
    @Step("Заполнение поля email")
    public void fillEmailInput(String userEmail) {
        emailInput.sendKeys(userEmail);
    }
//Поле пароля
    @FindBy(how = How.NAME, using = "Пароль")
    public SelenideElement passwordInput;
    @Step("Заполнение поля password")
    public void fillPasswordInput(String userPassword) {
        passwordInput.sendKeys(userPassword);
    }
//Кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    public SelenideElement registerButton;
    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }
//Ошибка валидации некорректного пароля
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    public SelenideElement passwordIncorrectError;
    @Step("Отображение сообщения об ошибке с неправильным паролем")
    public boolean isPasswordIncorrectErrorDisplayed() {
        return passwordIncorrectError.isDisplayed();
    }

//Комбинированные методы
    @Step("Ввод данных пользователя и нажатие кнопки регистрации")
    public void registration(User user) {
        fillNameInput(user.getName());
        fillEmailInput(user.getEmail());
        fillPasswordInput(user.getPassword());
        clickRegisterButton();
    }
}