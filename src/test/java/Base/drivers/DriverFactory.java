package Base.drivers;

import org.openqa.selenium.chrome.ChromeOptions;
import Base.exceptions.NoSuchDriverException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class DriverFactory {

    private static Logger log = Logger.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {
        if (driverInstance.get() == null){
            log.debug("Initalizing variable driverInstance");
            getSpecificDriver(driverType) ;
            driverInstance.get().manage().window().maximize();
        }
        return driverInstance.get();
    }

    private static void getSpecificDriver (DriverType driverType) throws NoSuchDriverException {
        switch(driverType) {
            case IE:
                File ieExe = new File("src//test//resources//DriversExecutables//IEDriverServer.exe");
                InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(ieExe)
                        .usingAnyFreePort().build();
                driverInstance.set(new InternetExplorerDriver(ieService));
            break;

            case CHROME:
                File chromeExe = new File("src//test//resources//DriversExecutables//chromedriver.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeExe)
                        .usingAnyFreePort().build();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                driverInstance.set(new ChromeDriver(chromeService,chromeOptions));
            break;

            case FIREFOX:
                File fireFoxExe = new File("src//test//resources//DriversExecutables//geckodriver.exe");
                GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(fireFoxExe)
                        .usingAnyFreePort().build();
                FirefoxOptions options = new FirefoxOptions();
                driverInstance.set(new FirefoxDriver(geckoDriverService));
            break;

            default:
               throw new NoSuchDriverException();
        }

    }

    public static void resetDriver() {

        driverInstance.set(null);
    }






}
