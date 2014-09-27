Feature: Ebay navigation
  Scenario: Display ebay
    When I navigate to 'ebay'
    Then I see the right title
    
  Scenario: Click on the first link in ebay
    When I navigate to 'ebay'
    And I click on the first link
    Then I see the right title