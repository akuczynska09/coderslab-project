package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage {

    private WebDriver driver;

    @FindBy(id = "group_1")
    private Select selectSize;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")
    private WebElement addToCardButton;

    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")
    private WebElement proceedToCheckOutButton;

    @FindBy(xpath = "//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")
    private WebElement confirmAddressButton;

    @FindBy(xpath = "//*[@id=\"js-delivery\"]/button")
    private WebElement confirmShippingMethodButton;

    @FindBy(xpath = "//*[@id=\"payment-confirmation\"]/div[1]/button")
    private WebElement placeOrderButton;

    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CheckOutPage selectAddress(String addressId) {
        driver.findElement(By.id(addressId)).findElement(By.className("radio-block")).click();

        confirmAddressButton.click();
        
        return this;
    }

    public CheckOutPage shipmentType(String shipmentAddressXPath) {
        WebElement element = driver.findElement(By.xpath(shipmentAddressXPath));

        element.click();

        confirmShippingMethodButton.click();

        return this;
    }

    public void placeOrder(String paymentTypeXPath) {
        WebElement element = driver.findElement(By.xpath(paymentTypeXPath));

        element.click();

        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        
        placeOrderButton.click();
    }
}
