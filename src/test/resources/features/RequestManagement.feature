Feature: Request Management

  Scenario: Create a new processing request
   Given request has the following attribuites
   	| interval | videoFileName            |
   	| 10       | JohnCenaChairFight.mpeg  |
    When create a new request
    Then the request is successfully registered
    And should be showed    
    
	Scenario: Get request information
	 Given the request is already registered with the following attributes
   	| interval | videoFileName            |
   	| 10       | JohnCenaChairFight.mpeg  |		 
	 When ask for request information
	 Then the request is retrieved
	
	Scenario: Update request information
	 Given the request is already registered with the following attributes
   	| interval | videoFileName            |
   	| 10       | JohnCenaChairFight.mpeg  |	
	 When gives a new status
	 Then update the request status