package SatelliteApp.pages.tracking;

import Base.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopPanel extends BaseSeleniumTest {

    @FindBy(xpath = "//img[@src='theme/images/user.png']")
    private WebElement userIcon;

    public void goToUserSettingsPanel(){
        userIcon.click();
    }

    public TopPanel(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
