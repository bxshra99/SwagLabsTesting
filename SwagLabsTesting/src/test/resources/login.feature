Feature: As a user on the Swag Labs Page, I want to be able to login
  Scenario: unregistered user
    Given I am on the login page
    When I enter "alena" into the username box
    And I enter "potato" into the password
    And I click the login button
    Then I will see the message "Epic sadface: Username and password do not match any user in this service"

  Scenario Outline: locked out user
    Given I am on the login page
    When I enter <username> into the username box
    And I enter <password> into the password
    And I click the login button
    Then I will see the message <message>
    Examples:
      | username          | password       | message                                               |
      | "locked_out_user" | "secret_sauce" | "Epic sadface: Sorry, this user has been locked out." |

  Scenario Outline: unregistered user with testClasses
    Given I am on the login page
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


