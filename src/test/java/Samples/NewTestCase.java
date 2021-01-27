package Samples;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTestCase {
    @Test
    public void newTestCase() throws InterruptedException {
        String driverPath = "src//test//resources//DriversExecutables//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///C:/pliki_z_kursu/Test.html");

        driver.findElement(By.xpath("html//Button[text()='Kliknij mnie!']"));


        Thread.sleep(3000);
        WebElement clickMeButton = driver.findElement(By.id("clickOnMe"));
        clickMeButton.click();

        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        driver.quit();




    }

}
