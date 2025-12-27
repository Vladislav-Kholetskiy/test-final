package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.time.Duration;

public class BaseMobileTest {

    protected AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.main.MainActivity")
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(300));

        options.setCapability("forceAppLaunch", true);
        options.setCapability("autoGrantPermissions", true);
        options.setCapability("disableWindowAnimation", true);
        options.setCapability("uiautomator2ServerInstallTimeout", 60000);
        options.setCapability("uiautomator2ServerLaunchTimeout", 60000);
        options.setCapability("adbExecTimeout", 60000);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
