@smoke
Feature: Citibank search and validation in IB login portal

#Costco is not found under "Other" hnce swithed to "Bestbuy"
  Scenario: Validate "BestBuy.com - Credit Card" card is under Other section after Citibank search
    Given I navigate to "https://ndcdyn.interactivebrokers.com/sso/Login?RL=1&locale=en_US"
    And I log in with username "testah000" and password "tester12"
    And I click on 'Link Another Account'
    When I enter "Citibank" in the Search Institutions box
    Then I should see "BestBuy.com - Credit Card" listed under the Other section
    And I should not see "BestBuy.com - Credit Card" under the Best Match section
    When I click on the close button "X"
    Then the Search Institutions dialog should close
    
  Scenario Outline: Add an offline account of type <AccountType>
    Given I am on the Landing Page
  	Then I click on 'Link Another Account' from the dashboard
    Then I am on the Add External Account page
    When I click on the 'Offline Account' option
    And I click 'Continue' on the Add External Account modal
    Then I am on the Add Account page
    Then I should be able to add an offline account of type "<AccountType>"
    

    Examples:
      | AccountType   |
      | Brokerage     |
      | Credit Card   |
      | Real Estate   |
      | Other Asset   |
      | Savings       | 