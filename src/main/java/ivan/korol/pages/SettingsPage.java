package ivan.korol.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BasePage {
    @AndroidFindBy(id = "com.chanel.weather.forecast.accu:id/tvDone")
    public MobileElement doneButton;

    public MainPage agreeWithDefaultSettings() {
        doneButton.click();
        return new MainPage();
    }
}
