package RunnerFiles;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\java\\Resource\\Wikipedia.feature",
		glue={"StepDefs"},
		tags= {"@WikipediaLinks"}
		)


public class WikipediaRunner{
}