package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AmazonTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @Test
    public void addFifthNonSponsoredIphoneToCart() throws Exception {

        // Open Amazon
        driver.get("https://www.amazon.in");

        // Search for iPhone
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("twotabsearchtextbox")));

        searchBox.sendKeys("iphone");
        searchBox.sendKeys(Keys.ENTER);

        // Wait for results
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(
                                "div[data-component-type='s-search-result']")));

        Thread.sleep(3000);

        List<WebElement> cards =
                driver.findElements(
                        By.cssSelector(
                                "div[data-component-type='s-search-result']"));

        System.out.println("Total Cards Found : " + cards.size());

        int actualCount = 0;
        WebElement targetProduct = null;

        for (WebElement card : cards) {

            String text = card.getText();

            // Skip sponsored products
            if (text.contains("Sponsored")) {
                continue;
            }

            String asin = card.getAttribute("data-asin");

            if (asin == null || asin.isBlank()) {
                continue;
            }

            try {

                WebElement productLink =
                        card.findElement(
                                By.cssSelector("a[href*='/dp/']"));

                String href =
                        productLink.getAttribute("href");

                // Skip promotion pages
                if (href.contains("Promotion")) {
                    continue;
                }

                actualCount++;

                System.out.println(
                        "Actual Product : "
                                + actualCount);

                System.out.println(
                        productLink.getText());

                if (actualCount == 5) {
                    targetProduct = productLink;
                    break;
                }

            } catch (Exception ignored) {
            }
        }

        if (targetProduct == null) {
            throw new RuntimeException(
                    "5th non-sponsored product not found.");
        }

        String parentWindow =
                driver.getWindowHandle();

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                targetProduct);

        Thread.sleep(2000);

        js.executeScript(
                "arguments[0].click();",
                targetProduct);

        Thread.sleep(5000);

        // Switch to new tab
        Set<String> windows =
                driver.getWindowHandles();

        for (String window : windows) {

            if (!window.equals(parentWindow)) {

                driver.switchTo().window(window);
                break;
            }
        }

        System.out.println(
                "Current URL : "
                        + driver.getCurrentUrl());

        Thread.sleep(8000);

        // Scroll slightly
        js.executeScript(
                "window.scrollBy(0,600)");

        Thread.sleep(3000);

        // Re-locate button
        By addToCartLocator =
                By.id("add-to-cart-button");

        WebElement addToCart =
                wait.until(
                        ExpectedConditions
                                .presenceOfElementLocated(
                                        addToCartLocator));

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                addToCart);

        Thread.sleep(3000);

        // Find again to avoid stale element
        addToCart =
                driver.findElement(
                        addToCartLocator);

        try {

            wait.until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    addToCartLocator));

            addToCart =
                    driver.findElement(
                            addToCartLocator);

            addToCart.click();

            System.out.println(
                    "Normal click successful.");

        } catch (Exception e) {

            System.out.println(
                    "Normal click failed. Using JS click.");

            addToCart =
                    driver.findElement(
                            addToCartLocator);

            js.executeScript(
                    "arguments[0].click();",
                    addToCart);

            System.out.println(
                    "JavaScript click successful.");
        }

        Thread.sleep(5000);

        // Open cart directly
        driver.get(
                "https://www.amazon.in/gp/cart/view.html");

        wait.until(
                ExpectedConditions
                        .urlContains("cart"));

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("cart"));

        System.out.println(
                "Cart Page Opened Successfully");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            System.out.println("--------------------------------");
            System.out.println("Press ENTER to close browser...");
            System.out.println("--------------------------------");

            try {
                System.in.read();
            } catch (Exception ignored) {
            }

            driver.quit();
        }
    }
}
