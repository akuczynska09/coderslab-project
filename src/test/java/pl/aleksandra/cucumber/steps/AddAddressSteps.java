package pl.aleksandra.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.aleksandra.project.*;

import java.time.Duration;

import static junit.framework.TestCase.assertTrue;

public class AddAddressSteps {

    private WebDriver driver;

    private MyAccountPage myAccountPage;

    @Given("an open browser on store page and sign user")
    public void an_open_browser_on_store_page_and_sign_user() {


        // Logowanie na stworzonego wcześniej użytkownika

        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl/");

        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(this.driver);
        myAccountPage = loginPage.signInAs("gotzknpkqikttvzlym@cazlq.com", "8XgsB!QArxk!W7B");
    }

    @When("create new address with {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void create_new_address_with(String alias, String firstname, String lastname, String company, String vatNumber, String address, String addressComplement, String city, String postcode, String phone) {


        //użytkownik wejdzie na stronę https://mystore-testlab.coderslab.pl/index.php?controller=addresses
        // po kliknięciu w kafelek Adresses kliknie w kafelek Create new adress

        AddAddressPage addAddressPage = myAccountPage.goToAddressesPage().addAddress();

        //Wypelnienie formularza New adress (pobrane z tabeli Examples w Gherkinie)
        addAddressPage.setAlias(alias);
        addAddressPage.setFirstname(firstname);
        addAddressPage.setLastname(lastname);
        addAddressPage.setCompany(company);
        addAddressPage.setVatNumber(vatNumber);
        addAddressPage.setAddress(address);
        addAddressPage.setAddressComplement(addressComplement);
        addAddressPage.setCity(city);
        addAddressPage.setPostCode(postcode);
        addAddressPage.setPhone(phone);
        addAddressPage.submit(); //dodaje address
    }

    @Then("an address is created with {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void an_address_is_created_with(String alias, String firstname, String lastname, String company, String vatNumber, String address, String addressComplement, String city, String postcode, String phone) {
        String alertText = driver.findElement(By.id("notifications"))
                .findElement(By.className("alert-success")).getText();

        //Sprawdzenie czy wyświetla się wiadomość o prawidłowym dodaniu adresu
        assertTrue(alertText.contains("Address successfully added!"));
        String pageSource = driver.getPageSource();

        //Sprawdzenie czy strona ma stworzony adres z podanymi danymi z tabeli
        assertTrue(pageSource.contains(alias));
        assertTrue(pageSource.contains(firstname));
        assertTrue(pageSource.contains(lastname));
        assertTrue(pageSource.contains(company));
        assertTrue(pageSource.contains(vatNumber));
        assertTrue(pageSource.contains(address));
        assertTrue(pageSource.contains(addressComplement));
        assertTrue(pageSource.contains(city));
        assertTrue(pageSource.contains(postcode));
        assertTrue(pageSource.contains(phone));
    }

     // zamknięcie przeglądarki

    @Then("close browser")
    public void close_browser() {
        driver.quit();
    }
}
