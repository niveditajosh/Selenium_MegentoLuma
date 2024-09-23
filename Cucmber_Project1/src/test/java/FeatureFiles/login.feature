Feature: User login

  Scenario: Login with empty username and password
    Given user is on the Luma webpage
    When user navigates to the signin page
    And logs in without entering the values
    Then login failed
    And username and password required error message pops up

Scenario: Login with invalid username and password
  Given user is on the Luma webpage
  When user navigates to the signin page
  And enters invalid username and password
  Then login failed
  And error message pops up

 Scenario: Login with valid username and password
   Given user is on the Luma webpage
   When user navigates to the signin page
   And enter valid login details
   Then user logged in successfully