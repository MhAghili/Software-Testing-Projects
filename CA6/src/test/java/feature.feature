Feature: User Actions

  Scenario: User adds credit
    Given the user has a credit of 100
    When the user adds credit of 50
    Then the new credit should be 150

  Scenario: User removes item from buy list
    Given the user has a buy list with item ID "1" and quantity 2
    When the user removes 1 item with ID "1" from the buy list
    Then the new quantity of item ID "1" in the buy list should be 1

  Scenario: User withdraws credit
    Given the user has a credit of 100
    When the user withdraws credit of 30
    Then the new credit should be 70


