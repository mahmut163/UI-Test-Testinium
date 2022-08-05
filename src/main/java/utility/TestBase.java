package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {
    public static WebDriver driver;

    static BrowserType browserType;

    public static void browserSetUp(String url) {
        String browserName = String.valueOf(browserType);
        switch (browserName) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions1 = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions1);
                break;

        }
        driver.manage().window().maximize();
        driver.get(url);
    }


    public static void closeBrowser() {
        driver.close();
        driver.quit();
    }
}



