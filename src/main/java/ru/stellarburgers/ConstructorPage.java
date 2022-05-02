package ru.stellarburgers;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {
    public static final String CONSTRUCTOR_PAGE_URL = "https://stellarburgers.nomoreparties.site";

//Нажатая кнопка в блоке выбора ингредиента
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    public SelenideElement clickedButton;
    @Step("Получение текста нажатой кнопки")
    public String getTextClickedButton() {
        return clickedButton.getText();
    }
//Кнопка Булки
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    public SelenideElement bunsButtonIsNotClicked;
    @Step("Клик на кнопку Булки в конструкторе")
    public void clickBunsButton() {
        bunsButtonIsNotClicked.click();
    }
//Кнопка Соусы
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    public SelenideElement sauceButtonIsNotClicked;
    @Step("Клик на кнопку Соусы в конструкторе")
    public void clickSauceButton() {
        sauceButtonIsNotClicked.click();
    }
//Кнопка Начинки
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    public SelenideElement fillingButtonIsNotClicked;
    @Step("Клик на кнопку Начинки в конструкторе")
    public void clickFillingButton() {
        fillingButtonIsNotClicked.click();
    }
//Кнопка Войти в аккуант
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    public SelenideElement loginToAccountButton;
    @Step("Нажатие кнопки Войти в аккуант")
    public void clickLoginToAccountButton() {
        loginToAccountButton.click();
    }
//Кнопка оформить заказ
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    public SelenideElement createOrderButton;
    @Step("Проверка отображения кнопки Оформить зазказ")
    public boolean isCreateOrderButtonEnabled() {
        return createOrderButton.isEnabled();
    }
//Кнопка Личный кабинет
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    public SelenideElement personalCabinetButton;
    @Step("Нажатие кнопки Личный кабинет")
    public void clickPersonalCabinetButton() {
        personalCabinetButton.click();
    }
}