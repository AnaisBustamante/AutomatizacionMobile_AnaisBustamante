package com.nttdata.steps;

import com.nttdata.screens.SauceLabsCartScreen;
import com.nttdata.screens.SauceLabsHomeScreen;
import com.nttdata.screens.SauceLabsProductScreen;
import org.junit.Assert;

public class SauceLabsSteps {

    SauceLabsHomeScreen homeScreen;
    SauceLabsProductScreen productScreen;
    SauceLabsCartScreen cartScreen;

    public void validateLogoDisplayed() {
        Assert.assertTrue("El logo no está visible", homeScreen.logoVisible());
    }

    public void validateLoadProducts() {
        homeScreen.validateLoadProducts();
    }

    public void addProductToCart(int unidades, String product) {
        homeScreen.validateProduct(product);
        productScreen.addProductToCart(unidades,product);
    }

    public void validarCarrito(String producto, int unidades) {
        cartScreen.validarCarrito(producto, unidades);
    }
}

