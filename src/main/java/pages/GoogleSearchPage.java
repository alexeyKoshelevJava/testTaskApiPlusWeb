package pages;

import helpers.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {
    protected WebDriver driver;
    private String search = "//input[@name='q']";

    public GoogleSearchPage() {
        this.driver = WebDriverManager.getCurrentDriver();

    }

    /**
     * Метод для поиска в гугл str
     * @param str
     */
    public void search(String str) {
        driver.findElement(By.xpath(search)).sendKeys(str + "\n");

    }
}
