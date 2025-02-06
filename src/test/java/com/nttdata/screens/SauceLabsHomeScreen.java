package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SauceLabsHomeScreen extends PageObject {

    // Variables para los XPaths
    private static final String LOGO_XPATH = "//android.widget.ImageView[@content-desc=\"App logo and name\"]";
    private static final String PRODUCTS_RECYCLER_VIEW_XPATH = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']";
    private static final String PRODUCT_TITLE_XPATH = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV']";
    private static final String PRODUCT_ITEM_XPATH = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']/android.view.ViewGroup";
    private static final String PRODUCT_TEXT_XPATH = ".//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV']";
    private static final String PRODUCT_IMAGE_XPATH = ".//android.widget.ImageView";

    @AndroidFindBy(xpath = LOGO_XPATH)
    private WebElement logo;

    @AndroidFindBy(xpath = PRODUCTS_RECYCLER_VIEW_XPATH)
    private WebElement productsRecyclerView;

    @AndroidFindBy(xpath = PRODUCT_TITLE_XPATH)
    private WebElement productTitle;


    public boolean logoVisible() {
        waitFor(ExpectedConditions.visibilityOf(logo));
        return logo.isDisplayed();
    }

    public void validateLoadProducts() {
        waitFor(ExpectedConditions.visibilityOf(productsRecyclerView));

        List<WebElement> productsFound = getDriver().findElements(By.xpath(PRODUCT_ITEM_XPATH));

        Assert.assertFalse("Los productos no se han cargado", productsFound.isEmpty());
    }

    public void validateProduct(String producto) {
        waitFor(ExpectedConditions.visibilityOf(productsRecyclerView));
        List<WebElement> productList = getDriver().findElements(By.xpath(PRODUCT_ITEM_XPATH));

        boolean productoEncontrado = false;

        for (WebElement product : productList) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_TITLE_XPATH)));

                WebElement productTextView = product.findElement(By.xpath(PRODUCT_TEXT_XPATH));
                WebElement productImageView = product.findElement(By.xpath(PRODUCT_IMAGE_XPATH));

                System.out.println("Producto encontrado en el screen: " + productTextView.getText()); // Debugging

                if (productTextView.getText().equals(producto) || productImageView.getAttribute("content-desc").equals(producto)) {
                    Assert.assertTrue("El producto " + producto + " si se encuentra", productTextView.isDisplayed());
                    System.out.println("Producto seleccionado: " + productTextView.getText());
                    productImageView.click();

                    productoEncontrado = true;
                    break; // Salimos del bucle ya que encontramos el producto
                }
            } catch (NoSuchElementException e) {
                System.out.println("Elemento no encontrado.");
            }
        }

        if (!productoEncontrado) {
            System.out.println("El producto " + producto + " no se encontró"); // Mensaje final
            Assert.fail("El producto " + producto + " no se encontró en la lista.");
        }
    }


}


