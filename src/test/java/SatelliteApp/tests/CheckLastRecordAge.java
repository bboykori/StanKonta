package SatelliteApp.tests;

import SatelliteApp.helpers.Helpers;
import SatelliteApp.pages.LogonPage;
import SatelliteApp.pages.tracking.LeftPanel;
import SatelliteApp.pages.tracking.LeftPanel_TabObjects;
import Base.helpers.TestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseSeleniumTest;

import java.util.List;

@Listeners(TestListener.class)
public class CheckLastRecordAge extends BaseSeleniumTest {

    @Test
    public void checkLastRecordAge()  {

        String username = "demo";
        String password = "demo123";
        long recordAgeThreshold = 600; //time threshold in seconds

        testReporter.set(reports.createTest("Satellite - Check age of last record"));
        driver.get("https://track.satellite.net.pl");
        LogonPage logonPage = new LogonPage(driver);
        LeftPanel leftPanel = new LeftPanel(driver);
        LeftPanel_TabObjects leftPanel_tabObjects = new LeftPanel_TabObjects(driver);
        Helpers helpers = new Helpers();

        logonPage.submitCredentials(username,password);
        leftPanel.goToObjectsTab();

        List<WebElement> cars = leftPanel_tabObjects.getCars();
             for (WebElement car:cars)
             {
                 String carName = leftPanel_tabObjects.getCarName(car);
                 String lastRecordTime = leftPanel_tabObjects.getCarLastRecordTime(car);
                 long recordAge = helpers.calculateRecordAge(lastRecordTime);
                 testReporter.get().info("Age of last record of "+carName+": "+recordAge+"s");
                 testReporter.get().info("Assertion: Last record should be not older than: "+recordAgeThreshold+"s");
                 Assert.assertTrue(recordAge<recordAgeThreshold,"Last record not older than "+recordAgeThreshold+"s");
             }

    }

}
