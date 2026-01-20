package com.shiftup.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ReusableMethods {


    /**
     * Bu metot Selenium'un normal click() metodunun başarısız olduğu durumlarda,
     * JavaScript Executor kullanarak elemente doğrudan tıklama yapar.
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Bu metot Belirtilen süre (saniye) kadar testi statik olarak durdurur.
     * Not: Dinamik beklemelerin (Explicit Wait) yetersiz kaldığı
     * durumlarda tercih edilmiştir.
     */
    public static void waitFor(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    /**
     * Bu metot Mevcut tarayıcı sayfasını yeniler (refresh).
     */
    public static void sayfayıYenile(){
        Driver.getDriver().navigate().refresh();
    }

    /**
     * Bu metot, belirtilen elementin DOM üzerinde görünür hale gelmesi için
     * dinamik olarak maksimum 10 saniye bekler.
     */
    public static void waitVisibilityOf(WebElement element){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * Bu metot, belirtilen elementin tıklanabilir (clickable) olmasını maksimum 10 saniye bekler
     * ve element hazır olduğunda tıklama işlemini gerçekleştirir.
     */
    public static void waitForClickability(WebElement element){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }




}



