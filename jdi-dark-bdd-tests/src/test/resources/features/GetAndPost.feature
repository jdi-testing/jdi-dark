@smoke
Feature: GET and POST check

  Scenario Outline: Check response
    Given init service example
    When perform '<method>' request
    Then response status type is <responseStatus>
    And response body has values:
      | url          | <url>       |
      | headers.Host | httpbin.org |
    Examples:
      | method     | responseStatus | url                     |
      | getMethod  | OK             | http://httpbin.org/get  |
      | postMethod | OK             | http://httpbin.org/post |

  Scenario: Check service method
    Given init service example
    And 'getMethod' method is alive