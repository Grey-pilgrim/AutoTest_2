import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static ru.task.infrastructure.Core.DRIVER;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features")
public class RunnerTest {

    @BeforeClass
    public static void openMainPage() {
        DRIVER.manage().window().maximize();
    }

    @AfterClass
    public static void quit() {
        DRIVER.quit();
    }
}