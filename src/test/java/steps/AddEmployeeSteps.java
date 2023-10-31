package steps;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utlis.CommonMethods;
import utlis.Constants;
import utlis.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(dashboardPage.pimButton);
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
       click(dashboardPage.addEmployeeButton);
    }
    @When("user adds multiple employees from data table")
    public void user_adds_multiple_employees_from_data_table(io.cucumber.datatable.DataTable dataTable) {
//        convert the datatable as list of maps
        List<Map<String, String>> employeesNames = dataTable.asMaps();

//        iteration
        for(Map<String,String>map:employeesNames){
            String firstname = map.get("firstName");
            String middlename=map.get("middleName");
            String lastname=map.get("lastName");

//            send the names to the webElements
            sendText(addEmployeePage.firstNameLoc,firstname);
            sendText(addEmployeePage.middleNameLoc,middlename);
            sendText(addEmployeePage.lastNameLoc,lastname);

//            save the employee ID
            String empID = addEmployeePage.employeeID.getAttribute("value");
            click(addEmployeePage.saveBtn);
//            verify that the employee has actually been added
            click(dashboardPage.empListButton);

            sendText(employeeSearchPage.empSearchIdField,empID);
            click(employeeSearchPage.searchBtn);

            String addedEmpId = employeeSearchPage.addemployeeSearchResult.getText();

            Assert.assertEquals(empID,addedEmpId);

            click(dashboardPage.addEmployeeButton);
        }

    }
    @When("user adds multiple employees from excel using {string} and verify them")
    public void user_adds_multiple_employees_from_excel_using_and_verify_them
            (String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees =
                ExcelReader.read(sheetName, Constants.TESTDATA_FILEPATH);

        //from the list of maps, we need one map at one point of time
        // this iterator will give me one map to add one employee at a time
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        //it checks whether we have values in map or not
        while (itr.hasNext()){
            //it will return the keys and the values of the map which we store in this
            // variable
            Map<String, String> employeeMap = itr.next();
            sendText(addEmployeePage.firstNameLoc, employeeMap.get("firstName"));
            sendText(addEmployeePage.middleNameLoc, employeeMap.get("middleName"));
            sendText(addEmployeePage.lastNameLoc, employeeMap.get("lastName"));
            sendText(addEmployeePage.photograph, employeeMap.get("Photograph"));
            if(!addEmployeePage.checkBox.isSelected()){
                click(addEmployeePage.checkBox);
            }
            sendText(addEmployeePage.usernameEmp, employeeMap.get("Username"));
            sendText(addEmployeePage.passwordEmp, employeeMap.get("Password"));
            sendText(addEmployeePage.confirmPassword, employeeMap.get("confirmPassword"));
            //we are storing the emp id from the locator
            String empIdValue =
                    addEmployeePage.employeeIdLocator.getAttribute("value");
            click(addEmployeePage.saveBtn);
            Thread.sleep(2000);

            //verification of employee still pending
            click(dashboardPage.empListButton);
            //we need to search the employee by the stored employee id
            sendText(employeeSearchPage.empSearchIdField, empIdValue);
            click(employeeSearchPage.searchBtn);

            //after searching the employee, it returns the info in format
            //empid firstname middlename lastname this is the format
            List<WebElement> rowData =
                    driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

            for (int i=0; i<rowData.size(); i++){
                //it will give me the data from all the cell of the row
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                //it is we are getting from excel to compare with web table data
                String expectedDataFromExcel = empIdValue + " " + employeeMap.get("firstName")
                        + " " + employeeMap.get("middleName") + " "
                        + employeeMap.get("lastName");
                System.out.println(expectedDataFromExcel);
                Assert.assertEquals(expectedDataFromExcel, rowText);

            }
            //because we want to add many employees
            click(dashboardPage.addEmployeeButton);
            Thread.sleep(2000);

        }

    }

}
