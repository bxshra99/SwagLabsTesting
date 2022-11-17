Feature: As a user I want to checkout so that I can finish my shopping

  Scenario Outline: User
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will be navigated to the second step of the checkout
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User doesn't enter information in one of the fields
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I enter "" as first name
    And I enter "Baker" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: First Name is required"
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User doesn't enter last name
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I enter "Amy" as first name
    And I enter "" as last name
    And I enter "EC2Y 5AS" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: Last Name is required"
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User doesn't enter postal code
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I enter "Amy" as first name
    And I enter "Baker" as last name
    And I enter "" as a postal code
    And I click continue button
    Then I will receive an error message that states "Error: Postal Code is required"
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User cancels checkout
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I click cancel button
    Then I will go back to the cart
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User opens menu
    Given I am on the Checkout page logged with "<username>" and "<password>"
    When I click menu button
    Then I will see the menu opened
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User finished checkout
    Given I am on the Checkout page logged with "<username>" and "<password>"
    Given I finished the first step of the checkout
    When I click finish button
    Then I will the checkout complete page opened
    And I will see the header "THANK YOU FOR YOUR ORDER"
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User returns to the Inventory page after finishing checkout
    Given I am on the Checkout page logged with "<username>" and "<password>"
    Given I finished checkout
    When I press back home button
    Then I will see the Inventory page opened
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |