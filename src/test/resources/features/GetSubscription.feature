Feature: GET subscriptions in the API

  Background:
    Given the endpoint subscriptions is available
    And A subscription is already saved

  Scenario Outline: GET all subscriptions from the API
    Given Call to "<url>"
    Then Get a response with "<statusCode>"
    Examples:
      | url           | statusCode |
      | /subscriptions | 200        |

  Scenario Outline: GET a subscription by Id from the API
    Given Call to the endpoint by Id
    Then Get a code response with "<statusCode>"
    And Response in the body with "<startDate>", "<finishDate>", "<planId>" exists
    Examples:
      | statusCode | startDate                     | finishDate                    | planId |
      | 200        | 2022-09-28T00:00:00.000+00:00 | 2022-10-28T00:00:00.000+00:00 | 1      |
