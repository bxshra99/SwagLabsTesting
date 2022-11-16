Feature: As a user I want to be able to navigate on the website using the menu

  Scenario Outline: User attempting to navigate to the inventory page
    Given I opened the menu logged in with "<username>" and "<password>"
    When I click on the inventory link
    Then I should be navigated to the inventory page
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User attempting to navigate to the about page
    Given I opened the menu logged in with "<username>" and "<password>"
    When I click on the about page link
    Then I should be navigated to the about page
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User attempting to logout
    Given I opened the menu logged in with "<username>" and "<password>"
    When I click on the logout link
    Then i should be navigated back to the login page
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User attempting to reset the app
    Given I opened the menu logged in with "<username>" and "<password>"
    When I click on the reset app link
    Then I should be navigated to the inventory page
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |

  Scenario Outline: User attempting to close the menu
    Given I opened the menu logged in with "<username>" and "<password>"
    When i click on the close menu button
    Then the menu will be hidden from the webpage
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |