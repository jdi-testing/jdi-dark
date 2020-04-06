@smoke
Feature: Response status check

  Scenario: Server error status request
    Given init service example
    When perform 'status' request with named path parameters '503'
    Then response status code is 503
    And response status type is SERVER_ERROR
    And response body is empty