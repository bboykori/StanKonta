package Samples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class GoogleSearch {
    @Test
    public void googleSearch() throws InterruptedException {
        String driverPath = "src//test//resources//DriversExecutables//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://google.com");
        driver.switchTo().frame(0);
        WebElement agreeButton = driver.findElement(By.xpath("//span[text()='Zgadzam się']"));
        agreeButton.click();
        WebElement queryField = driver.findElement(By.name("q"));
        queryField.sendKeys("Solwit S.A.");
        queryField.sendKeys(Keys.ENTER);
        Thread.sleep(3000);


    }
}
