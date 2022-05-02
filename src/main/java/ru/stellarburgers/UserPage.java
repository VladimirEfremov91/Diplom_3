package ru.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserPage {
    public static final String USER_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

//Кнопка Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    public SelenideElement constructorButton;
    @Step("Клик на кнопку Конструктор в личном кабинете")
    public void clickConstructorButton() {
        constructorButton.click();
    }
//Логотип
    @FindBy(how = How.CSS, using = "div#root>div>header>nav")
    public SelenideElement logo;
    @Step("Клик на Логотип в личном кабинете")
    public void clickLogo() {
        logo.click();
    }
//Кнопка Выход
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'Account_button__14Yp3 text')]")
    public SelenideElement exitButton;
    @Step("Клик на кнопку Конструктор в личном кабинете")
    public void clickExitButton() {
        exitButton.click();
    }
//Текст-подсказка
    @FindBy(how = How.XPATH, using = "//p[text()='В этом разделе вы можете изменить свои персональные данные']")
    public SelenideElement informationText;
    @Step("Информационный текст ЛК отображается")
    public boolean isInformationTextDisplayed() {
        informationText.shouldBe(Condition.visible);
        return informationText.isDisplayed();
    }
}