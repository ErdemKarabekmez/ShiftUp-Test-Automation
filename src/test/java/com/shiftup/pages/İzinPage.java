package com.shiftup.pages;

import com.shiftup.utilities.Driver;
import com.shiftup.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class İzinPage {


    public İzinPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    /**
     * Bu sınıf içerisinde, İzin Yönetimi sayfasına ait tüm web elementleri (locatorlar) ve metotlar yer almaktadır.
     * Sayfadaki butonlar, giriş alanları ve tabloların adresleri (locatorlar) burada tanımlanmış ve yönetilmektedir.
     */
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

    @FindBy(xpath = "//md-datepicker[@ng-model='vm.row.Start_Date']//input")
    public WebElement izinBaşlamaTarihiAlanı;

    @FindBy(xpath = "//md-datepicker[@ng-model='vm.row.Plan_Finish_Date']//input")
    public WebElement izinBitişTarihiAlanı;

    @FindBy(xpath = "//md-datepicker[@ng-model='vm.row.Start_Working_Date']//input")
    public WebElement işeBaşlamaTarihiAlanı;

    @FindBy(id = "input_127")
    public WebElement açıklamaAlanı;

    @FindBy(xpath = "//button[@aria-label='Ekle']")
    public WebElement ekleButonu;

    @FindBy(xpath = "(//button[@aria-label='delete'])[1]")
    public WebElement tablodakiİlkİzniSilmeButonu;

    @FindBy(xpath = "//button[contains(@ng-click,'dialog.hide')]//span[text()='Sil']")
    public WebElement popupSilButonu;

    @FindBy(xpath = "//md-radio-button[@value='0' and @aria-label='Günlük']")
    public WebElement günlükRadioButonu;

    @FindBy(xpath = "//span[contains(text(), 'İzin tipi saatliktir')]")
    public WebElement saatlikIzinUyariMesaji;

    @FindBy(xpath = "//span[contains(text(), 'İzin tipi günlüktür')]")
    public WebElement günlükIzinUyariMesaji;

    @FindBy(xpath = "//span[contains(text(), 'Evlilik izni')]")
    public WebElement evlilikIzniUyariMesaji;

    @FindBy(xpath = "(//span[contains(text(), 'İzin hak edişiniz bulunmamaktadır.')])[2]")
    public WebElement izinHakedişiBulunmamaUyariMesaji;

    @FindBy(xpath = "//span[contains(text(), 'izin tanımlı')]")
    public WebElement izinTanımlıUyarıMesajı;

    @FindBy(xpath = "//md-radio-button[@value='1' and @aria-label='Saatlik']")
    public WebElement saatlikRadioButonu;

    @FindBy(xpath = "//small[contains(@class, 'ng-binding') and contains(@style, 'color:yellow')]")
    public WebElement kalanIzinBakiyeDegeri;

    @FindBy(xpath = "//*[contains(text(),'İşgünü')]/../b")
    public WebElement izinGunuSayisi;

    /**
     * Bu metot, test senaryosu içerisinde İzin Yönetimi sayfasına giderek
     * ilgili butonlara basar ve yeni izin ekleme formunu açar.
     */
    public void izinEkleMenüsüneGidiş() throws InterruptedException {

        //ReusableMethods.waitFor(5);
        ReusableMethods.waitVisibilityOf(izinYönetimiButonu);
        ReusableMethods.clickWithJS(izinYönetimiButonu);
        //ReusableMethods.waitFor(5);
        ReusableMethods.waitVisibilityOf(izinlerimButonu);
        izinlerimButonu.click();
        //ReusableMethods.clickWithJS(izinlerimButonu);
        ReusableMethods.waitFor(5);
        //ReusableMethods.waitVisibilityOf(hızlıİşlemlerButonu);
        ReusableMethods.clickWithJS(hızlıİşlemlerButonu);
        //ReusableMethods.waitFor(5);
        ReusableMethods.waitVisibilityOf(artıİşaretiButonu);
        ReusableMethods.clickWithJS(artıİşaretiButonu);
        // ReusableMethods.waitFor(5);

    }

    /**
     * Bu metot, parametre olarak gelen izin tipine göre dinamik bir XPath oluşturur
     * ve dropdowndan ilgili izin seçeneğini bularak JavaScript yardımıyla seçim işlemini gerçekleştirir.
     */
    public void izinTipiSeçme(String izinTipi) throws InterruptedException {

        String dinamikXpath = "//md-option//div[normalize-space()='" + izinTipi + "']";
        WebElement seçim = Driver.getDriver().findElement(By.xpath(dinamikXpath));
        ReusableMethods.waitFor(7);
        ReusableMethods.clickWithJS(seçim);

    }

    /**
     * Bu metot, tarihi alanındaki mevcut veriyi JavaScript kullanarak temizler,
     * yeni izin başlama tarihi verisini yazar.
     */
    public void tarihiTemizleVeYeniTarihYaz(WebElement element, String tarih) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
        js.executeScript("arguments[0].value = '" + tarih + "';", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);

        element.sendKeys(Keys.TAB);

    }

    /**
     * Bu metot, sayfadaki tüm izinlerin listelendiği tablonun ilk satırına gider,
     * ilgili sütundaki izin tipi verisini okur ve bu metni doğrulama yapmak üzere geri döndürür.
     */
    public String tümİzinlerTablosundailkSatırdakiİzinTipiniAl() {

        WebElement ilkSatirIzinTipi = Driver.getDriver().findElement(By.xpath("(//table//tr[1]//td)[4]"));
        ReusableMethods.waitVisibilityOf(ilkSatirIzinTipi);
        return ilkSatirIzinTipi.getText();

    }

    /**
     * Bu metot, izin tablosundaki ilk satırda bulunan silme butonuna tıklar,
     * ardından çıkan onay pop-up'ındaki silme butonunu tetikleyerek
     * ilgili izin kaydının sistemden kaldırılmasını sağlar.
     */
    public void tablodakiİlkİzniSil() throws InterruptedException {

        ReusableMethods.waitFor(4);
        // ReusableMethods.waitForClickability(tablodakiİlkİzniSilmeButonu);
        ReusableMethods.clickWithJS(tablodakiİlkİzniSilmeButonu);
        //ReusableMethods.waitForClickability(popupSilButonu);
        ReusableMethods.clickWithJS(popupSilButonu);
        ReusableMethods.waitFor(6);
    }

    /**
     * Bu metot, sayfadaki izin bakiye bilgisini metin olarak alır, içindeki rakam ve nokta dışındaki
     * tüm karakterleri temizler ve sonucu matematiksel işlemler
     * yapılabilmesi için ondalıklı sayı (double) formatında döndürür.
     */
    public double bakiyeHesapla() {

        String bakiyeMetni = kalanIzinBakiyeDegeri.getText();
        String temizBakiye = bakiyeMetni.replaceAll("[^0-9.-]", "");
        return Double.parseDouble(temizBakiye);

    }

    /**
     * Bu metot, sayfada metin olarak görünen izin gün sayısını alır,
     * bu metni tam sayı (integer) formatına dönüştürür ve testlerde
     * sayısal karşılaştırmalar yapılabilmesi için geri döndürür.
     */
    public int izinGunSayisi() {

        return Integer.parseInt(izinGunuSayisi.getText());

    }

}
