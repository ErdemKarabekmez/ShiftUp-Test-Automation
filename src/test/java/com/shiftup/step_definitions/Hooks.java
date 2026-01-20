package com.shiftup.step_definitions;

import com.shiftup.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Test Başlıyor");
    }


    /**
     * Bu metot, her test senaryosundan sonra otomatik olarak çalışır.
     * Test başarısız olursa (fail) ekran görüntüsü alarak rapora ekler ve
     * her durumda tarayıcıyı güvenli bir şekilde kapatır.
     */
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
       Driver.closeDriver();
    }
}