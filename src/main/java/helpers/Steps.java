package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.GoogleSearchPage;
import pages.GoogleSearchResult;
import pages.YandexMailFactory;

public class Steps {
    @Step("ШАГ .Переход на страницу : {page} ")
    public static void goToPage(String page) {
        WebDriver driver = WebDriverManager.getCurrentDriver();
        driver.get(page);
    }

    @Step("ШАГ .Переход в yandex mail : {login} {pass}")
    public static void goToYandexMailBox(String login, String pass) {
        YandexMailFactory factory = new YandexMailFactory();
        factory.goToMaleBox(login, pass);
        Assertions.assertTrue(factory.getUserAccountName().getText().contains(login), "user account has not text: " + login);
        Screenshoter.getScreen(WebDriverManager.getCurrentDriver(), factory.getUserAccountName());
        Assertions.assertTrue(WebDriverManager.getCurrentDriver().getTitle().contains("Входящие — Яндекс Почта"), "title has not text: Входящие — Яндекс Почта");
    }

    @Step("ШАГ .Поиск google : {str}")
    public static void googleSearch(String str) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.search(str);
    }

    @Step("ШАГ .Проверка наличия в Google поиске необходимого url и необходимого количества результатов выдачи  : {url} {count} ")
    public static void checkUrlPresentIntoSearchAndCountItemMoreThan(String url, int count) {
        GoogleSearchResult googleSearchResult = new GoogleSearchResult();
        Assertions.assertTrue(googleSearchResult.checkUrlPresentIntoSearchByName(url), "в выдаче нет требуемого url: " + url);
        Assertions.assertTrue(googleSearchResult.getUrlList().size() > count, "в выдаче меньше элементов чем: " + count);
    }


}


