package pl.aleksandra.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")
    private WebElement proceedToCheckout;

    public ShoppingCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void proceedToCheckout() {
        proceedToCheckout.click();
    }

    public String getCartItemsText() {
        return driver.findElement(By.xpath("//*[@id=\"cart-subtotal-products\"]/span[1]")).getText();
    }

    public String getCartPrice() {
        return driver.findElement(By.xpath("//*[@id=\"cart-subtotal-products\"]/span[2]")).getText();
    }
}
