package com.shiftup.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/features",
        glue = "com.shiftup.step_definitions",
        dryRun = false,
        tags = "@HesaplamaKontrolu"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /*
       Bu metot, testlerin PARALEL çalışmasını sağlar.
       parallel = true olması gereklidir.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }
}
