package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressesPage {

    public WebDriver driver;

    @FindBy(xpath = "//a[@data-link-action=\"add-address\"]")
    private WebElement createAddressLink;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddAddressPage addAddress() {

        //kliknie w Create address
        this.createAddressLink.click();

        return new AddAddressPage(driver);
    }
}
