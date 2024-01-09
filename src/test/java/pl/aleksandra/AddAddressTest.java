package pl.aleksandra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pl.aleksandra.project.AddAddressPage;
import pl.aleksandra.project.LoginPage;
import pl.aleksandra.project.MainPage;
import pl.aleksandra.project.MyAccountPage;

import java.time.Duration;

import static junit.framework.TestCase.assertTrue;

public class AddAddressTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void isPossibleToClickAddAddress() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.signInAs("gotzknpkqikttvzlym@cazlq.com", "8XgsB!QArxk!W7B");

        MyAccountPage myAccountPage = new MyAccountPage(this.driver);
        myAccountPage.goToAddressesPage().addAddress();

        assertTrue(this.driver.findElement(By.id("field-alias")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-firstname")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-lastname")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-company")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-vat_number")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-address1")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-address2")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-city")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-postcode")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-id_country")).isDisplayed());
        assertTrue(this.driver.findElement(By.id("field-phone")).isDisplayed());
    }

    @Test
    public void isPossibleToAddAddress() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.signInAs("gotzknpkqikttvzlym@cazlq.com", "8XgsB!QArxk!W7B");
        
        MyAccountPage myAccountPage = new MyAccountPage(this.driver);

        AddAddressPage addAddressPage = myAccountPage.goToAddressesPage().addAddress();
        addAddressPage.setAlias("test alias");
        addAddressPage.setFirstname("Alex");
        addAddressPage.setLastname("Nowak");
        addAddressPage.setCompany("A7IT");
        addAddressPage.setVatNumber("0000000000");
        addAddressPage.setAddress("Luzycka 89A");
        addAddressPage.setAddressComplement("Puszkina 9");
        addAddressPage.setCity("Gliwice");
        addAddressPage.setPostCode("44-100");
        addAddressPage.setPhone("500500500");
        addAddressPage.submit();


        assertTrue(driver.getPageSource().contains("test alias"));
        assertTrue(driver.getPageSource().contains("Alex"));
        assertTrue(driver.getPageSource().contains("Nowak"));
        assertTrue(driver.getPageSource().contains("A7IT"));
        assertTrue(driver.getPageSource().contains("0000000000"));
        assertTrue(driver.getPageSource().contains("Luzycka 89A"));
        assertTrue(driver.getPageSource().contains("Puszkina 9"));
        assertTrue(driver.getPageSource().contains("Gliwice"));
        assertTrue(driver.getPageSource().contains("44-100"));
        assertTrue(driver.getPageSource().contains("500500500"));
    }
}
