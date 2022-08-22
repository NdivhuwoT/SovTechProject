package com.sovtech.pageObjects;

import com.sovtech.userAction.UserAction;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends UserAction {

    @FindBy(how = How.NAME, using = "your_name")
    public static WebElement Name;

    @FindBy(how = How.NAME, using = "numemployees")
    public static WebElement Company_Size;

    @FindBy(how = How.NAME, using = "what_kind_of_problem_is_your_business_currently_facing_")
    public static WebElement Service;

    @FindBy(how = How.ID, using = "LEGAL_CONSENT.subscription_type_10841063-c2e387f9-4bd8-496f-ab2a-81fbbc31712a")
    public static WebElement CheckBox;

    @FindBy(how = How.XPATH, using = "//input[@value='Submit']")
    public static WebElement Submit;

    @FindBy(how = How.XPATH, using = "//label[@class='hs-main-font-element']")
    public static WebElement Error;

    @FindBy(how = How.XPATH, using = "//iframe[@title='Form 0']")
    public static WebElement Frame;

    public static void objects(){

        PageFactory.initElements(driver, ContactUsPage.class);
        switchiframe(Frame);
    }

    public static void enterName(String name) throws Exception{
        sendKey(Name,name);
    }

    public static void selectSize(String company) throws Exception {
        select("Company_Size",Company_Size,company);
    }

    public static void selectService(String service) throws Exception{
        select("Service",Service,service);
    }

    public static void clickCheckBox() throws Exception {
        scrollTo(CheckBox);
        click(CheckBox);
    }

    public static void submit() throws Exception {
        click(Submit);
    }

    public static void validate(String Validated_Text){
        validateObject(Error, Validated_Text);
    }

}
