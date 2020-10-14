package webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BrowserFactory {

    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String LANG_RU = "ru";
    public static final String LANG_EN = "en";

    private static WebDriver webDriver = null;

    public static WebDriver getInstance(String browser, String lang) {

        if (webDriver != null) {
            return webDriver;
        }

        if (CHROME.equalsIgnoreCase(browser)) {

            if (LANG_EN.equalsIgnoreCase(lang)) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(options);

            } else if (LANG_RU.equalsIgnoreCase(lang)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("–-lang=ru");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(options);
            }

        } else if (FIREFOX.equalsIgnoreCase(browser)) {

            if (LANG_EN.equalsIgnoreCase(lang)) {

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "en");
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver((Capabilities) profile);

            } else if (LANG_RU.equalsIgnoreCase(lang)) {

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "ru");
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver((Capabilities) profile);
            }

        }

        return webDriver;
    }
}