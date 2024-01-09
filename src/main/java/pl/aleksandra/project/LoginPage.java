package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    @FindBy(id = "field-email")
    private WebElement signInEmailInput;

    @FindBy(id = "field-password")
    private WebElement signInPasswordInput;

    @FindBy(id = "submit-login")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MyAccountPage signInAs(String email, String password) {
        signInEmailInput.clear();
        signInEmailInput.sendKeys(email);

        signInPasswordInput.clear();
        signInPasswordInput.sendKeys(password);
        signInButton.click();

        return new MyAccountPage(driver);
    }

}
