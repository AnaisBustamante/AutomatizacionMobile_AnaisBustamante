package com.nttdata.stepsdefinitions;

import com.nttdata.steps.SauceLabsSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class SauceLabsCartStepsDef {

    @Steps
    SauceLabsSteps sauceLabsStepsSteps;

    private String productoSeleccionado;
    private int unidadesSeleccionadas;

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {
        sauceLabsStepsSteps.validateLogoDisplayed();
    }

    @And("valido que carguen correctamente los productos en la galería")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGalería() {
        sauceLabsStepsSteps.validateLoadProducts();
    }

    @When("agrego {int} unidades del producto {string}")
    public void agregoUnidadesUnidadesDelProductoProducto(int cantidad, String producto) {
        this.productoSeleccionado = producto;
        this.unidadesSeleccionadas = cantidad;
        sauceLabsStepsSteps.addProductToCart(cantidad, producto);
    }

    @Then("valido que el carrito de compras se actualice correctamente")
    public void validoQueElCarritoDeComprasSeActualiceCorrectamente() {
        sauceLabsStepsSteps.validarCarrito(productoSeleccionado, unidadesSeleccionadas);
    }
}

