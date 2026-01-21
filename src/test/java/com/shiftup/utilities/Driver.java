package com.shiftup.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;

public class Driver {

    private Driver() {
    }

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browser = ConfigReader.getProperty("browser");

            switch (browser) {
                case "headless-chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    driverPool.set(new ChromeDriver(options));
                }
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "edge" -> driverPool.set(new EdgeDriver());
                case "safari" -> driverPool.set(new SafariDriver());
                default -> driverPool.set(new ChromeDriver());
            }

            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
