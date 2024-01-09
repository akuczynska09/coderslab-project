package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

public class MainPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"_desktop_user_info\"]/div/a")
    private WebElement signInButton;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goToLoginPage() {
        signInButton.click();
    }

    public ProductViewPage viewProduct(int productId) {
        //clicks on product and goes to its view page
        this.driver.findElement(By.xpath("//a[contains(@href, 'id_product=" + productId + "')]")).click();

       return new ProductViewPage(driver);
    }
}
