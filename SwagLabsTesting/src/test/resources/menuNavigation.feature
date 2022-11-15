Feature: As a user I want to be able to navigate on the website using the menu
  Background: Given that the menu is open


  Scenario: User attempting to navigate to the inventory page
    When I click on the inventory link
    Then I should be navigated to the inventory page


  Scenario: User attempting to navigate to the about page
    When I click on the about page link
    Then I should be navigate to the about page

  Scenario: User attempting to logout
    When I click on the logout link
    Then i should be navigated back to the login page

  Scenario: User attempting to reset the app
    When I click on the reset app link
    Then i should be navigated to the inventory page


  Scenario: User attempting to close the menu
    When i click on the close menu button
    Then the menu will be hidden from the webpage

