Feature: Amazon navigation
  Scenario: Display amazon
    When I navigate to 'amazon'
    Then I see the right title
    
  Scenario: Click on the first link in amazon
    When I navigate to 'amazon'
    And I click on the first link
    Then I see the right title