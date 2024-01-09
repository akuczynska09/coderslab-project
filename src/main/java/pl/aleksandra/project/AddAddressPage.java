package pl.aleksandra.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddAddressPage {

    public WebDriver driver;

    @FindBy(id = "field-alias")
    private WebElement aliasInput;

    @FindBy(id = "field-firstname")
    private WebElement firstnameInput;

    @FindBy(id = "field-lastname")
    private WebElement lastnameInput;

    @FindBy(id = "field-company")
    private WebElement companyInput;

    @FindBy(id = "field-vat_number")
    private WebElement vatNumberInput;

    @FindBy(id = "field-address1")
    private WebElement addressInput;

    @FindBy(id = "field-address2")
    private WebElement addressComplementInput;

    @FindBy(id = "field-city")
    private WebElement cityInput;

    @FindBy(id = "field-postcode")
    private WebElement postcodeInput;

    @FindBy(id = "field-country")
    private WebElement countryInput;

    @FindBy(id = "field-phone")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/footer/button")
    private WebElement submitButton;

    public AddAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setAlias(String alias) {
        aliasInput.clear();
        aliasInput.sendKeys(alias);
    }

    public void setFirstname(String firstname) {
        firstnameInput.clear();
        firstnameInput.sendKeys(firstname);
    }

    public void setLastname(String lastname) {
        lastnameInput.clear();
        lastnameInput.sendKeys(lastname);
    }

    public void setCompany(String company) {
        companyInput.clear();
        companyInput.sendKeys(company);
    }

    public void setVatNumber(String vatNumber) {
        vatNumberInput.clear();
        vatNumberInput.sendKeys(vatNumber);
    }

    public void setAddress(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
    }

    public void setAddressComplement(String addressComplement) {
        addressComplementInput.clear();
        addressComplementInput.sendKeys(addressComplement);
    }

    public void setCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void setPostCode(String postcode) {
        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);
    }

    public void setPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void submit() {
        submitButton.click();
    }
}
