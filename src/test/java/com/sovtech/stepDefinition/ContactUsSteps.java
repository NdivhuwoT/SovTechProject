package com.sovtech.stepDefinition;

import com.sovtech.pageObjects.ContactUsPage;
import com.sovtech.userAction.UserAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.sovtech.utils.GlobalVariables.*;

public class ContactUsSteps {
    @Given("that user is on the contact us page")
    public void thatUserIsOnTheContactUsPage() {
        UserAction.browser(browser,url);
        ContactUsPage.objects();

    }

    @Then("user enter {string}")
    public void userEnter(String arg0) throws Exception {
        ContactUsPage.enterName(arg0);
    }

    @And("user select {string}")
    public void userSelect(String Size) throws Exception {
        ContactUsPage.selectSize(Size);
    }

    @And("select the {string} user is looking for")
    public void selectTheUserIsLookingFor(String Service) throws Exception {
        ContactUsPage.selectService(Service);
    }

    @And("user click on the check box to receive other communications")
    public void userClickOnTheCheckBoxToReceiveOtherCommunications() throws Exception {
        ContactUsPage.clickCheckBox();
    }

    @And("user click on submit button")
    public void userClickOnSubmitButton() throws Exception {
        ContactUsPage.submit();
    }

    @Then("user Validate if {string} is displayed")
    public void userValidateIfIsDisplayed(String error) {
        ContactUsPage.validate(error);
    }
}
