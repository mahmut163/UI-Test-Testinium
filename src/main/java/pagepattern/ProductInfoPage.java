package pagepattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.TestUtility;

public class ProductInfoPage {
    WebDriver driver;
    TestUtility testUtility;
    String content;
    private static final String PRODUCT_FINDER = "//span[text()='?']";
    @FindBy(css = ".o-productDetail__title")
    WebElement productDetail;
    @FindBy(id = "priceNew")
    WebElement productPrice;
    @FindBy(xpath = "//span[@class=\"m-variation__item\" and text()=\"M\"]")
    WebElement productSizeM;
    @FindBy(id = "addBasket")
    WebElement addButton;
    @FindBy(css = ".m-notification.success")
    WebElement addedNotification;
    @FindBy(css = ".m-notification__button.btn")
    WebElement goToShoppingCartButton;


    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public void productInformationWriteToFile(){
        testUtility.waitForElementPresent(productDetail);
        content=productDetail.getText()+ "\n" + getProductPrice();
        testUtility.writeToFile("ProductInfo","productinformation"," .txt",content);
        Log.info("product information has been write to txt file");

    }


    public void addProductToShoppingCart(){
        testUtility.waitForElementPresent(productSizeM);
        productSizeM.click();
        testUtility.waitForElementPresent(addButton);
        addButton.click();
        Log.info("product added to shopping cart");
    }
    public boolean isProductAdded(){
        testUtility.sleep(2);
        return addedNotification.getText().contains("Ürün sepetinize eklenmiştir.");

    }
    public String getProductPrice(){
        testUtility.waitForElementPresent(productPrice);
        Log.info("get the product price");
        return productPrice.getText();
    }
    public ShoppingCartPage goToShoppingCart(){
        testUtility.waitForElementPresent(goToShoppingCartButton);
        goToShoppingCartButton.click();
        Log.info("go to shopping cart");
        return new ShoppingCartPage(driver);
    }
}
