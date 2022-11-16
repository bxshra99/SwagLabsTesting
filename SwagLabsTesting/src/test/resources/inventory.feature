Feature: As a user I would like to navigate the inventory page
  Background:
    Given I am on the inventory page


#  Scenario: Sorting products on "products' page
#    When I click on sorting field in upper right corner
#    And I click one of possible sorting options
#    Then Products on page should be sorted by name[A-Z]


  Scenario: Adding products to cart
    When I click Add to cart button
    Then Product should be added to the cart


  Scenario: Removing products from cart
    When I have a product added to cart
    And I click Remove button
    Then Remove button should change to Add to cart