package Samples;

import Base.BaseSeleniumTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.helpers.TestListener;

import java.io.IOException;

@Listeners(TestListener.class)
public class NewTestCase2 extends BaseSeleniumTest {
    private Logger log = Logger.getLogger(NewTestCase2.class);

    @Test
    public void newTestCase2() throws IOException, InterruptedException {
        testReporter.set(reports.createTest("newTestCase2"));

        driver.get("https://google.com");
        testReporter.get().info("Google",getScreenShot());
        log.info("Google");
        driver.switchTo();



        Thread.sleep(3000);





    }
}
