Feature: Shop from new luma yoga collection without sign in

  Scenario: Add product to cart by clicking on the product name
    Given the user is on the "New Luma Yoga Collection" page
    When the user selects the desired product by its name
    And the user selects the "size", "colour"
    And the user adds the product to the cart
    Then the product should be added to the cart successfully


  Scenario: User should not be able to add a product to the cart without selecting size, colour, and quantity
    Given the user is on the "New Luma Yoga Collection" page
    When the user selects the desired product by its name
    And the user tries to add the product to the cart without selecting size, colour
    And the user tries to add the product to the cart without selecting size, colour
    Then the product should not be added to the cart
    And error message should be prompted
