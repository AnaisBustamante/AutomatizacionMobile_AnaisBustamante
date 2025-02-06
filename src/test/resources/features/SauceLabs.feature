@validate_cart
Feature: Validación de la funcionalidad del carrito de compras

  Como usuario de la aplicación SauceLabs
  Quiero poder agregar productos al carrito
  Para validar que la funcionalidad del carrito opera correctamente

  @addProductsTest
  Scenario Outline: Agregar productos al carrito y validar actualización
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galería
    When agrego <UNIDADES> unidades del producto "<PRODUCTO>"
    Then valido que el carrito de compras se actualice correctamente

    Examples:
      | PRODUCTO                        | UNIDADES |
      | Sauce Labs Backpack     | 1                   |
      | Sauce Labs Bolt - T-Shirt | 1                   |
      | Sauce Labs Bike Light     | 2                   |



