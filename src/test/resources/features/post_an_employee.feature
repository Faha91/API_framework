
  Feature: Employee REST Api requests

    @ApiPost
    Scenario: Post an Employee method test
      Given Content type and Accept type is Json
      When I post a new Employee with "random" id
      Then Status code is 201
      When I send a GET request with "random" id
      Then Status code is 200
      And Employee JSON Response Data should match the posted JSON data


      Scenario: Post an Employee and Verify in UI
        Given Content type and Accept type is Json
        When I post a new Employee with "random" id
        Then Status code is 201
