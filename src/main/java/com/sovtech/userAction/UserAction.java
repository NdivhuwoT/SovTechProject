package com.sovtech.userAction;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sovtech.utils.GlobalVariables;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.sovtech.utils.GlobalVariables.*;

public class UserAction {

    public static WebDriver driver;
    private static WebDriverWait wait;
    public static ExtentReports report;
    public static ExtentTest logger;


    public static WebDriver browser(String browsers, String url) {

        boolean browserFound = true;

        if ("CHROME".equals(browsers.toUpperCase())){

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver =new ChromeDriver(options);

        }
        else if(browsers.toUpperCase().equals("EDGE")){

            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();

            driver = new EdgeDriver(options);
        }
        else{
            browserFound = false;
        }

        if (browserFound){
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(max_wait);
            scenario("Clicking First Available Job");
            logger.log(Status.PASS,browsers+" Browser Opened Successfully");
            driver.get(url);
            return driver;
        }else {
            logger.log(Status.FAIL, browsers+" Browser Note Opened");
            return null;
        }
    }

    public static void click(WebElement objName) throws Exception{
        try{
            wait = new WebDriverWait(driver, max_wait);
            wait.until(ExpectedConditions.elementToBeClickable(objName));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'",objName);
            screenShot("Click ");
            objName.click();
            logger.log(Status.PASS,"Clicked Successfully");
        }catch (Exception e){
            logger.log(Status.FAIL,"Click Unsuccessful "+objName);
            //screenShot("Click_Error ");
        }

    }

    public static void sendKey(WebElement objName, String data) throws Exception {
        try{
            wait = new WebDriverWait(driver, max_wait);
            wait.until(ExpectedConditions.visibilityOf(objName));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid blue'",objName);
            objName.clear();
            objName.sendKeys(data);
            logger.log(Status.PASS,"Data entered Successfully");

        }catch (Exception e){
            logger.log(Status.FAIL,"Data Input Failed");
           // screenShot("Input Failed ");
        }

    }

    public static void validateObject(WebElement objName, String TextToBeVerified){

        String ActualText = objName.getText();

        if(ActualText.equals(TextToBeVerified)) {
            Assert.assertTrue("Text Found", ActualText.contains(TextToBeVerified));
            logger.log(Status.PASS,"Same Text");
        }else {
            Assert.assertFalse("Text Not found", false);
            logger.log(Status.FAIL,"Text Is not the same");
        }
    }

    public static void scrollTo(WebElement elementLocator) throws Exception {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            js.executeScript("arguments[0].scrollIntoView()", elementLocator);
            logger.log(Status.PASS,"Scroll to Element passed");
        }
        catch (Exception e) {
            logger.log(Status.FAIL,"Scroll to Element Failed");
            screenShot("Scroll_Fail ");
        }
    }

    public static String screenShot(String fieldName) throws Exception {

        try{
            Date date = new Date();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy_MM_dd");
            String imagename = screenshot_path+"/"+dateFormat2.format(date)+"/"+fieldName+dateFormat1.format(date)+".png";
            File screenfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenfile, new File(imagename));
            return imagename;
        }
        catch (Exception e){
            logger.log(Status.INFO,"Unable to Capture ScreenShot");
        }
        return null;
    }

    public static void createReport(){

        report = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(report_path);
        report.attachReporter(spark);
    }

    public static void scenario(String Stepname){

        logger=report.createTest(Stepname);
    }

    public static void savereport() throws Exception{
        try{
            report.flush();

        }catch(Exception e){
            logger.log(Status.INFO, "Couldnt't save");
        }
    }

    public static void select(String UIName, WebElement objTechName, String data) throws Exception{
        try{

            final Select selectBox = new Select(objTechName);

            selectBox.selectByVisibleText(data);

            logger.log(Status.PASS, UIName+" Dropdown selected successfully");


        }catch(Exception e){

            logger.log(Status.FAIL, "Error in selecting dropdown "+UIName);

            screenShot(UIName);

        }
    }

    public static void selectbyindex(String UIName, WebElement objTechName, int data) throws Exception{
        try{

            final Select selectBox = new Select(objTechName);

            selectBox.selectByIndex(data);

            logger.log(Status.PASS, UIName+" dropdown selected successfully");

        }catch(Exception e){

            logger.log(Status.FAIL,UIName+" Error in selecting dropdown");

            screenShot(UIName);

        }
    }

    public static void switchframe(String framename){

        try{
            driver.switchTo().defaultContent();
            driver.switchTo().frame(framename);
        }catch(Exception e){
            logger.log(Status.INFO, "No frame found");
        }

    }

    public static void switchiframe(WebElement objTechName){

        try {
            driver.switchTo().frame(objTechName);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void launchResultFile()
    {
        try
        {

            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"+" "+"C:/Users/c804666/IdeaProjects/SovTechProject/target/Extent_Report/Report.html");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

}
