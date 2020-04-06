@skip
Feature: Performance after load check

  Scenario: Load service
    Given init service example
    When load service for 20 seconds with 'getMethod' request
    Then performance result doesn't have any fails
    And average response time is lesser than 2 seconds