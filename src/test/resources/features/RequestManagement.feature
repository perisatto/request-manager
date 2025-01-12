Feature: Request Management

  Scenario: Create a new processing request
   Given request has the following attribuites
   	| documentNumber | name            | email                        |
   	| 35732996010    | Roberto Machado | roberto.machado@bestmail.com |
    When create a new request
    Then the request is successfully registered
    And should be showed