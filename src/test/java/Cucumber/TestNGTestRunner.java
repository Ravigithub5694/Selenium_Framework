package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="ravikumaracademy.stepDefinition"
,monochrome=true,plugin= {"html:target/Cucumberhtml"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
