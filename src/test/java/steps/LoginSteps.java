package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utlis.CommonMethods;

public class LoginSteps extends CommonMethods {

    @Given("user navigate to the Website")
    public void user_navigate_to_the_website() {
        openBrowserAndLaunchApplication();
    }

    @When("user enters the credentials {string} {string}")
    public void user_enters_the_credentials(String username, String password) {
       sendText(loginPage.usernameTextField,username);
       sendText(loginPage.passwordTextField,password);
    }
    @When("click on login button")
    public void click_on_login_button() {
    click(loginPage.loginBtn);
    }
    @Then("verify the error message is {string}")
    public void verify_the_error_message_is(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.actualErrorMsg.getText();
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);

    }


}
