package pagepattern;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.TestUtility;

public class HomePage  {
WebDriver driver;
TestUtility testUtility;
Actions actions;
String config = "config.properties";
String excelFile="test_data/testdata.xlsx";

@FindBy(css = ".default-input.o-header__search--input")
WebElement searchBox;
@FindBy(id = "onetrust-accept-btn-handler")
WebElement cookieMessage;


    private static final String PRODUCT_FINDER = "//span[text()='?']/preceding::a[2]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
    }
    String expectedTitle = TestUtility.readFromConfigProperties(config,"title");
    public boolean verifyHomePageOpened(){
        if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle)){
            System.out.println("Home page is opened");
            Log.info("Home page is opened");

            return true;
        }
        else{
            System.out.println("Home page could not open.");
            Log.error("Home page could not open");
            return false;
        }

    }
    public void cookieHandle(){
        testUtility.waitForElementPresent(cookieMessage);
        cookieMessage.click();
        testUtility.sleep(3);
        Log.info("cookies accepted");

    }
    public void typeProductNameInSearchBox(){
        testUtility.waitForElementPresent(searchBox);
        searchBox.sendKeys(testUtility.readFromExcelCell(excelFile,"ProductName",0,0));
        Log.info("Product Name is Written in Search Box");

    }
    public void cleanSearchBox(){
        testUtility.waitForElementPresent(searchBox);
        actions.moveToElement(searchBox).doubleClick().sendKeys(Keys.BACK_SPACE)
                .build().perform();
        Log.info("product name is cleaned");

    }
    public void typeSecondProductNameInSearchBox(){
        testUtility.waitForElementPresent(searchBox);
        searchBox.sendKeys(testUtility.readFromExcelCell(excelFile,"ProductName",0,1)+Keys.ENTER);
        Log.info("Second Product Name is Written in Search Box");
        testUtility.sleep(3);

    }
    public ProductInfoPage clickRandomProduct(String productName) {
        WebElement product = driver.findElement(By.xpath(PRODUCT_FINDER.replace("?", productName)));
        testUtility.waitForElementPresent(product);
         product.click();
        Log.info("product selected");
        testUtility.sleep(3);
        return new ProductInfoPage(driver);

    }


}
