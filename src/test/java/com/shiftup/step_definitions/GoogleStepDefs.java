package com.shiftup.step_definitions;

import com.shiftup.utilities.ConfigReader;
import com.shiftup.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class GoogleStepDefs {

    @Given("kullanici google anasayfasina gider")
    public void kullanici_google_anasayfasina_gider() {
        // Driver class'ımız üzerinden config dosyasındaki url'e gidiyoruz
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @Then("sayfa basliginin {string} oldugunu dogrular")
    public void sayfa_basliginin_oldugunu_dogrular(String beklenenBaslik) {
        // Tarayıcıdaki gerçek başlığı alıyoruz
        String gercekBaslik = Driver.getDriver().getTitle();

        // TestNG ile doğrulama (Assertion) yapıyoruz
        Assert.assertEquals(gercekBaslik, beklenenBaslik);
    }
}