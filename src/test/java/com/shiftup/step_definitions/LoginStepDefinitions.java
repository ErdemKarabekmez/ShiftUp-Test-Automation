package com.shiftup.step_definitions;

import com.shiftup.pages.LoginPage;
import com.shiftup.utilities.ConfigReader;
import com.shiftup.utilities.Driver;
import com.shiftup.utilities.ReusableMethods;
import io.cucumber.java.en.*;
import org.testng.Assert;


public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("Kullanıcı login sayfasına gider")
    public void kullanıcı_login_sayfasına_gider() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }
    @When("Kullanıcı kullanıcı adını girer")
    public void kullanıcı_kullanıcı_adını_girer() throws InterruptedException {

        loginPage.kullanıcıAdıInputBox.sendKeys(ConfigReader.getProperty("username"));

    }
    @When("Kullanıcı Şifreyi girer")
    public void kullanıcı_şifreyi_girer() throws InterruptedException {

        ReusableMethods.waitFor(5);
        loginPage.şifreInputBox.sendKeys(ConfigReader.getProperty("password"));

    }
    @When("Kullanıcı Giriş butonuna tıklar")
    public void kullanıcı_giriş_butonuna_tıklar() {

        loginPage.girisButonu.click();

    }
    @Then("Kullanıcı sisteme Login olduğunu doğrular")
    public void kullanıcı_sisteme_login_olduğunu_doğrular() {

        String actualMesaj = loginPage.girişBaşarılıMesajı.getText();
        String expectedMesaj = "Giriş Başarılı.";
        System.out.println(actualMesaj);

        Assert.assertEquals( actualMesaj, expectedMesaj);
        Assert.assertTrue( Driver.getDriver().getCurrentUrl().contains("home"),"Giriş Başarısız! URL home kelimesi içermiyor.");

    }
}


