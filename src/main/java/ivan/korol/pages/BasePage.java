package ivan.korol.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import ivan.korol.driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getAppiumDriver()), this);
    }
}
