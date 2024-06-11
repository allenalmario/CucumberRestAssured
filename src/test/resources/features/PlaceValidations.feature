Feature: Place API's

  @AddPlace
  Scenario Outline: Validate Add Place API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is successful with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
    | name    | language | address    |
    | ABHouse | English  | Lion St SW |
    | CDHouse | Spanish  | Cat Ave S  |

  @DeletePlace
  Scenario: Validate Delete Place API
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "POST" http request
    Then the API call is successful with status code 200
    And "status" in response is "OK"