Feature: Validating place API's 

@AddPlace_TC
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI 
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request 
	Then the API call got susscee with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name			|language 	|address				    |
	|Frontline house|French-IN	|29, side layout, cohen 09|
#	|BB house		|Spanish  	|Sea Cross Center			|


@DeletePlace_TC
Scenario: Verify id Delete Place functionality is working
	Given Delete place PayLoad
	When user calls "DeletePlaceAPI" with "Post" http request
	Then the API call got susscee with status code 200
	And "status" in response body is "OK"