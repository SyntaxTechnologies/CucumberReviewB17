Feature: Login Feature

@fail
 Scenario Outline: user enters the incorrect credentials and verify the error message
#    Given user navigate to the Website
    When user enters the credentials "<username>" "<password>"
    And click on login button
    Then verify the error message is "<Message>"
    Examples:
      | username | password    | Message|
      |Admin     |Hum@nhrm     |   Invalid credentials    |
#      |Ad        |Hum@nhrm123  |    Invalid credentials      |
#      |      |          |    Username cannot be empty   |



