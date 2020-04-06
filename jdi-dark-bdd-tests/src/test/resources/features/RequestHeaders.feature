Feature: Request headers check

  Scenario: Pass headers and check response
    Given init service example
    When set request headers:
      | Name | Katarina |
      | Id   | 1        |
    And perform 'get' request
    And print response
    Then response status type is OK
    And response header "headers.Name" is "Katarina"
    And response header "headers.Id" is "1"