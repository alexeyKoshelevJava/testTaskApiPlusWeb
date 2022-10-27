package pages;

import helpers.Constants;
import helpers.WebDriverManager;
import helpers.WebDriverWaits;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResult {

    private WebDriver driver;
    @FindBy(xpath = "//*[@id='rso' ]/div")
    private List<WebElement> selectorSearchItem;
    @FindBy(xpath = "./descendant::h3[1]/parent::a/descendant::cite")
    private WebElement urlItem;
    @FindBy(xpath = "//a[@id='pnnext']")
    private WebElement hrefNextList;
    @FindBy(xpath = "//div[@id='bres']/following-sibling::div")
    private WebElement navigate;
    @FindBy(xpath = "//*[@id='rso' ]/div//descendant::h3[1]//parent::a//descendant::cite[@class]")
    private List<WebElement> textListIntoBlocks;
    private List<String> urlList = new ArrayList<>();

    public GoogleSearchResult() {
        this.driver = WebDriverManager.getCurrentDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод , проверяющий присутствует ли элемент  в дом дереве
     *
     * @param el
     * @return Возвращает true если есть элемент
     */

    public boolean isElementPresent(List<WebElement> el) {
        return el.size() > 0;
    }

    /**
     * Метод , проверяющий присутствует ли элемент  в дом дереве
     *
     * @param el
     * @return Возвращает true если есть элемент
     */

    public boolean isDisplayed(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Метод, заполняющий urlList текстом содержащим url
     */
    private void initListUrl() {
        do {
            textListIntoBlocks.stream().forEach(el -> urlList.add(el.getText()));
            hrefNextList.click();
            WebDriverWaits.waitUntilElementBeVisible(navigate);
        } while (isDisplayed(hrefNextList));
        System.out.println(urlList.stream().map(x -> x.replaceAll("\\s.\\s[а-яА-ЯA-Za-z]*.*", "").trim()).collect(Collectors.toList()));


    }

    /**
     * Метод проверяющий наличие в листе требуемого урла
     *
     * @param urlName
     * @return true если есть такой url
     */
    public boolean checkUrlPresentIntoSearchByName(String urlName) {
        initListUrl();
        return urlList.stream().anyMatch(url -> url.contains(urlName));
    }

    /**
     * Метод возвращает лист url
     *
     * @return
     */
    public List<String> getUrlList() {
        return urlList;
    }
}
