package pages;


import helpers.WebDriverManager;
import helpers.WebDriverWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class YandexMailFactory {
    private WebDriver driver;
    @FindBy(xpath = "//span[text()='Войти в Почту']/parent::button")
    private WebElement enterButton;
    @FindBy(xpath = "//*[@id='passp-field-login']")
    private WebElement inputMailAddress;
    @FindBy(xpath = " //button[@id='passp:sign-in']")
    private WebElement buttonMailAddress;
    @FindBy(xpath = "//*[@id='passp-field-passwd']")
    private WebElement  inputMailPassword;
    @FindBy(xpath = "//a[contains(@class,'user-account_')]/*[@class='user-account__name']")
    private WebElement  userAccountName;
    @FindBy(xpath = "//button[@data-type='login']")
    private WebElement  mailButton;
    @FindBy(xpath = "//head[@data-reactroot]/title")
    private WebElement  title;

    public YandexMailFactory() {
        this.driver= WebDriverManager.getCurrentDriver();
        PageFactory.initElements(driver, this);

    }

    /**
     * Метод вход с почтовый ящик yandex
     * @param login
     * @param pass
     */
    public void goToMaleBox(String login, String pass){
        enterButton.click();
        mailButton.click();
        inputMailAddress.sendKeys(login);
        buttonMailAddress.click();
        inputMailPassword.sendKeys(pass);
        buttonMailAddress.click();



    }

    /**
     * Метод для получения userAccountName
     * @return
     */

    public WebElement getUserAccountName() {
       WebDriverWaits.waitUntilElementToBe(userAccountName,"class","user-account__name");
        return userAccountName;
    }
}
