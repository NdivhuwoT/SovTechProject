package com.sovtech.testRunner;

import com.sovtech.userAction.UserAction;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featureFile",
        glue = "com.sovtech.stepDefinition",
        plugin = {"pretty"},
        monochrome = true
)

public class TestRunner {
    @BeforeClass
    public static void report(){
        UserAction.createReport();
    }

    @AfterClass
    public static void quit() throws Exception {
        UserAction.savereport();
        UserAction.driver.quit();
        UserAction.launchResultFile();
    }
}
