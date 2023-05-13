
@tag
Feature: Validating place API
  

  @AddPlace
  Scenario Outline: AddPlace API successfully added new place
    Given AddPlace Payload is available with "<name>" "<language>" "<address>"
    When "AddPlaceAPI" api is called with "POST" http request
    Then Api call success with statuscode 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And "GetPlaceAPI" is called and check created place id maps to the "<name>"  
    
    Examples:
	  |name 	|language |address 		 |
    |AAHouse|French   |Eiffel Tower|  
#    |ACHouse|English  |WTO tower	 |			 

  @DeletePlace
  Scenario: Verify whether delete place functatonality is working
  	Given Delete Place API Payload is available 
    When "DeletePlaceAPI" api is called with "POST" http request
    Then Api call success with statuscode 200
    And "status" in response is "OK"