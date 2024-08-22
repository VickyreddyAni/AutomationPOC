Feature: User Management API

  Scenario: POST and GET User
    Given I post a user with id "43433" and username "323243431qw"
    When I get the user with username "323243431qw"
    Then the response should contain user with id "43433"
    And the user should have the username "323243431qw"
    And the user should have the email "we@gmail.com"

  Scenario: POST with a new user
    Given I post a user with id "12345" and username "newuser"
    When I get the user with username "newuser"
    Then the response should contain user with id "12345"
    And the user should have the username "newuser"
    And the user should have the email "newuser@example.com"

  Scenario: POST and verify non-existent user
    Given I post a user with id "99999" and username "nonexistentuser"
    When I get the user with username "nonexistentuser"
    Then the response should contain user with id "99999"
    And the user should have the username "nonexistentuser"
    And the user should have the email "nonexistent@example.com"
