package pl.aleksandra.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber/features/order-product.feature", plugin = {"pretty","html:out/add-address-raport.html"})
public class OrderProductTest {
}