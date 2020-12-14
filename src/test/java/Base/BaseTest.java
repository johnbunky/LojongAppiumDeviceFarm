package Base;

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
    private AppiumDriver driver;

    @Before
    public void setUpAppium() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "testDevice");
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        File file = new File("/Users/you/Downloads", "org.wikipedia.apk");
        capabilities.setCapability("app", file.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
    }

    @After
    public void testDown() {
        driver.quit();
    }

    public void setText(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
        driver.hideKeyboard();
    }
    public void clickElement(By by){
        driver.findElement(by).click();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);

    }
}
