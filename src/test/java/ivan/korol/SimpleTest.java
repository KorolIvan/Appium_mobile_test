package ivan.korol;

import ivan.korol.pages.MainPage;
import ivan.korol.pages.SettingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {
    @Test
    public void confirmDefaultSettings() {
        SettingsPage settingsPage = new SettingsPage();
        MainPage mainPage = settingsPage.agreeWithDefaultSettings();
        Assert.assertNotNull(mainPage);
    }
}
