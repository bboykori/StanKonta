package SatelliteApp.pages.tracking;

import Base.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftPanel extends BaseSeleniumTest {

    @FindBy(xpath = "//a[@href='#left_panel_objects']")
    private WebElement linkObjects;

    @FindBy(xpath = "//a[@href='#left_panel_events']")
    private WebElement linkEvents;

    @FindBy(xpath = "//a[@href='#left_panel_places']")
    private WebElement linkPlaces;

    @FindBy(xpath = "//a[@href='#left_panel_history_reports']")
    private WebElement linkHistory;

    public void goToObjectsTab(){
        linkObjects.click();
    }

    public void goToEventsTab(){
        linkEvents.click();
    }

    public void goToPlacesTab(){
        linkPlaces.click();
    }

    public void goToHistoryTab(){
        linkHistory.click();
    }

    public LeftPanel(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}