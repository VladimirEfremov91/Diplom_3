package ru.model;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BrowserSettings {
    private static String browser = "chrome";   //Если нужно запустить тесты в Яндекс.Браузере, ввести "yandex". Сделано для того, чтоюы менять браузер в одном месте

    public static void yandexBrowser() {
            ChromeDriver driver;
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            driver = new ChromeDriver();
            setWebDriver(driver);
    }
    /* В качестве драйвера используется версия 22.3.0.2520, получееная по ссылке:
        https://github.com/yandex/YandexDriver/releases/download/v22.3.0-stable/yandexdriver-22.3.0.2434-win.zip
    * Версии для других платформ:
        https://github.com/yandex/YandexDriver/releases
    */
    @Step("Выбор браузера, в котором будет проводится тест")
    public static void browserPropertySetUp() {
        if (browser.equals("yandex")) {
            yandexBrowser();
        }
    }
    public static void testConfigurationSetUp() {
        Configuration.timeout = 5000;
        Configuration.pollingInterval = 500;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}