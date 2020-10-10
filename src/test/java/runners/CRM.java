package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features",
glue = {"stepDefinitions"}, 
tags = "@suiteCRM",
monochrome = true, 
plugin = {"pretty", "junit:target/XML/crmreport.xml",
		            "html:target/HTML/crmreport.html",
					"json:target/JSON/crmreport.json"})

public class CRM {

}