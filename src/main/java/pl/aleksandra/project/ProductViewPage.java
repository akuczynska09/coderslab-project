package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class ProductViewPage {

    private WebDriver driver;

    private Select selectSize;

    @FindBy(id = "group_1")
    private WebElement selectSizeInput;

    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]")
    private WebElement increaseProductQuantity;

    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[2]")
    private WebElement decreaseProductQuantity;

    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")
    private WebElement addToCardButton;

    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")
    private WebElement proceedToCardButton;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/button")
    private WebElement continueShoppingButton;

    public ProductViewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

        selectSize = new Select(selectSizeInput);
    }

    public String getProductDiscountText() {
        return driver.findElement(By.className("discount-percentage")).getText();
    }

    public void selectSize(String size) {
        selectSize.selectByVisibleText(size);
    }

    public void selectQuantity(Integer quantity) throws InterruptedException {
        for (int i = 1; i < quantity; i++) { //przejście przez pętlę i wybór ilości produktu
            TimeUnit.SECONDS.sleep(1); // należy poczekać pomiędzy każdorazowym kliknięciem przycisku
            increaseProductQuantity.click();
        }
    }

    public ShoppingCartPage addToCard(boolean processToCheckOut) {
        //klikamy na add to card przycisk
        addToCardButton.click();

        if (processToCheckOut) {
            //klikamy na proceed to card przycisk
            proceedToCardButton.click();

            return new ShoppingCartPage(driver);
        }

        //klikamy na continue shopping przycisk
        continueShoppingButton.click();

        return null;
    }
}
