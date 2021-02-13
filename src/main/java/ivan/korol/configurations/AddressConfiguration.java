package ivan.korol.configurations;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AddressConfiguration {

    private static final String ERROR_LOG_LEVEL = "error";
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";

    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfiguration(){}

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) {
            startService(port);
        }
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.getInstance().getAppiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, ERROR_LOG_LEVEL)
                .build();
        appiumDriverLocalService.start();
        LOGGER.info("Appium service started successful on port {}", port);
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            LOGGER.info("Appium service stopped successful");
        });
    }

    private static void makePortAvailableIfOccupied(int port) {
        if(!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(KILL_NODE_COMMAND);
            } catch (IOException e) {
                LOGGER.error("Could not execute runtime command, message: {}", e.getMessage());
            }
        }
    }

    private static boolean isPortFree(int port) {
        boolean isFree = true;
        try(ServerSocket ignored = new ServerSocket(port)) {
            LOGGER.info("Specified port - {} is free and ready to use", port);
        }catch (Exception e) {
            isFree = false;
            LOGGER.warn("Specified port - {} is occupied by some process, process will be terminated!", port);
        }
        return isFree;
    }
}
