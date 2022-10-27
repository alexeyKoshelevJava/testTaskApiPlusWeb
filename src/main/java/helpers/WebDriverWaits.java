package helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WebDriverWaits {

    public static boolean isVisible(WebElement element) {
        return element.isDisplayed();

    }


    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<WebElement> waitUntilElementsPresents(String xpath) {
        return new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }


    public static void waitUntilElementBeVisible(String xpath) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void waitUntilElementBeVisible(WebElement element) {
        if (isVisible(element)) return;
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementBeClickable(WebElement element) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementTextContains(WebElement element, String text) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElementTextContainsByLocator(By locator, String text) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

    }

    public static void waitUntilElementCountWillBe(String xpath, Integer number) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpath), number));
    }

    public static void waitUntilElementNotExistByXpath(String xpath) {
        int timer = 0;
        for (; timer < Constants.DEFAULT_TIMEOUT; timer++) {
            if (WebDriverManager.getCurrentDriver().findElements(By.xpath(xpath)).size() == 0) {
                break;
            }
            sleep(1);
        }
        Assertions.assertNotEquals(timer, Constants.DEFAULT_TIMEOUT, "element  with XPath  " + xpath + " was not found, after the expiration of time " + Constants.DEFAULT_TIMEOUT);
    }

    public static void waitUntilElementNotBeVisible(String xpath) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

    }

    public static void waitUntilElementToBe(WebElement element, String atribute, String value) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.attributeToBe(element, atribute, value));

    }

    public static void waitUntilAttributeWillBe(WebElement element, String attribute, String value) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until((ExpectedCondition<Boolean>) driver -> element.getAttribute(attribute).contains(value));
    }

    public static boolean isElementNotExist(WebElement element) {
        int timer = 0;
        WebDriverManager.getCurrentDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            while (isVisible(element) && timer < 10) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer++;

            }
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        } finally {
            WebDriverManager.getCurrentDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }


    }


}
