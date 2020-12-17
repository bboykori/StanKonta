package play24.tests;

import com.microsoft.schemas.vml.STExt;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import play24.helpers.TestListener;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import static play24.helpers.MySeleniumMethods.*;


@Listeners(TestListener.class)
public class Play24 extends BaseSeleniumTest{

    private Logger log = Logger.getLogger(Play24.class);
    private List<String> numbers = new ArrayList<>();
    private List<String> phoneNumbers = new ArrayList<>();
    private List<String> result = new ArrayList<>();
    private int iteration;

    @Test
    public void play24Main() throws IOException, InterruptedException {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        testReporter.set(reports.createTest("play 24"));

        //Getting phone numbers from text file
        numbers = readFromTextFile("src\\test\\resources\\Numbers.txt");
        phoneNumbers.clear();
        phoneNumbers = convertPhoneNumberFormat(numbers);

        //Go to play24 and logon
        logonToPlay24();

        result.clear();
        result.add("NrTelefonu;Konto;Internet;MinutyDoPlay");

        //Iterations over phone numbers from txt file
        for (int i=0; i<phoneNumbers.size();i++)
        {
            iteration = i;
            String text = "Iteration "+(i+1)+" of "+phoneNumbers.size()+" phone no: "+phoneNumbers.get(iteration);
            log.info(text); testReporter.get().info(text);
            waitForWebElementBy(By.id("changeNumberDropdown"),40,driver);
            play24selectNumber();
            play24clickOnLinkStanKonta();
            play24gettingDataOfPhoneNumber();
        }
        writeToTextFile(result,"src\\test\\resources\\output.txt",false);

    }

    public List<String> convertPhoneNumberFormat (List<String> numbers){
        List<String> convertedNumbers = new ArrayList<String>();
        for (String line:numbers)
        {
            String phoneNumber = ""+line.charAt(0)+line.charAt(1)+line.charAt(2)+" "
                                   +line.charAt(3)+line.charAt(4)+line.charAt(5)+" "
                                   +line.charAt(6)+line.charAt(7)+line.charAt(8);
            convertedNumbers.add(phoneNumber);
        }
        return convertedNumbers;

    }

    public void logonToPlay24(){
        driver.get("https://24.play.pl/");
        waitForWebElementBy(By.id("IDToken1"),10,driver);
        WebElement loginField = driver.findElement(By.id("IDToken1"));
        loginField.sendKeys("d.korecki@wp.pl");
        WebElement passwordField = driver.findElement(By.id("IDToken2"));
        passwordField.click();
//        passwordField.sendKeys("type password here");
//        passwordField.sendKeys(Keys.ENTER);
        log.info("Waiting for first dashboard to be loaded");
        waitForWebElementBy(By.xpath("//div[@class='pointOut number']"),40,driver);
        waitForWebElementBy(By.id("changeNumberDropdown"),40,driver);

    }



    public void play24selectNumber()
    {
        String text = "Selecting Number";
        log.info(text); testReporter.get().info(text);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String currentNumber = null;
       try {
           waitForWebElementBy(By.xpath("//div[@class='pointOut number']"), 40, driver);
           currentNumber = driver.findElement(By.xpath("//div[@class='pointOut number']")).getText();
       } catch (org.openqa.selenium.StaleElementReferenceException e){
           text = "play24selectNumber catch block of reading current number on page, Refreshing page";
           log.warn(text); testReporter.get().warning(text);
           driver.get("https://24.play.pl/");
           play24selectNumber();
       }
         //If current number on page is not equal to current number in iteration, change number on page
        if (!currentNumber.equals(phoneNumbers.get(iteration)))
        {
            while (!checkIfElementExist(By.xpath("//a[@class='header__user-section__number-dropdown__options__number']/span[text()='" + phoneNumbers.get(iteration) + "']"), driver)) {
                text = "Number "+phoneNumbers.get(iteration)+" element not found in dropdown list, refreshing page";
                log.warn(text); testReporter.get().warning(text);
                driver.get("https://24.play.pl/");
            }

            WebElement changeNumberDropdown = driver.findElement(By.className("header__user-section__current-number__formattedMsisdn"));
            executor.executeScript("arguments[0].click();", changeNumberDropdown);
            WebElement selectedNumber = driver.findElement(By.xpath("//a[@class='header__user-section__number-dropdown__options__number']/span[text()='"+phoneNumbers.get(iteration)+"']"));

            try
            {
                executor.executeScript("arguments[0].click();",selectedNumber) ;
            } catch(org.openqa.selenium.StaleElementReferenceException e)
            {
                text = "play24selectNumber catch block of click on phone number, refreshing page";
                log.warn(text); testReporter.get().warning(text);
                driver.get("https://24.play.pl/");
                play24selectNumber();
            }
        }

        waitForWebElementBy(By.xpath("//div[@class='pointOut number' and text()='"+phoneNumbers.get(iteration)+"']"),40,driver);
        waitForWebElementBy(By.xpath("//div[@id='accountBallances']/a"),40,driver);

    }


    public void play24clickOnLinkStanKonta() throws InterruptedException, IOException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String text = "Clicking on button 'stan konta'";
        log.info(text); testReporter.get().info(text);
        try{
            WebElement linkStanKonta = driver.findElement(By.xpath("//div[@id='accountBallances']/a"));
            executor.executeScript("arguments[0].click();",linkStanKonta) ;
        } catch (org.openqa.selenium.StaleElementReferenceException e){
            text = "play24clickOnLinkStanKonta catch block of click on 'stan konta' button, Refreshing page";
            log.warn(text); testReporter.get().warning(text);
            driver.get("https://24.play.pl/");
            play24selectNumber();
            play24clickOnLinkStanKonta();
        }
        try {
            waitForWebElementBy(By.xpath("//div[@class='modal-action']/button"),3,driver);
        } catch (org.openqa.selenium.TimeoutException e){
            text = "Cannot display dashboard 'stan konta' of number: "+phoneNumbers.get(iteration)+", refreshing page";
            log.warn(text); testReporter.get().warning(text, getScreenShot());
            driver.get("https://24.play.pl/");
            play24selectNumber();
            play24clickOnLinkStanKonta();
        }
    }

    public void play24gettingDataOfPhoneNumber() throws IOException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        waitForWebElementBy(By.xpath("//div[@class='modal-action']/button"),40,driver);
        WebElement okButton = driver.findElement(By.xpath("//div[@class='modal-action']/button"));

        String currentNumberData = driver.findElement(By.xpath("//div[@class='modal-header']/span")).getText();
        Assert.assertEquals(phoneNumbers.get(iteration),currentNumberData);


        List<WebElement> resultData;
        resultData = driver.findElements(By.xpath("//div[@class='modal-body fullHeight']/div/div/div"));

         result.add(currentNumberData+";"
                 +resultData.get(3).getText()+";"
                 +resultData.get(15).getText()+";"
                 +resultData.get(21).getText());

         String text = "Got data for number: "+phoneNumbers.get(iteration)+", clicking ok button";
         log.info(text); testReporter.get().pass(text,getScreenShot());
         executor.executeScript("arguments[0].click();",okButton) ;


    }



}
