package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceLabsProductScreen extends PageObject {

    // Variables para los XPaths
    private static final String INCREASE_QUANTITY_BUTTON_XPATH = "//android.widget.ImageView[@content-desc='Increase item quantity']";
    private static final String ADD_TO_CART_BUTTON_XPATH = "//android.widget.Button[@content-desc='Tap to add product to cart']";
    private static final String PRODUCT_TITLE_XPATH = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/productTV']";

    @AndroidFindBy(xpath = INCREASE_QUANTITY_BUTTON_XPATH)
    private WebElement increaseQuantityButton;

    @AndroidFindBy(xpath = ADD_TO_CART_BUTTON_XPATH)
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = PRODUCT_TITLE_XPATH)
    private WebElement productTitle;

    public void addProductToCart(int unidades, String productoEsperado) {
        waitFor(ExpectedConditions.visibilityOf(productTitle));

        String productoActual = productTitle.getText();
        if (!productoActual.equals(productoEsperado)) {
            throw new IllegalStateException("El producto mostrado en pantalla no es el esperado. Esperado: " + productoEsperado + ", pero se encontró: " + productoActual);
        } else {
            System.out.println("El detalle del producto " + productoEsperado + " se está mostrando");
        }

        waitFor(ExpectedConditions.visibilityOf(increaseQuantityButton));

        for (int i = 1; i < unidades; i++) {
            increaseQuantityButton.click();
        }

        addToCartButton.click();
    }
}

