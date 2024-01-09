Feature: Order product

  Scenario Outline: product ordering
    Given an open browser on store page to order product
    When go to product view page and check the "<expectedDiscount>" discount <productId>
    Then add to card <quantity> sweaters of size "<size>" with "<expectedPrice>" price
    Then complete the order <addressId>
    Then take screenshot of order confirmation and close browser
    Examples:
      | expectedDiscount | quantity | expectedPrice | addressId | size | productId |
      | 20%              | 5        | 143.60        | 8394      | M    | 2         |
      | 20%              | 5        | 143.60        | 8394      | S    | 2         |
      | 20%              | 5        | 143.60        | 8394      | L    | 2         |
      | 20%              | 5        | 143.60        | 8394      | XL   | 2         |
      | 20%              | 10       | 287.20        | 8394      | XL   | 2         |
