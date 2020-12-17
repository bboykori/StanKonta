package play24.drivers;

import org.openqa.selenium.chrome.ChromeOptions;
import play24.exceptions.NoSuchDriverException;
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
import java.util.Collections;

public class DriverFactory {

    private static Logger log = Logger.getLogger(DriverFactory.class);


    //private static WebDriver driverInstance;
    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();
    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {

        if (driverInstance.get() == null){
            log.debug("Inicjalizacja zmiennej driverInstance");
           // System.out.println("Inicjalizacja zmiennej driverInstance");
            getSpecificDriver(driverType) ;



            driverInstance.get().manage().window().maximize();
           // Dimension dimension = new Dimension(1024,653);
           // driverInstance.get().manage().window().setSize(dimension);
        }


        return driverInstance.get();

    }

    private static void getSpecificDriver (DriverType driverType) throws NoSuchDriverException {

        switch(driverType) {
            case IE:
                File ieExe = new File("src//test//resources//drivers//IEDriverServer.exe");
                InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(ieExe)
                        .usingAnyFreePort().build();
                driverInstance.set(new InternetExplorerDriver(ieService));
            break;

            case CHROME:
                //            //Pierwszy sposób inicjalizacji driver'a z wykorzystaniem zmiennej środowiskowej
//            String driverPath = "C:\\Users\\Administrator\\IdeaProjects\\selenium\\src\\test\\resources\\drivers\\chromedriver.exe";
//            System.setProperty("webdriver.chrome.driver",driverPath);
//            driverInstance = new ChromeDriver();

                //Drugi sposób inicjalizacji driver'a bez zmiennej środowikoswej
                File chromeExe = new File("src//test//resources//drivers//chromedriver.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeExe)
                        .usingAnyFreePort().build();
                ChromeOptions chromeOptions = new ChromeOptions();
            //    chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

             //   chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
             //   chromeOptions.setExperimentalOption("useAutomationExtension", false);
                driverInstance.set(new ChromeDriver(chromeService,chromeOptions));

            break;

            case FIREFOX:
                File fireFoxExe = new File("src//test//resources//drivers//geckodriver.exe");
                GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(fireFoxExe)
                        .usingAnyFreePort().build();
                FirefoxOptions options = new FirefoxOptions();
                driverInstance.set(new FirefoxDriver(geckoDriverService));

            break;

            default:
                System.out.println("Brak danego driver'a");
                throw new NoSuchDriverException();



        }

    }

    public static void resetDriver() {

        driverInstance.set(null);
    }






}
