@orangeHRM
Feature: Alchemy HRM.

  Scenario: Creating a job vacancy for DevOPS Engineer
    Given user login to the OrangeHRM Page
    When user Navigate to vacancies menu from Recruitment page
    Then user adds a Job Vacancy
    And user verifies the added vacancy and close browser

  Scenario: adding a candidate for recruitment
    Given user login to the OrangeHRM Page1
    When user Navigate to Recruitment page
    Then user adds a candidate
    And user verifies added candidate and close browser

  Scenario Outline: Add multiple employees
    Given user login to the OrangeHRM Page2
    When user navigates to PIM page
    Then user adds a employee with details "<FirstName>", "<LastName>"
    And user enters further details "<UserName>", "<Status>"
    Then user verifies the created employee "<FirstName>", "<LastName>" and close browser

    Examples: 
      | FirstName | LastName | Status  | UserName |
      | UserF01   | UserL01  | Enabled | User01   |
      | UserF02   | UserL02  | Enabled | User02   |

  Scenario Outline: Creating multiple vacancies
    Given user login to the OrangeHRM Page3
    When user Navigate to vacancies menu from Recruitment page2
    Then user adds a candidate with details "<NewVName>", "<NewHManager>", "<NewJTitle>"
    And user verifies added candidate with details "<NewVName>", "<NewHManager>", "<NewJTitle>" and close browser

    Examples: 
      | NewVName            | NewHManager          | NewJTitle                |
      | newJobVacancyName77 | FirstName1 LastName1 | Automation Test Engineer |
      | newJobVacancyName99 | FirstName1 LastName1 | Automation Test Engineer |
