package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class SauceLabsCartScreen extends PageObject {

    // Variables para los XPaths
    private static final String VIEW_CART_BUTTON_XPATH = "//android.widget.RelativeLayout[@content-desc='View cart']";
    private static final String CART_ITEMS_XPATH = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays list of selected products']/android.view.ViewGroup";
    private static final String PRODUCT_NAME_XPATH = ".//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV']";
    private static final String PRODUCT_QUANTITY_XPATH = ".//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/noTV']";

    @AndroidFindBy(xpath = VIEW_CART_BUTTON_XPATH)
    private WebElement viewCartButton;

    public void validarCarrito(String producto, int unidades) {
        viewCartButton.click();

        List<WebElement> itemsEnCarrito = getDriver().findElements(By.xpath(CART_ITEMS_XPATH));

        Assert.assertFalse("El carrito está vacío", itemsEnCarrito.isEmpty());

        boolean productoEncontrado = false;

        for (WebElement item : itemsEnCarrito) {
            try {
                WebElement nombreProducto = item.findElement(By.xpath(PRODUCT_NAME_XPATH));
                if (nombreProducto.getText().equals(producto)) {
                    productoEncontrado = true;

                    WebElement cantidadProducto = item.findElement(By.xpath(PRODUCT_QUANTITY_XPATH));

                    int cantidad = Integer.parseInt(cantidadProducto.getText());
                    Assert.assertEquals("La cantidad del producto no es la correcta en el carrito", unidades, cantidad);
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Elemento no encontrado.");
            }
        }

        Assert.assertTrue("El producto '" + producto + "' no se encontró en el carrito", productoEncontrado);
    }
}


