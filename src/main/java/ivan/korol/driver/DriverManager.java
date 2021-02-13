package ivan.korol.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ivan.korol.configurations.AddressConfiguration;
import ivan.korol.configurations.ConfigurationReader;
import ivan.korol.configurations.EnvironmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType
            .valueOf(ConfigurationReader
                    .getInstance()
                    .getEnvironment()
                    .toUpperCase());

    private static AppiumDriver<MobileElement> appiumDriver;
    private DriverManager(){}

    public static AppiumDriver<MobileElement> getAppiumDriver() {
        if(appiumDriver == null) {
            appiumDriver = creatDriver();
        }
        return appiumDriver;
    }

    private static AppiumDriver<MobileElement> creatDriver() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                appiumDriver = new AndroidDriver<>(null);
                break;
            default:
                throw new IllegalArgumentException(format("Unknown environment type: %s", ENVIRONMENT_TYPE));
        }
        LOGGER.info("Driver is created");
        LOGGER.info("Environment type {}", ENVIRONMENT_TYPE);
        return appiumDriver;
    }

    public static void closeAppiumDriver() {
        Optional.ofNullable(getAppiumDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            appiumDriver = null;
            LOGGER.info("Driver closed successful");
        });
    }

    public static void closeAppiumSession() {
        AddressConfiguration.stopService();
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill",
                    ConfigurationReader.getInstance().getDeviceUDID()));
            LOGGER.info("AVD is closed");
        } catch (IOException e) {
            LOGGER.error("AVD was not closed, message {}", e.getMessage());
        }
    }
}
