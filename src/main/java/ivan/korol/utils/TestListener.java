package ivan.korol.utils;

import ivan.korol.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.String.format;

public class TestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getRootLogger();
    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Test {} start...", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test {} passed", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test {} failed", result.getName());
    }

    private static void takeScreenshot() {
        File screenshot = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot, new File(format(".//target/screenshots/%s.png", LocalDate.now())));
        } catch (IOException e) {
            LOGGER.error("Screenshot has net be stored by reason: {}", e.getMessage());
        }
    }

}
