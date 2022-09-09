import io.appium.java_client.MobileBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Work {

    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private AppiumDriver driver;
    private static final String ID = "boriszimmerman6@gmail.com";
    private static final String PASSWORD = "12a34b56";
    private static final String TO = "polvit@meta.ua";
    private static final String SUBJECT = "Hello everybody.";
    private static final String BODY = "Have a nice day.";

    private void setUpAndroid() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.google.android.gm");
        caps.setCapability("appActivity", ".ConversationListActivityGmail");
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @Before
    public void setUp() throws Exception {
        setUpAndroid();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGmail() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebDriverWait wait_more = new WebDriverWait(driver, 180, 1000);

        WebElement got_it = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/welcome_tour_got_it")));
        got_it.click();
        WebElement set_address = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/setup_addresses_add_another")));
        set_address.click();
        WebElement account_setup = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/account_setup_label")));
        account_setup.click();

        WebElement identifier = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='identifierId']")));
        identifier.sendKeys(ID);
        Thread.sleep(3000);
        WebElement next_id = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.view.View[@resource-id='identifierNext']/android.widget.Button")));
        next_id.click();
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.View[@resource-id='password']//android.widget.EditText")));
        password.sendKeys(PASSWORD);
        Thread.sleep(1000);
        WebElement next_password = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.view.View[@resource-id='passwordNext']/android.widget.Button")));
        next_password.click();

        WebElement signinconsent = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.view.View[@resource-id='signinconsentNext']/android.widget.Button")));
        signinconsent.click();
        Thread.sleep(1000);
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='ACCEPT']")));
        accept.click();
        Thread.sleep(1000);
        WebElement account_address = wait_more.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/account_address")));
        account_address.click();

        WebElement take_mi_to = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/action_done")));
        take_mi_to.click();
        Thread.sleep(3000);

        WebElement compose = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Compose")));
        compose.click();
        Thread.sleep(1000);
        WebElement to = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/to")));
        to.sendKeys(TO);
        Thread.sleep(1000);
        WebElement subject = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/subject")));
        subject.sendKeys(SUBJECT);
        Thread.sleep(1000);
        WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.LinearLayout[@resource-id='com.google.android.gm:id/wc_body_layout']//android.widget.EditText")));
        body.sendKeys(BODY);
        Thread.sleep(1000);
        WebElement send = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Send")));
        send.click();
        Thread.sleep(1000);

        WebElement navigation_drawer = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Open navigation drawer")));
        navigation_drawer.click();
        Thread.sleep(1000);
        WebElement sent = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.TextView[@text='Sent']")));
        sent.click();
        Thread.sleep(1000);
        WebElement sender = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.gm:id/senders")));

        assert(sender.getText().contains(TO.substring(0, 6)));

    }
}