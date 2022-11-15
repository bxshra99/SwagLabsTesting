Feature: As a user I want to checkout so that I can finish my shopping
  Scenario: User
    Given I am on the Checkout page
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will be navigated to the second step of the checkout

  Scenario: User doesn't enter first name
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
    Given I am on the Checkout page
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: Postal Code is required"

