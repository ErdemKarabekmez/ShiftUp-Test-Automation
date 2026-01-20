package com.shiftup.step_definitions;

import com.shiftup.pages.LoginPage;
import com.shiftup.pages.İzinPage;
import com.shiftup.utilities.Driver;
import com.shiftup.utilities.ReusableMethods;
import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;


public class İzinStepDefinitions {

    LoginPage loginPage = new LoginPage();
    İzinPage i̇zinPage = new İzinPage();


    @Given("Kullanıcı geçerli bilgilerle login olur")
    public void kullanıcı_geçerli_bilgilerle_login_olur() throws InterruptedException {

        loginPage.geçerliBilgilerleLoginOlma();

    }

    @Given("Kullanıcı izin ekle menüsüne gider")
    public void kullanıcı_izin_ekle_menüsüne_gider() throws InterruptedException {

        i̇zinPage.izinEkleMenüsüneGidiş();

    }


    @When("Kullanıcı açılan pencerede izin tipi dropdowndan {string} seçeneğini seçer")
    public void kullanıcı_açılan_pencerede_izin_tipi_dropdowndan_seçeneğini_seçer(String izinTipi) throws InterruptedException {

        ReusableMethods.waitVisibilityOf(i̇zinPage.izinTipiDropdown);
        ReusableMethods.clickWithJS(i̇zinPage.izinTipiDropdown);
        ReusableMethods.waitFor(3);
        i̇zinPage.izinTipiSeçme(izinTipi);

    }

    @When("Kullanıcı iznin başlama tarihini {string} olarak seçer")
    public void kullanıcı_iznin_başlama_tarihini_olarak_seçer(String başlamaTarihi) throws InterruptedException {

        ReusableMethods.waitFor(6);
        //ReusableMethods.waitVisibilityOf(i̇zinPage.izinBaşlamaTarihiAlanı);
        ReusableMethods.clickWithJS(i̇zinPage.izinBaşlamaTarihiAlanı);
        i̇zinPage.tarihiTemizleVeYeniTarihYaz(i̇zinPage.izinBaşlamaTarihiAlanı, başlamaTarihi);
        ReusableMethods.waitFor(3);

    }

    @When("Kullanıcı iznin bitiş tarihini {string} olarak seçer")
    public void kullanıcı_iznin_bitiş_tarihini_olarak_seçer(String bitişTarihi) throws InterruptedException {

        ReusableMethods.waitFor(5);
        ReusableMethods.waitVisibilityOf(i̇zinPage.izinBitişTarihiAlanı);
        ReusableMethods.clickWithJS(i̇zinPage.izinBitişTarihiAlanı);
        i̇zinPage.tarihiTemizleVeYeniTarihYaz(i̇zinPage.izinBitişTarihiAlanı, bitişTarihi);

    }

    @When("Kullanıcı açıklama alanına {string} yazar")
    public void kullanıcı_açıklama_alanına_yazar(String açıklama) throws InterruptedException {

        ReusableMethods.waitFor(3);
        ReusableMethods.waitVisibilityOf(i̇zinPage.açıklamaAlanı);
        i̇zinPage.açıklamaAlanı.sendKeys(açıklama);

    }

    @When("Kullanıcı ekle butonuna tıklar")
    public void kullanıcı_ekle_butonuna_tıklar() throws InterruptedException {

        ReusableMethods.waitVisibilityOf(i̇zinPage.ekleButonu);
        ReusableMethods.waitFor(5);
        i̇zinPage.ekleButonu.click();

    }

    @Then("Kullanıcı {string} oluşturulduğunu doğrular")
    public void kullanıcı_oluşturulduğunu_doğrular(String izinTipi) throws InterruptedException {

        ReusableMethods.waitFor(5);
        ReusableMethods.sayfayıYenile();
        ReusableMethods.waitFor(7);
        Assert.assertEquals(i̇zinPage.tümİzinlerTablosundailkSatırdakiİzinTipiniAl(), izinTipi);

    }


    @Then("Kullanıcı yeni oluşturduğu izni tablodan siler")
    public void kullanıcı_yeni_oluşturduğu_izni_tablodan_siler() throws InterruptedException {

        ReusableMethods.waitFor(5);
        i̇zinPage.tablodakiİlkİzniSil();

    }

    @Then("Kullanıcı iznin başlama tarihinin ilk geçerli gün olan {string} olarak güncellendiğini doğrular")
    public void kullanıcı_iznin_başlama_tarihinin_ilk_geçerli_gün_olan_olarak_güncellendiğini_doğrular(String izinBaşlamaTarihi) throws InterruptedException {

        ReusableMethods.waitFor(1);
        String güncelTarih = (String) ((JavascriptExecutor) Driver.getDriver()).executeScript("return arguments[0].value;", i̇zinPage.izinBaşlamaTarihiAlanı);
        System.out.println("Sistemin düzelttiği tarih: " + güncelTarih);
        Assert.assertEquals(güncelTarih, izinBaşlamaTarihi, "Hata: Sistem tarihi otomatik olarak düzeltmedi!");

    }

    @When("Kullanıcı açılan pencerede Günlük radio butonunun seçili olduğunu doğrular")
    public void kullanıcı_açılan_pencerede_günlük_radio_butonunun_seçili_olduğunu_doğrular() throws InterruptedException {

        ReusableMethods.waitFor(2);
        String isChecked = i̇zinPage.günlükRadioButonu.getAttribute("aria-checked");
        Assert.assertEquals(isChecked, "true", "Hata: Günlük butonu seçili değil!");

    }

