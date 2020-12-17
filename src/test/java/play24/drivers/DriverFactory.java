package play24.drivers;

import org.openqa.selenium.chrome.ChromeOptions;
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


    //private static WebDriver driverInstance;
    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();
    public static WebDriver getDriver(DriverType driverType)  {

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

    private static void getSpecificDriver (DriverType driverType)  {


        if (driverType == DriverType.IE)
        {
            File ieExe = new File("src//test//resources//drivers//IEDriverServer.exe");
            InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                    .usingDriverExecutable(ieExe)
                    .usingAnyFreePort().build();
            driverInstance.set(new InternetExplorerDriver(ieService));
        }

        if (driverType == DriverType.CHROME)
        {
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
        }

        if (driverType == DriverType.FIREFOX)
        {
            File fireFoxExe = new File("src//test//resources//drivers//geckodriver.exe");
            GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                    .usingDriverExecutable(fireFoxExe)
                    .usingAnyFreePort().build();
            FirefoxOptions options = new FirefoxOptions();
            driverInstance.set(new FirefoxDriver(geckoDriverService));
        }







    }

    public static void resetDriver() {

        driverInstance.set(null);
    }






}
