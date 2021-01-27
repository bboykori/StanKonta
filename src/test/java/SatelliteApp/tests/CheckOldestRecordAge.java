package SatelliteApp.tests;

import Base.BaseSeleniumTest;
import Base.helpers.TestListener;
import SatelliteApp.helpers.Helpers;
import SatelliteApp.pages.LogonPage;
import SatelliteApp.pages.tracking.BottomPanel;
import SatelliteApp.pages.tracking.LeftPanel;
import SatelliteApp.pages.tracking.LeftPanel_TabHistory;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CheckOldestRecordAge extends BaseSeleniumTest {

    @Test
    public void checkOldestRecordAge()  {

        String username = "test";
        String password = "test123";
        long oldestRecordAgeThreshold = 93;

        testReporter.set(reports.createTest("Satellite - Check oldest record"));
        driver.get("https://track.satellite.net.pl");
        LogonPage logonPage = new LogonPage(driver);
        LeftPanel leftPanel = new LeftPanel(driver);
        LeftPanel_TabHistory leftPanel_tabHistory = new LeftPanel_TabHistory(driver);
        BottomPanel bottomPanel = new BottomPanel(driver);
        Helpers helpers = new Helpers();

        logonPage.submitCredentials(username,password);
        leftPanel.goToHistoryTab();

        leftPanel_tabHistory.selectDateTimeFrom("2015-01-01 12:00");
        leftPanel_tabHistory.selectDateTimeTo(helpers.dateTimePast(oldestRecordAgeThreshold-4));
        leftPanel_tabHistory.showHistory();
        bottomPanel.showRecords();
        bottomPanel.changeOrder();
        String oldestRecordTime = bottomPanel.getOldestRecordTime();
        long oldestRecordAgeDays = helpers.calculateRecordAge(oldestRecordTime)/86400;
        testReporter.get().info("Age of oldest record is: "+oldestRecordAgeDays+" days");
        testReporter.get().info("Assertion: Age of oldest record should not be greater than "+oldestRecordAgeThreshold+"days");
        Assert.assertTrue(oldestRecordAgeDays<=oldestRecordAgeThreshold,"Age of oldest record should not be greater than "+oldestRecordAgeThreshold+"days");

    }

}
