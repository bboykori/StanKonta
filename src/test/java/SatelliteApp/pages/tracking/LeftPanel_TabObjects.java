package SatelliteApp.pages.tracking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import Base.BaseSeleniumTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LeftPanel_TabObjects extends BaseSeleniumTest {





    public LeftPanel_TabObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

   public List<WebElement> getCars(){
       List<WebElement> rows = driver.findElements(By.xpath("//tr[@role='row']"));
       List<WebElement> carRows = rows.stream()
               .filter(row -> row.findElements(By.xpath("td/span/a/img[@src='theme/images/gear.png']")).size()>0)
               .collect(Collectors.toList());
       return carRows;
   }

   public String getCarName(WebElement car){
       String carName = car.findElement(By.xpath("td/span[1]")).getText();
       return carName;
   }

   public String getCarLastRecordTime(WebElement car){
      String carLastRecordTime = car.findElement(By.xpath("td/span[2]")).getText();
      return carLastRecordTime;
   }
















}
