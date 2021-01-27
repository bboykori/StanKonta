package SatelliteApp.pages.tracking;

import Base.BaseSeleniumTest;
import Base.helpers.MySeleniumMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPanel_TabUser extends BaseSeleniumTest {
    JavascriptExecutor executor = (JavascriptExecutor) driver;

    @FindBy(id = "settings_main_name_surname")
    private WebElement inputNameSurename;

    @FindBy(xpath = "//div[@id='settings_main_my_account']/input[@type='button']")
    private WebElement buttonSave;

    @FindBy(xpath = "//div[@class='ui-pnotify-text']")
    private WebElement alertPopup;


    //div[@class='ui-pnotify-text']


    public void typeNameSurename(String nameSureName){
        inputNameSurename.clear();
        inputNameSurename.sendKeys(nameSureName);
    }

    public void saveSettings(){
        buttonSave.click();
        //executor.executeScript("arguments[0].click();",buttonSave) ;
    }

    public String getAlertMessage(){
        MySeleniumMethods.waitForWebElement(alertPopup,40,driver);
        return alertPopup.getText();
    }





    public SettingsPanel_TabUser(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
