package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features",
glue = {"stepDefinitions"}, 
tags = "@jobBoard",
monochrome = true, 
plugin = {"pretty", "junit:target/XML/jobreport.xml",
		            "html:target/HTML/jobreport.html",
					"json:target/JSON/jobreport.json"})
public class JobBoard {

}
