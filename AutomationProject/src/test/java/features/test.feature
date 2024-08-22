Feature: Job Listing on LabCorp

  Scenario: Verify Job Listing and Apply Now functionality
    Given I open the LabCorp homepage
    When I navigate to the Careers section
    And I search for "QA Test Automation Developer"
    And I select a job listing
    Then I should see the job title as "QA Test Automation Developer"
    And I should see the job location
    And I should see the job ID
    And I should see the job description containing "Participate in the test automation technology development"
    And I should see a requirement "5+ years of experience in QA automation development"
    And I should see the automation tool "Selenium"
    When I click on Apply Now
    Then I should see the job title as "QA Test Automation Developer" on the application page
    And I should see the job location and job ID match the previous page
    When I return to the Job Search
