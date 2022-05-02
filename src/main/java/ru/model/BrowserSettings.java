package ru.model;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BrowserSettings {
    public static void browserPropertySetUp(String browser) {
        if (browser.equals("yandex")) {
            ChromeDriver driver;
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\yandexdriver.exe");
            driver = new ChromeDriver();
            setWebDriver(driver);
        }
    }
}
