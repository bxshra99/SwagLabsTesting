Feature: As a user I want to checkout so that I can finish my shopping
  Background:
    Given I am on the Checkout page

  Scenario: User
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will be navigated to the second step of the checkout

  Scenario: User doesn't enter information in one of the fields
    When I enter "" as first name
    And I enter "Baker" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: First Name is required"

  Scenario: User doesn't enter last name
    When I enter "Amy" as first name
    And I enter "" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: Last Name is required"

  Scenario: User doesn't enter postal code
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: Postal Code is required"

  Scenario: User cancels checkout
    When I click cancel button
    Then I will go back to the cart

  Scenario: User opens menu
    When I click menu button
    Then I will see the menu opened

  Scenario: User finished checkout
    Given I finished the first step of the checkout
    When I click finish button
    Then I will the checkout complete page opened
    And I will see the header "THANK YOU FOR YOUR ORDER"

  Scenario: User returns to the Inventory page after finishing checkout
    Given I finished checkout
    When I press back home button
    Then I will see the Inventory page opened

