import config.PropertyLoader;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webdrivers.BrowserFactory;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class SimpleTests {

    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private Logger logger = LogManager.getLogger(SimpleTests.class);
    private WebDriver driver;

    @BeforeTest
    public void setUp() {

        logger.info("Запускаю веб драйвер");
//        driver = BrowserFactory.getInstance(PropertyLoader.getProperty("browser.name"), PropertyLoader.getProperty("browser.lang"));
        driver = BrowserFactory.getInstance(cfg.browserName(), cfg.browserLang());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Разворачиваю браузер на весь экран");
        driver.manage().window().maximize();
    }

    @Test
    public void openMainPageTest() {

        logger.info("Открываю главную страницу сайта");
//        driver.get(PropertyLoader.getProperty("site.url"));
        driver.get(cfg.url());
        logger.info("Проверяю по title, что открыта главная страница сайта");
        assertTrue(driver.findElements(By.xpath("//title[.='Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям']")).size() > 0, "Главная страница не открыта");
    }

    @AfterTest
    public void stop() {
        driver.quit();
    }
}
