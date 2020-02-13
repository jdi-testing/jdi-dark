@smoke
Feature: GET and POST check

  Scenario Outline: Check response
    Given I init service
    When I do <method> request
    Then Response status type is <responseStatus>
    And Response body has values
      | url          | <url>       |
      | headers.Host | httpbin.org |
    Examples:
      | method     | responseStatus | url                     |
      | getMethod  | OK             | http://httpbin.org/get  |
      | postMethod | OK             | http://httpbin.org/post |

  Scenario: Simple
    Given I init service
    When I verify that getMethod method is alive