    @Then("Kullanıcı ekranda izin tipi saatliktir uyarısını gördüğünü doğrular")
    public void kullanıcı_ekranda_izin_tipi_saatliktir_uyarısını_gördüğünü_doğrular() {

        String actualMesaj = i̇zinPage.saatlikIzinUyariMesaji.getText();
        String expectedMesaj = "İzin tipi saatliktir, günlük seçilemez !";
        Assert.assertEquals(actualMesaj, expectedMesaj, "Hata: Saatlik izin uyarısı görünmüyor!");

    }

    @When("Kullanıcı açılan pencerede Saatlik radio butonunu seçer")
    public void kullanıcı_açılan_pencerede_saatlik_radio_butonunu_seçer() {

        i̇zinPage.saatlikRadioButonu.click();

    }

    @Then("Kullanıcı ekranda izin tipi günlüktür uyarısını gördüğünü doğrular")
    public void kullanıcı_ekranda_izin_tipi_günlüktür_uyarısını_gördüğünü_doğrular() {

        String actualMesaj = i̇zinPage.günlükIzinUyariMesaji.getText();
        String expectedMesaj = "İzin tipi günlüktür, saatlik seçilemez !";
        Assert.assertEquals(actualMesaj, expectedMesaj, "Hata: Günlük izin uyarısı görünmüyor!");

    }

    @Then("Kullanıcı ekranda Evlilik izni en fazla üç gün olabilir uyarısını doğrular")
    public void kullanıcı_ekranda_evlilik_izni_en_fazla_üç_gün_olabilir_uyarısını_doğrular() {

        String actualMesaj = i̇zinPage.evlilikIzniUyariMesaji.getText();
        System.out.println(actualMesaj);
        String expectedMesaj = "Evlilik izni en fazla 3 gün kullanılabilir";
        Assert.assertEquals(actualMesaj, expectedMesaj, "Hata: Evlilik izni uyarısı görünmüyor!");

    }

    @Then("Kullanıcı Ekle butonunun disabled olduğunu doğrular")
    public void kullanıcı_ekle_butonunun_disabled_olduğunu_doğrular() {

        String ariaDisabled = i̇zinPage.ekleButonu.getAttribute("aria-disabled");
        Assert.assertEquals(ariaDisabled, "true", "BUG: Ekle butonu disabled değildir!");

    }

    @When("Kullanıcı kalan bakiyenin eksiye düştüğünü doğrular")
    public void kullanıcı_kalan_bakiyenin_eksiye_düştüğünü_doğrular() throws InterruptedException {

        ReusableMethods.waitFor(7);
        Assert.assertTrue(i̇zinPage.bakiyeHesapla() < 0, "Hata: Kalan izin bakiyesi sayısal olarak 0'dan küçük değil!");

    }

    @When("Kullanıcı ekranda izin hakedişiniz bulunmamaktadır uyarısını gördüğünü doğrular")
    public void kullanıcı_ekranda_izin_hakedişiniz_bulunmamaktadır_uyarısını_gördüğünü_doğrular() throws InterruptedException {

        ReusableMethods.waitFor(4);
        String actualMesaj = i̇zinPage.izinHakedişiBulunmamaUyariMesaji.getText();
        System.out.println("actual mesaj" + actualMesaj);
        Assert.assertTrue(actualMesaj.contains("İzin hak edişiniz bulunmamaktadır."), "Hata: İzin hakediş uyarısı beklenen metni içermiyor!");

    }

    @Then("Kullanıcı alt panelde toplam {int} İşgünü kullanıldığını doğrular")
    public void kullanıcı_alt_panelde_toplam_işgünü_kullanıldığını_doğrular(int işgünü) throws InterruptedException {

        ReusableMethods.waitFor(5);
        Assert.assertEquals(i̇zinPage.izinGunSayisi(), işgünü, "Hata: Hesaplanan iş günü beklenenden farklı!");

    }

    @Then("Kullanıcı işe başlama tarihinin {string} olarak güncellendiğini doğrular")
    public void kullanıcı_işe_başlama_tarihinin_olarak_güncellendiğini_doğrular(String işeBaşlamaTarihi) throws InterruptedException {

        ReusableMethods.waitFor(1);
        String güncelTarih = (String) ((JavascriptExecutor) Driver.getDriver()).executeScript("return arguments[0].value;", i̇zinPage.işeBaşlamaTarihiAlanı);
        System.out.println("Sistemin düzelttiği tarih: " + güncelTarih);
        Assert.assertEquals(güncelTarih, işeBaşlamaTarihi, "Hata: Sistem tarihi otomatik olarak düzeltmedi!");

    }

    @Then("Kullanıcı aynı tarihlerde yeni izin eklemek için tekrar izin ekle ekranına gelir")
    public void kullanıcı_aynı_tarihlerde_yeni_izin_eklemek_için_tekrar_izin_ekle_ekranına_gelir() throws InterruptedException {

        ReusableMethods.waitFor(5);
        ReusableMethods.clickWithJS(i̇zinPage.hızlıİşlemlerButonu);
        ReusableMethods.waitFor(5);
        ReusableMethods.clickWithJS(i̇zinPage.artıİşaretiButonu);
        ReusableMethods.waitFor(5);

    }

    @Then("Kullanıcı ekranda izin tanımlı uyarısını gördüğünü doğrular")
    public void kullanıcı_ekranda_izin_tanımlı_uyarısını_gördüğünü_doğrular() throws InterruptedException {

        ReusableMethods.waitFor(4);
        String actualMesaj = i̇zinPage.izinTanımlıUyarıMesajı.getText();
        System.out.println("actual mesaj" + actualMesaj);
        String expectedMesaj = "Belirtilen tarihler arasında izin tanımlı olduğu için devam edilemez!";
        Assert.assertEquals(actualMesaj, expectedMesaj, "Hata: İzin tanımlı uyarısı görünmüyor.");

    }
}





















