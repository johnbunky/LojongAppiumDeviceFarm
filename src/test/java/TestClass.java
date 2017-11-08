import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException ;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;



public class TestClass {
    AppiumDriver driver;
    int tenSeconds = 10;
    int tenSecondsMl = 10000;

    @Before
    public void setUpAppium() throws MalformedURLException {
 /*       DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "testDevice");
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability("platformVersion", "5.0.2");

        File file = new File("/home/johnbunky/Downloads", "app-debug.apk");
        capabilities.setCapability("app", file.getAbsolutePath());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);*/
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";

        URL url = new URL(URL_STRING);

        //Use a empty DesiredCapabilities object
        DesiredCapabilities capabilities = new DesiredCapabilities();

        driver = new AndroidDriver<MobileElement>(url, capabilities);

        //Use a higher value if your mobile elements take time to show up
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testLojongAppium() throws InterruptedException {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String uniqueString =  dateFormat.format(date);

        clickElement(By.id("br.com.lojong:id/btSkip"));

        // Click on Create account button
        clickElement(By.xpath(".//*[@text='Criar Conta']"));

        // Type a different Name, Password and Email
        setText(By.id("br.com.lojong:id/etName"), "Name");
        setText(By.id("br.com.lojong:id/etPassword"), "Password");
        setText(By.id("br.com.lojong:id/etEmail"), uniqueString + "@gmail.com");

        // Click on Submit
        clickElement(By.xpath(".//*[@text='Entrar']"));

        // Click on Path button
        clickElement(By.xpath(".//*[@text='Caminho']"));

        // Click on 1
        clickElement(By.id("br.com.lojong:id/ivIconWay1"));

        // Click on Introduction
        clickElement(By.xpath(".//*[@text='Introdução']"));

        // After 10 seconds click on X (close)
        Thread.sleep(tenSecondsMl);
        clickElement(By.id("br.com.lojong:id/ivRightIcon"));

        // Click on Yes
        clickElement(By.id("android:id/button1"));

        // Click on Profile
        clickElement(By.id("br.com.lojong:id/llProfile"));

        // Click on Video
        clickElement(By.id("br.com.lojong:id/llVideos"));

        //  Click on Play
        clickElement(By.id("br.com.lojong:id/ivVideo"));
        Thread.sleep(tenSecondsMl);

        // Tap on Back button
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    }
     private void setText(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
        driver.hideKeyboard();
    }
    private void clickElement(By by){
        driver.findElement(by).click();
        driver.manage().timeouts().implicitlyWait(tenSeconds, TimeUnit.SECONDS);

    }
    public boolean takeScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }
}
