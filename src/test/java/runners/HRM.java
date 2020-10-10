package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features",
glue = {"stepDefinitions"}, 
tags = "@orangeHRM",
monochrome = true, 
plugin = {"pretty", "junit:target/XML/hrmreport.xml",
		            "html:target/HTML/hrmreport.html",
					"json:target/JSON/hrmreport.json"})

public class HRM {

}
