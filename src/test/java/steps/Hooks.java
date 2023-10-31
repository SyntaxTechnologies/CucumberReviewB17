package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utlis.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void start(){
        openBrowserAndLaunchApplication();
    }
   @After
    public  void end(Scenario scenario){
   byte[] pic;
       if(scenario.isFailed()) {
           pic=takeScreenshot("failed/"+scenario.getName());
       } else {
           pic=takeScreenshot("passed/"+scenario.getName());
       }
       scenario.attach(pic,"image/png",scenario.getName());
       closeBrowser();
    }
}
