package Samples;

import Base.BaseSeleniumTest;
import Base.helpers.MySeleniumMethods;
import Base.helpers.TestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Listeners(TestListener.class)
public class Satellite extends BaseSeleniumTest {
    private Logger log = Logger.getLogger(Satellite.class);

    @Test
    public void checkDemoObjects() throws ParseException {
        testReporter.set(reports.createTest("Satellite - checkDemoObjects"));
        String logText;

        driver.get("https://track.satellite.net.pl/demo.php");
        MySeleniumMethods.waitForWebElementBy(By.xpath("//img[@src='theme/images/gear.png']"),10,driver);

        //Locate WebElement with last seen time
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@role='row']"));
        List<WebElement> carRows = rows.stream()
                .filter(row -> row.findElements(By.xpath("td/span/a/img[@src='theme/images/gear.png']")).size()>0)
                .collect(Collectors.toList());

        logText = carRows.size()+" cars found.";
        log.info(logText); testReporter.get().info(logText);

        long timeDifferenceThreshold = 6000;

        for (WebElement carRow:carRows)
        {
            String car = carRow.findElement(By.xpath("td/span[1]")).getText();
            String timeAsString = carRow.findElement(By.xpath("td/span[2]")).getText();
            Date timeAsDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeAsString);
            long timeDifference = calculateTimeDifferenceFromNow(timeAsDate);
            logText = "Time of last record for car: "+car+": "+timeDifference+"s. Assertion: Last record should be not older than: "+timeDifferenceThreshold+"s";
            log.info(logText); testReporter.get().info(logText);
            Assert.assertTrue(timeDifference<timeDifferenceThreshold,"Last record not older than "+timeDifferenceThreshold+"s");

        }



    }

    public long calculateTimeDifferenceFromNow(Date time)
    {
        Timestamp now=new Timestamp(System.currentTimeMillis());
        return (now.getTime()-time.getTime())/1000;
    }
}
