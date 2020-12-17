package play24.helpers;

//import jdk.internal.icu.lang.UCharacterDirection;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MySeleniumMethods {

    private static Logger log = Logger.getLogger(MySeleniumMethods.class);



    // CheckIfElementExist - bylo na kursie, ale chyba bez sensu, bo sa przeciez asercje
    public static boolean checkIfElementExist(By locator, WebDriver driver) {
        if (driver.findElements(locator).size() > 0) {
         //   System.out.println("Element istnieje na stronie");
            return true;
        } else {
         //   System.out.println("Elementu niema na stronie");
            return false;
        }
    }

    //Switch to new window
    public void switchToNewWindow(WebDriver driver) {
        String currentHandle = driver.getWindowHandle();
        // System.out.println("Current handle ze srodka metody switchToNewWindow to "+currentHandle);
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles
        ) {
            //System.out.println("To jest iteracja for each handle dla "+handle);
            if (!currentHandle.equals(handle)) {
                //System.out.println("currentHandle to "+currentHandle);
                // System.out.println("Przelaczam okno na "+handle);
                driver.switchTo().window(handle);
            }

        }

    }


    //Explicit wait for visibilityOfElementLocatedBy
    public static void waitForWebElementBy(By locator, int timeout, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Explicit wait for visibilityOfElement - To use with @FindBy
    public static void waitForWebElement(WebElement webElement, int timeout, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    //Explicit wait for invisibilityOfElement - To use with @FindBy
    public static void waitForWebElementInvisibility(WebElement webElement, int timeout, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }




    //Take screenshot
    public static void takeScreenShot(WebDriver driver){

        try {
            TakesScreenshot screenshoter = (TakesScreenshot) driver;
            File screenshot = screenshoter.getScreenshotAs(OutputType.FILE);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss.SSS");
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = dtf.format(now);
            Files.copy(screenshot.toPath(), Paths.get("src/test/resources/screenshots/"+formattedNow+".png"));
            log.info("Method takeScreenShot: Screenshot "+formattedNow+".png created.");
          //  System.out.println("Method takeScreenShot: Screenshot "+formattedNow+".png created.");
        } catch (IOException e) {
            log.error("Method takeScreenShot: Path not exists");
          //  System.out.println("Method takeScreenShot: Nie znaleziono pliku");
        }
    }

    //Take screenshot for report (filePath return)
    public static String takeScreenShotForReport(WebDriver driver){
        try {
            TakesScreenshot screenshoter = (TakesScreenshot) driver;
            File screenshot = screenshoter.getScreenshotAs(OutputType.FILE);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss.SSS");
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = dtf.format(now);
            Path detinationPath = Paths.get("src/test/resources/reports/"+formattedNow+".png");
            Files.copy(screenshot.toPath(),detinationPath );
          //  System.out.println("Method takeScreenShotForReport: Screenshot "+formattedNow+".png created.");
            log.info("Method takeScreenShotForReport: Screenshot "+formattedNow+".png created.");
            return formattedNow+".png";

        } catch (IOException e) {
            log.error("Method takeScreenShotForReport: Path not exists");
         //   System.out.println("Method takeScreenShotForReport: Nie znaleziono pliku");
            return "Method takeScreenShotForReport: Path not exists";
        }

    }

    //Czytanie z pliku tekstowego
    public static List<String> readFromTextFile(String filePath){
        List<String> text = new ArrayList<String>();
        text.clear();
        File plik = new File(filePath);
        try {
            BufferedReader reader = com.google.common.io.Files.newReader(plik, Charset.defaultCharset());
            String line = null;
            while ((line = reader.readLine())!=null){
                // System.out.println(line);
                text.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    // Zapis do pliku tekstowego
    public static void writeToTextFile(List<String> text, String filePath, boolean append)  {

        File file = new File(filePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line:text)
        {
            try {
                writer.append(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
