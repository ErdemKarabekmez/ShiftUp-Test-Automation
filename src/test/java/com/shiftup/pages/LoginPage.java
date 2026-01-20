package com.shiftup.pages;

import com.shiftup.utilities.ConfigReader;
import com.shiftup.utilities.Driver;
import com.shiftup.utilities.ReusableMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {



    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     * Bu sınıf içerisinde, Login işlemlerine ait tüm web elementleri (locatorlar) ve metotlar yer almaktadır.
     * Sayfadaki butonlar, giriş alanları ve tabloların adresleri (locatorlar) burada tanımlanmış ve yönetilmektedir.
     */
    @FindBy(id = "username")
    public WebElement kullanıcıAdıInputBox;

    @FindBy(id = "password")
    public WebElement şifreInputBox;

    @FindBy(xpath = "//button[@class='md-raised md-primary full-width margin-left-0 margin-right-0 margin-top-10 margin-bottom-10 md-button ng-scope md-cyan-theme md-ink-ripple']")
    public WebElement girisButonu;

    @FindBy(id = "swal2-title")
    public WebElement girişBaşarılıMesajı;

    /**
     * Bu metot, test senaryosu başlangıcında yapılandırma dosyasındaki (config) URL'e gider
     * ve geçerli kullanıcı bilgileriyle sisteme giriş (login) işlemini gerçekleştirir.
     */
    public void geçerliBilgilerleLoginOlma() throws InterruptedException {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.waitFor(4);
        ReusableMethods.waitVisibilityOf(kullanıcıAdıInputBox);
        kullanıcıAdıInputBox.sendKeys(ConfigReader.getProperty("username"));
        // ReusableMethods.waitFor(3);
        ReusableMethods.waitVisibilityOf(şifreInputBox);
        şifreInputBox.sendKeys(ConfigReader.getProperty("password"));
        //ReusableMethods.waitFor(3);
        ReusableMethods.waitVisibilityOf(girisButonu);
        ReusableMethods.clickWithJS(girisButonu);

    }

}
