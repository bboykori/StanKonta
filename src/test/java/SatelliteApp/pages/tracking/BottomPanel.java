package SatelliteApp.pages.tracking;

import Base.BaseSeleniumTest;
import Base.helpers.MySeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BottomPanel extends BaseSeleniumTest {

    @FindBy(xpath = "//a[@href='#bottom_panel_msg']")
    private WebElement linkRecords;

    @FindBy(xpath = "//div[@id='jqgh_bottom_panel_msg_list_grid_dt_tracker']")
    private WebElement linkTimeOfRecord;

    @FindBy(id = "load_bottom_panel_msg_list_grid")
    private WebElement loadingRecordsIndicatior;





 public void showRecords(){
     linkRecords.click();
 }

 public void changeOrder(){
     linkTimeOfRecord.click();
     MySeleniumMethods.waitForWebElementInvisibility(loadingRecordsIndicatior,40,driver);
 }

 public String getOldestRecordTime(){
     String time = driver.findElement(By.xpath("//table[@id='bottom_panel_msg_list_grid']/tbody/tr[@id='0']/td[1]")).getText();
     return time;

 }

    public BottomPanel(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
