package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utlis.CommonMethods;


public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id="empsearch_id")
    public WebElement empSearchIdField;

    @FindBy(id="empsearch_employee_name_empName")
    public WebElement empSearchNameField;

    @FindBy(id="searchBtn")
    public WebElement searchBtn;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public WebElement addemployeeSearchResult;

    public EmployeeSearchPage(){
        PageFactory.initElements(driver, this);
    }
}
