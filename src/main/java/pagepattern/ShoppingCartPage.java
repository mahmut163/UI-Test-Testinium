package pagepattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import utility.Log;
import utility.ScreenShotUtility;
import utility.TestUtility;

import java.util.List;
import java.util.Locale;

public class ShoppingCartPage {
    WebDriver driver;
    TestUtility testUtility;
    ScreenShotUtility screenShotUtility;
    String productQuantity;

    @FindBy(css = ".m-productPrice__salePrice")
    WebElement salePrice;
    @FindBy(css = ".a-selectControl.-small")
    WebElement productAmountDropDown;
    @FindBy(css = ".m-basket__quantity")
    WebElement amountField;
    @FindAll(@FindBy(css = ".a-selectControl.-small>option"))
    WebElement quantityList;
    @FindBy(css = ".m-basket__remove.btn-text")
    WebElement deleteProductButton;
    @FindBy(xpath = "//div[@id=\"emtyCart\"]//strong[@class=\"m-empty__messageTitle\"]")
    WebElement deleteMessage;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        screenShotUtility = new ScreenShotUtility();

    }

    public String getSalesPrice() {
        testUtility.waitForElementPresent(salePrice);
        Log.info("get sales price of the product");
        return salePrice.getText();
    }

    public  void selectProductAmount() {
        Select select = getAmountDropDown();
        select.selectByValue("2");
        testUtility.sleep(2);
        screenShotUtility.takeScreenshot("image", "product_amount", driver);

    }

    private Select getAmountDropDown(){
        testUtility.waitForElementPresent(productAmountDropDown);
        return new Select(productAmountDropDown);
    }

    public String getSelectedAmount(){
        return getAmountDropDown().getFirstSelectedOption().getText();
    }

    public void deleteProductButton(){
        testUtility.waitForElementPresent(deleteProductButton);
        deleteProductButton.click();
        testUtility.sleep(2);
        Log.info("product deleted");
    }
    public boolean isShoppingCartEmpty(){

        testUtility.waitForElementPresent(deleteMessage);
        if(deleteMessage.getText().equalsIgnoreCase("Sepetinizde Ürün Bulunmamaktadır")){
            Log.info("shopping cart is empty");
            return true;
        } else {
            screenShotUtility.takeScreenshot("image", "delete-result", driver);
            Log.error("shopping cart is not empty,please check it again");
            return false;
        }

    }
}

