package SatelliteApp.pages.tracking;

import Base.helpers.MySeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Base.BaseSeleniumTest;
import org.openqa.selenium.support.ui.Select;

public class LeftPanel_TabHistory extends BaseSeleniumTest {

    @FindBy(id = "left_panel_history_reports_date_from")
    private WebElement inputDateFrom;

    @FindBy(id = "left_panel_history_reports_date_to")
    private WebElement inputDateTo;

    @FindBy(id = "left_panel_history_reports_hour_from")
    private WebElement selectHoursFrom;

    @FindBy(id = "left_panel_history_reports_hour_to")
    private WebElement selectHoursTo;

    @FindBy(id = "left_panel_history_reports_minute_from")
    private WebElement selectMinutesFrom;

    @FindBy(id = "left_panel_history_reports_minute_to")
    private WebElement selectMinutesTo;

    @FindBy(className ="ui-datepicker-year" )
    private WebElement selectYear;

    @FindBy(className ="ui-datepicker-month" )
    private WebElement selectMonth;

    @FindBy(xpath = "//input[@class='button icon-show icon']")
    private WebElement buttonShow;




    //DateTime Format: String "yy-MM-dd HH:mm"
    public void selectDateTimeFrom(String dateTimeFrom) {
        String year = ""+dateTimeFrom.charAt(0)
                        +dateTimeFrom.charAt(1)
                        +dateTimeFrom.charAt(2)
                        +dateTimeFrom.charAt(3);

        String month =""+dateTimeFrom.charAt(5)
                        +dateTimeFrom.charAt(6);
        Integer monthInt=Integer.parseInt(month);
        Integer monthIntMinus1 = monthInt-1;
        String monthConverted = monthIntMinus1.toString();

        String day =  ""+dateTimeFrom.charAt(8)
                        +dateTimeFrom.charAt(9);
        Integer dayInt=Integer.parseInt(day);
        String dayConverted = dayInt.toString();

        String hours = ""+dateTimeFrom.charAt(11)
                        +dateTimeFrom.charAt(12);

        String minutes=""+dateTimeFrom.charAt(14)
                        +dateTimeFrom.charAt(15);

        inputDateFrom.click();
        Select yearSelect = new Select(selectYear);
        yearSelect.selectByValue(year);
        Select monthSelect = new Select(selectMonth);
        monthSelect.selectByValue(monthConverted);
        driver.findElement(By.xpath("//a[text()='"+dayConverted+"']")).click();
        Select hoursSelect = new Select(selectHoursFrom);
        hoursSelect.selectByValue(hours);
        Select minutesSelect = new Select(selectMinutesFrom);
        minutesSelect.selectByValue(minutes);

    }


    //DateTime Format: String "yy-MM-dd HH:mm"
    public void selectDateTimeTo(String dateTimeTo){
        String year = ""+dateTimeTo.charAt(0)
                +dateTimeTo.charAt(1)
                +dateTimeTo.charAt(2)
                +dateTimeTo.charAt(3);

        String month =""+dateTimeTo.charAt(5)
                +dateTimeTo.charAt(6);
        Integer monthInt=Integer.parseInt(month);
        Integer monthIntMinus1 = monthInt-1;
        String monthConverted = monthIntMinus1.toString();

        String day =  ""+dateTimeTo.charAt(8)
                +dateTimeTo.charAt(9);
        Integer dayInt=Integer.parseInt(day);
        String dayConverted = dayInt.toString();

        String hours = ""+dateTimeTo.charAt(11)
                +dateTimeTo.charAt(12);

        String minutes=""+dateTimeTo.charAt(14)
                +dateTimeTo.charAt(15);

        inputDateTo.click();
        Select yearSelect = new Select(selectYear);
        yearSelect.selectByValue(year);
        Select monthSelect = new Select(selectMonth);
        monthSelect.selectByValue(monthConverted);
        driver.findElement(By.xpath("//a[text()='"+dayConverted+"']")).click();
        Select hoursSelect = new Select(selectHoursTo);
        hoursSelect.selectByValue(hours);
        Select minutesSelect = new Select(selectMinutesTo);
        minutesSelect.selectByValue(minutes);

    }

    public void showHistory(){
        buttonShow.click();
        MySeleniumMethods.waitForWebElementBy(By.xpath("//a[@href='#bottom_panel_msg']"),40,driver);
    }

    public LeftPanel_TabHistory(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;

    }
}
