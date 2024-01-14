package model;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class UntitledTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "");
        driver =  new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.findElement(By.xpath("//div[@id='operations-authentication-controller-signup']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-authentication-controller-signup']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-authentication-controller-signup']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-authentication-controller-signup']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n    \"username\": \"mh\",\n    \"password\": \"1234\",\n    \"email\": \"mh@gmail.com\",\n    \"birthDate\": \"2010-01-01\",\n    \"address\": \"Tehran\"\n}");
        driver.findElement(By.xpath("//div[@id='operations-authentication-controller-signup']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-commodities-controller-getCommodities']/div/button")).click();
        driver.get("http://localhost:8080/swagger-ui/index.html#/commodities-controller/getCommodities");
        driver.findElement(By.xpath("//div[@id='operations-commodities-controller-getCommodities']/div[2]/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-commodities-controller-getCommodities']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div/button")).click();
        driver.get("http://localhost:8080/swagger-ui/index.html#/user-controller/addCredit");
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//input[@value='']")).click();
        driver.findElement(By.xpath("//input[@value='mh']")).clear();
        driver.findElement(By.xpath("//input[@value='mh']")).sendKeys("mh");
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n   \"credit\":\"200\"\n}");
        driver.findElement(By.xpath("//div[@id='operations-user-controller-addCredit']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div/button")).click();
        driver.get("http://localhost:8080/swagger-ui/index.html#/buy-list-controller/addToBuyList");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n  \"username\":\"mh\"\n\n}");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n  \"username\":\"mh\",\n  \"id\":\"1\"\n\n}");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-addToBuyList']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div/button/span[2]/a/span")).click();
        driver.get("http://localhost:8080/swagger-ui/index.html#/buy-list-controller/getPurchasedList");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n  \"username\": \"mh\"\n}");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div/button")).click();
        driver.get("http://localhost:8080/swagger-ui/index.html#/buy-list-controller/purchaseBuyList");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div[2]/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div[2]/div/div/div[3]/div[2]/div/div/div/textarea")).sendKeys("{\n  \"username\":\"mh\"\n}");
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-purchaseBuyList']/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//div[@id='operations-buy-list-controller-getPurchasedList']/div[2]/div/div[2]/button")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
