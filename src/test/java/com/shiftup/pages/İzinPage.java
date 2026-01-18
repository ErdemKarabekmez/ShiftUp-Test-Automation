package com.shiftup.pages;

import com.shiftup.utilities.Driver;
import com.shiftup.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class İzinPage {

    public İzinPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'İzin Yönetimi')]")
    public WebElement izinYönetimiButonu;

    @FindBy(xpath = "//ul[@class='drop-down-list ng-scope']/li[1]")
    public WebElement izinlerimButonu;

    @FindBy(xpath = "//div[@class='md-fab-toolbar-background']")
    public WebElement hızlıİşlemlerButonu;

    @FindBy(xpath = "//button[@aria-label='add new']")
    public WebElement artıİşaretiButonu;

    @FindBy(xpath = "(//md-select[@name='Vacation_Type'])[2]")
    public WebElement izinTipiDropdown;

    @FindBy (xpath = "//md-datepicker[@ng-model='vm.row.Start_Date']//input")
    public WebElement başlamaTarihiAlanı;

    @FindBy (xpath = "//md-datepicker[@ng-model='vm.row.Plan_Finish_Date']//input")
    public WebElement bitişTarihiAlanı;

    @FindBy (id = "input_127")
    public WebElement açıklamaAlanı;

    @FindBy (xpath = "//button[@aria-label='Ekle']")
    public WebElement ekleButonu;

    @FindBy(xpath = "(//button[@aria-label='delete'])[1]")
    public WebElement tablodakiİlkİzniSilmeButonu;

    @FindBy(xpath = "//button[contains(@ng-click,'dialog.hide')]//span[text()='Sil']")
    public WebElement popupSilButonu;

    @FindBy (xpath = "//md-radio-button[@value='0' and @aria-label='Günlük']")
    public WebElement günlükRadioButonu;

    @FindBy(xpath = "//span[contains(text(), 'İzin tipi saatliktir')]")
    public WebElement saatlikIzinUyariMesaji;

    @FindBy(xpath = "//span[contains(text(), 'İzin tipi günlüktür')]")
    public WebElement günlükIzinUyariMesaji;

    @FindBy(xpath = "//span[contains(text(), 'Evlilik izni')]")
    public WebElement evlilikIzniUyariMesaji;

    @FindBy(xpath = "(//span[contains(text(), 'İzin hak edişiniz bulunmamaktadır.')])[2]")
    public WebElement izinHakedişiBulunmamaUyariMesaji;

    @FindBy (xpath = "//md-radio-button[@value='1' and @aria-label='Saatlik']")
    public WebElement saatlikRadioButonu;

    @FindBy(xpath = "//small[contains(@class, 'ng-binding') and contains(@style, 'color:yellow')]")
    public WebElement kalanIzinBakiyeDegeri;

    public void izinEkleMenüsüneGidiş() throws InterruptedException {

        izinYönetimiButonu.click();
        izinlerimButonu.click();
        ReusableMethods.waitFor(5);
        ReusableMethods.clickWithJS(hızlıİşlemlerButonu);
        ReusableMethods.waitFor(5);
        ReusableMethods.clickWithJS(artıİşaretiButonu);
        ReusableMethods.waitFor(5);

    }

    public void izinTipiSeçme(String izinTipi){

        String dinamikXpath = "//md-option//div[normalize-space()='" + izinTipi + "']";
        WebElement seçim = Driver.getDriver().findElement(By.xpath(dinamikXpath));
        ReusableMethods.clickWithJS(seçim);

    }

    public void tarihiTemizleVeYeniTarihYaz(WebElement element, String tarih){

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
        js.executeScript("arguments[0].value = '" + tarih + "';", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);

        element.sendKeys(Keys.TAB);

    }

    public String tümİzinlerTablosundailkSatırdakiİzinTipiniAl(){

        WebElement ilkSatirIzinTipi = Driver.getDriver().findElement(By.xpath("(//table//tr[1]//td)[4]"));
        return ilkSatirIzinTipi.getText();

    }

    public void tablodakiİlkİzniSil() throws InterruptedException {

        ReusableMethods.clickWithJS(tablodakiİlkİzniSilmeButonu);
        ReusableMethods.clickWithJS(popupSilButonu);
        ReusableMethods.waitFor(5);
    }

    public double bakiyeHesapla(){

        String bakiyeMetni = kalanIzinBakiyeDegeri.getText();
        System.out.println("Görünen Bakiye: " + bakiyeMetni);
        String temizBakiye = bakiyeMetni.replaceAll("[^0-9.-]", "");

        double kalanIzin = Double.parseDouble(temizBakiye);
        System.out.println(temizBakiye);
        return kalanIzin;

    }

}
