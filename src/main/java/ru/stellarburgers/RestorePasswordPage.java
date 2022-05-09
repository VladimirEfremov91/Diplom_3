package ru.stellarburgers;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RestorePasswordPage {
    public static final String RESTORE_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

//Кнопка восстановления пароля
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    public SelenideElement loginButton;
    @Step("Клик на кнопку Войти на странице восстановления пароля")
    public void clickLoginButton() {
        loginButton.click();
    }
}