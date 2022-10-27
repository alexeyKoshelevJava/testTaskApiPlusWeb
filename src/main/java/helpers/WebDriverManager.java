package helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static WebDriver currentDriver;

    public static void initChrome() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe" );
//        System.getenv("CHROME_DRIVER")
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try {
            currentDriver = new ChromeDriver(options);
        } catch (SessionNotCreatedException e) {
            Assertions.fail("ошибка создания драйвера");
        }
        setDriverDefaultSetting();


    }

    private static void setDriverDefaultSetting() {
        currentDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();

    }

    public static void killCurrentDriver() {
        if (currentDriver != null) {
            currentDriver.quit();
            currentDriver = null;

        }

    }

    public static WebDriver getCurrentDriver() {
        return currentDriver;
    }
}
