package SatelliteApp.pages;

import Base.helpers.MySeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Base.BaseSeleniumTest;

public class LogonPage extends BaseSeleniumTest {

    @FindBy(id = "username")
    private WebElement fieldUsername;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement buttonSubmit;

    public void submitCredentials(String username, String password){
        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        buttonSubmit.click();
        MySeleniumMethods.waitForWebElementBy(By.xpath("//img[@src='theme/images/gear.png']"),20,driver);
        testReporter.get().info("User: "+username+" - Logon success");
    }

    public LogonPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
