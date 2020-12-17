package play24.helpers;


import play24.tests.BaseSeleniumTest;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    Logger log = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        log.debug("On test start");
        //System.out.println("TestListener: On test start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.debug("On test success");
       // System.out.println("TestListener: On test success");

            BaseSeleniumTest.testReporter.get().pass("Passed!");
           }

    @Override
    public void onTestFailure(ITestResult result) {
       // System.out.println("TestListener: On test failure");
        log.debug("On test failure");
       MySeleniumMethods.takeScreenShot(BaseSeleniumTest.driverForListener.get());
       try {
           BaseSeleniumTest.testReporter.get().fail("Fail caught by Listener",BaseSeleniumTest.getScreenShot());
        } catch (IOException e) {
            log.error(e.getStackTrace());

        }


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("O test skipped");
        //System.out.println("TestListener: On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.debug("On test FailedButWithinSuccessPercentage");
        //System.out.println("TestListener: On test FailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.debug("On test onTestFailedWithTimeout");
        // System.out.println("TestListener: On test onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {
        log.debug("On test onStart(ITestContext context)");
       // System.out.println("TestListener: On test onStart(ITestContext context)");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.debug("On test Finish");
        //System.out.println("TestListener: On test Finish");
    }
}
