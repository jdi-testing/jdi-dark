@smoke
Feature: Json response check

  Scenario: Check json response
    Given init service example
    And set request content type to 'JSON'
    When perform 'getMethod' request
    Then response status type is OK
    And response body has values:
      | url          | http://httpbin.org/get |
      | headers.Host | httpbin.org            |
    And response header "Connection" is "keep-alive"