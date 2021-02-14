package ivan.korol.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final Properties properties = new Properties();
    private static ConfigurationReader instance;

    private ConfigurationReader(){}

    public static ConfigurationReader getInstance() {
        if(instance == null) {
            instance = new ConfigurationReader();
            try {
                properties.load(new FileInputStream("src/main/resources/app.properties"));
            } catch (IOException e) {
                LOGGER.error("Properties were not loaded by reason {}", e.getMessage());
            }
        }
        return instance;
    }

    public String getEnvironment() {
        return properties.getProperty("env.type");
    }
    public String getApplicationPath() {
        return properties.getProperty("app.path");
    }
    public String getApplicationPackage() {
        return properties.getProperty("app.package");
    }
    public String getApplicationActivity() {
        return properties.getProperty("app.activity");
    }
    public String getPlatformName() {
        return properties.getProperty("platform.name");
    }
    public String getPlatformVersion() {
        return properties.getProperty("platform.version");
    }
    public String getLocalDeviceName() {
        return properties.getProperty("local.device.name");
    }
    public String getDeviceUDID() {
        return properties.getProperty("udid");
    }
    public String getAppiumAddress() {
        return properties.getProperty("appium.address");
    }
    public int getAppiumPort() {
        return Integer.parseInt(properties.getProperty("appium.port"));
    }

}
