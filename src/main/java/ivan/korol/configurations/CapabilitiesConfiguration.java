package ivan.korol.configurations;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class CapabilitiesConfiguration {

    private CapabilitiesConfiguration() {}

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(UDID, ConfigurationReader.getInstance().getDeviceUDID());
        desiredCapabilities.setCapability(AVD, ConfigurationReader.getInstance().getLocalDeviceName());
        desiredCapabilities.setCapability(APP_PACKAGE, ConfigurationReader.getInstance().getApplicationPackage());
        desiredCapabilities.setCapability(APP_ACTIVITY, ConfigurationReader.getInstance().getApplicationActivity());
        desiredCapabilities.setCapability(APP, new File(ConfigurationReader.getInstance().getApplicationPath()).getAbsolutePath());
        desiredCapabilities.setCapability(PLATFORM_NAME, ConfigurationReader.getInstance().getPlatformName());
        desiredCapabilities.setCapability(PLATFORM_VERSION, ConfigurationReader.getInstance().getPlatformVersion());
        return desiredCapabilities;
    }
}
