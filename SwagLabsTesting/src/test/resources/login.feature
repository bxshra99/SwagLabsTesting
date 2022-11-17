Feature: As a user on the Swag Labs Page, I want to be able to login
  Background:
    Given I am on the login page

  Scenario Outline: registered user
    When I enter <username> into the username box
    And I enter <password> into the password
    And I click the login button
    Then I will be navigated to the inventory page
    Examples:
      | username | password |
      | "standard_user" | "secret_sauce" |
      | "problem_user"  | "secret_sauce" |

  Scenario: unregistered user
    When I enter "alena" into the username box
    And I enter "potato" into the password
    And I click the login button
    Then I will see the message "Epic sadface: Username and password do not match any user in this service"

  Scenario Outline: locked out user
    When I enter <username> into the username box
    And I enter <password> into the password
    And I click the login button
    Then I will see the message <message>
    Examples:
      | username          | password       | message                                               |
      | "locked_out_user" | "secret_sauce" | "Epic sadface: Sorry, this user has been locked out." |

  Scenario Outline: unregistered user with testClasses
    When I enter "<username>" into the username box
    And I enter "<password>" into the password
    And I click the login button
    Then I will see the message "Epic sadface: Username and password do not match any user in this service"
    Examples:
      | username | password |
      | cathy    | potato   |
      | alena    | cucumber |
      | rumana   | miah     |
      | A        |S         |

  Scenario: Valid login
    Given I am on the login page
    When I input the correct login information and press login
    Then I am taken to the inventory page

  Scenario: Wrong username, correct password
    Given I am on the login page
    When I input the wrong username and the right password, and press login
    Then I see a message telling me that my details do not match any username or password in the service.

  Scenario: Correct username, wrong password
    Given I am on the login page
    When I input the correct username and wrong password, and press login
    Then I see a message telling me that my details do not match any username or password in the service.

  Scenario: Username but no password
    Given I am on the login page
    When I input a username but no password and press login
    Then I see a message that tells me a password is required.

  Scenario: Password but no username
    Given I am on the login page
    When I input a password but no username and press login
    Then I see a message that tells me a username is required.