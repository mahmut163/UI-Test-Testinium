import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pagepattern.HomePage;
import pagepattern.ProductInfoPage;
import pagepattern.ShoppingCartPage;
import utility.TestBase;
import utility.TestUtility;
public class JunitTestRunner extends TestBase {
    final static String config = "config.properties";
    String productName=" Comfort Fit Haki Keten Gömlek";
    HomePage homePage=new HomePage(driver);
    ProductInfoPage productInfoPage;
    ShoppingCartPage shoppingCartPage;


    String productPrice ;
    @BeforeClass
    public static void setUp(){
        String url= TestUtility.readFromConfigProperties(config,"url");
    browserSetUp(url);
    }
    @Test()
    public void testBeymenWeb(){
        homePage.verifyHomePageOpened();
        homePage.cookieHandle();
        homePage.typeProductNameInSearchBox();
        homePage.cleanSearchBox();
        homePage.typeSecondProductNameInSearchBox();
        productInfoPage = homePage.clickRandomProduct(productName);
        productPrice = productInfoPage.getProductPrice();
       productInfoPage.productInformationWriteToFile();
       productInfoPage.addProductToShoppingCart();
        Assert.assertTrue(productInfoPage.isProductAdded());
        shoppingCartPage = productInfoPage.goToShoppingCart();
        Assert.assertEquals(productPrice,shoppingCartPage.getSalesPrice());
        shoppingCartPage.selectProductAmount();
        Assert.assertTrue(shoppingCartPage.getSelectedAmount().equals("2 adet"));
        shoppingCartPage.deleteProductButton();
        Assert.assertTrue(shoppingCartPage.isShoppingCartEmpty());



    }
    @AfterClass
    public static void tearDown(){
       closeBrowser();

    }
}
