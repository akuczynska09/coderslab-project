package pl.aleksandra.project;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "_desktop_logo")
    private WebElement homeButton;

    @FindBy(id = "address-link")
    private WebElement addAddressLink;

    @FindBy(id = "addresses-link")
    private WebElement addressesLink;

    public AddAddressPage goToAddAddressPage() {
        try {
            this.addAddressLink.click();

            return new AddAddressPage(driver);
        } catch (NoSuchElementException e) {
            return this.goToAddressesPage().addAddress();
        }
    }

    public AddressesPage goToAddressesPage() {
        this.addressesLink.click(); // klika na kafelek addresses

        return new AddressesPage(driver); // od razu zwraca object
    }

    public void goToMainPage() {
        homeButton.click();
    }
}
