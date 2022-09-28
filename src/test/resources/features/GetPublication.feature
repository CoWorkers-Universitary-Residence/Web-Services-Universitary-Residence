Feature: Verify the GET method for publications in the API

  Background:
    Given the endpoint publications is available
    And A publication is already stored

  Scenario Outline: GET all publications from the API
    Given Get call to "<url>"
    Then Response is "<statusCode>"
    Examples:
      | url           | statusCode |
      | /publications | 200        |

  Scenario Outline: GET a publication by Id from the API
    Given Get call to endpoint by Id
    Then Status code response is "<statusCode>"
    And Response with "<width>", "<height>", "<rooms>", "<bathrooms>" exists
    Examples:
      | statusCode    | width | height | rooms | bathrooms    |
      | 200           | 15.0  | 24.0   | 3     | 3 bathrooms  |


