@jobBoard
Feature: Testing Job Board Page

  Scenario: create a new user
    Given Open a browser
    Then Login to jobs page
    And Locate the left hand menu and Click on Menu Item
    And Click the Add New button
    Then Fill in the details and submit the form
    And Verify user was created and close browser

  Scenario: Searching for Jobs using xpath
    Given Open a alchemy browser
    Then Navigate to Job site
    And Find the Keywords search input field and change job
    Then Find the filter to show only full time jobs
    And Find the title of job and print and close browser

  Scenario: Posting a job using parameterization
    Given Open a browser1
    Then Navigate to post a job page
    And Enter "Cucumber/BDD Tester", "Hyderabad" and "IBM"
    And Click on submit
    Then navigate to Job Page
    And Confirm "Cucumber/BDD Tester" Lisiting as shown and close browser

  Scenario Outline: Using Examples table to post a job
    Given Open a browser2
    Then Navigate to post a job page2
    And Enter Example "<Email>", "<Job>", "<Location>" and "<Company>"
    And Click on submit2
    Then navigate to Job Page2
    And Confirm Lisiting and close browser

    Examples: 
      | Email          | Job           | Location  | Company |
      | Test1@IBM1.com | Selenium/BDD4 | Bangalore | IBM     |
