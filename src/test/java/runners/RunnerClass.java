package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src//test//resources//features//Store.feature",
			glue= {"stepDefs"},
			monochrome=true,
			dryRun=false,
			plugin= {"pretty", 
					"html:target//Reports//HtmlReport.html"
			//"json:target//Reports//jsonReport.json",
			//"usage:target//Reports//UsageReport",
			//"rerun:target//failedScenarios.txt"
	}
	)

public class RunnerClass extends AbstractTestNGCucumberTests{

}
