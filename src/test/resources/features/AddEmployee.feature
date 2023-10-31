Feature:adding an Employee

  Background:
#    Given user navigate to the Website
    When user enters the credentials "Admin" "Hum@nhrm123"
    And click on login button
    When user clicks on PIM option
    And user clicks on add employee option

@datatable
Scenario: adding multiple employees from data table
  When user adds multiple employees from data table
  |firstName   | middleName | lastName |
  |donal12      |ms          |duck      |
  |tamoha12     |ms          |jimo      |
  |joe12        |ms          |trump     |


  @excel
  Scenario: Adding multiple employees from excel file
    When user adds multiple employees from excel using "Sheet1" and verify them
