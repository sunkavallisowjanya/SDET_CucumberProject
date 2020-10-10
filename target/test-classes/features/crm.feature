@suiteCRM
Feature: Alchemy CRM.

  Scenario: Counting Dashlets
    Given user is on the Alchemy CRM website
    When user count the dashlets
    Then user prints all dashlet titles and close browser

  Scenario: Create leads using parameterization
    Given user is on the Alchemy CRM website1
    When user navigate to the lead
    Then user creates new lead with the following details "Mr.", "FirstName01", "LastName01", "0001"
    And user navigates to view leads and close browser

  Scenario: Schedule a meeting and invite members
    Given user is on the Alchemy CRM website2
    When user navigates to schedule meeting
    Then user enter details for meeting "Subject001", "10/10/2020", "11/10/2020", "Max", "allen", "abc"
    And user verifies the created meeting with "Subject001" and close browser

  Scenario Outline: Create a Product
    Given user is on the Alchemy CRM website3
    When user navigates to products
    Then user enter details for new product "<Name>" and "<Price>"
    And user verifies created record "<Name>" and close browser

    Examples: 
      | Name         | Price |
      | NewProduct01 |   100 |