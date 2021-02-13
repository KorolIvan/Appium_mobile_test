package ivan.korol;

import ivan.korol.driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    @BeforeClass
    public void createSession() {
        DriverManager.getAppiumDriver();
    }

    @AfterMethod
    public void resetApp() {
        DriverManager.getAppiumDriver().resetApp();
    }

    @AfterClass
    public void tearDownSession() {
        DriverManager.closeAppiumDriver();
        DriverManager.closeEmulator();
    }
}
