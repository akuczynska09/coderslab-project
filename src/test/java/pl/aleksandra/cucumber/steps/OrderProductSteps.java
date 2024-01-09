package pl.aleksandra.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.aleksandra.project.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class OrderProductSteps {

    private WebDriver driver;

    private MainPage mainPage;

    @Given("an open browser on store page to order product")
    public void an_open_browser_on_store_page_to_order_product() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl/");

        mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.signInAs("gotzknpkqikttvzlym@cazlq.com", "8XgsB!QArxk!W7B")
                .goToMainPage();
    }

    @When("go to product view page and check the {string} discount {int}")
    public void go_to_product_view_page_and_check_the_discount(String expectedDiscount, Integer productId) {
        //idziemy do strony produktu przez podany produktId
        ProductViewPage productViewPage = mainPage.viewProduct(productId);

        //sprawdzamy czy rabat wynosi expectedDiscount
        assertTrue(productViewPage.getProductDiscountText().contains(expectedDiscount));
    }

    @Then("add to card {int} sweaters of size {string} with {string} price")
    public void add_to_card_sweaters_of_size_with_price(Integer quantity,  String size, String expectedPrice) throws InterruptedException {
        ProductViewPage productViewPage = new ProductViewPage(driver);

        //wybieramy rozmiar
        productViewPage.selectSize(size);

        //wybieramy ilość
        productViewPage.selectQuantity(quantity);

        //dodajemy do koszyka i przechodzimy do strony koszyka
        ShoppingCartPage shoppingCartPage = productViewPage.addToCard(true);

        //sprawdzamy czy ilość produktów się zgadza
        TestCase.assertEquals(quantity + " items", shoppingCartPage.getCartItemsText()); // we are checking if on our cart we have selected quantity

        //sprawdza czy cena jest równa cenie oczekiwanej (expectedPrice)
        TestCase.assertEquals("€" + expectedPrice, shoppingCartPage.getCartPrice());
        //przedzie do opcji checkout
        shoppingCartPage.proceedToCheckout();
    }

    @Then("complete the order {int}")
    public void complete_the_order(Integer addressId) throws InterruptedException {
        CheckOutPage productViewPage = new CheckOutPage(driver);

        //wybór adresu dostawy
        productViewPage.selectAddress("id-address-delivery-address-" + addressId);

        TimeUnit.SECONDS.sleep(1); // należy poczekać aż elementy będą dostępne

        // wybór typu przesyłki
        productViewPage.shipmentType("//*[@id=\"js-delivery\"]/div/div[1]/div[1]");

        TimeUnit.SECONDS.sleep(1); // należy poczekać aż elementy będą dostępne

        //złożenie zamówienia z opcją płatności
        productViewPage.placeOrder("//*[@id=\"payment-option-2-container\"]/label");
    }

    @Then("take screenshot of order confirmation and close browser")
    public void take_screenshot_of_order_confirmation() throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        File screenshotFile = scrShot.getScreenshotAs(OutputType.FILE); // zrobienie  screenshota
        String filePath = System.getProperty("user.dir") + "/screenshots/order" + RandomString.make() + ".png"; //screenshot path
        File destination = new File(filePath);

        FileUtils.copyFile(screenshotFile, destination); //kopiowanie screenshota do żądanej lokalizacji

        driver.quit(); //zamknięcie przeglądarki
    }
}
