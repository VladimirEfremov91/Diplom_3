package ru.stellarburgers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
public class LoginPage {
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

//Кнопка Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    public SelenideElement loginButton;
    @Step("Кнопка логина отображается?")
    public boolean isLoginButtonDisplayed() {
        loginButton.shouldBe(Condition.visible);
        return loginButton.isDisplayed();
    }
    @Step("Нажатие кнопки логина")
    public void clickLoginButton() {
        loginButton.click();
    }
//Поле email
    @FindBy(how = How.NAME, using = "name")
    public SelenideElement emailLoginInput;
    @Step("Заполнение поля email для авторизации")
    public void fillEmailLoginInput(String userEmail) {
        emailLoginInput.sendKeys(userEmail);
    }
    @Step("Поле email отображается?")
    public boolean isEmailLoginInputDisplayed() {
        emailLoginInput.shouldBe(Condition.visible);
        return emailLoginInput.isDisplayed();
    }
//Поле Пароль
    @FindBy(how = How.NAME, using = "Пароль")
    public SelenideElement passwordLoginInput;
    @Step("Заполнение поля Пароль для авторизации")
    public void fillPasswordLoginInput(String userPassword) {
        passwordLoginInput.sendKeys(userPassword);
    }
//Метод заполнения полей и нажатия кнопки Войти
    @Step("Заполнение полей и нажатие кнопки Войти")
    public void login(String userEmail, String userPassword) {
        fillEmailLoginInput(userEmail);
        fillPasswordLoginInput(userPassword);
        clickLoginButton();
    }
}