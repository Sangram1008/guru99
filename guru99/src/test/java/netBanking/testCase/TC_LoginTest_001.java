package netBanking.testCase;

import netBanking.pageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void logIn() throws IOException {

        driver.get(url);
        logger.info("URL is opened");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserName(userId);
        logger.info("UserId is entered");

        loginPage.setPassword(passWord);
        logger.info("Password is entered");

        loginPage.clickLoginButton();
        logger.info("Click on Login Button");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Guru99 Bank Manager HomePage";

        if (actualTitle.equals(expectedTitle)) {
            AssertJUnit.assertTrue("Titles Matched", true);
            logger.info("Titles Matched");
        } else {
            captureScreenShot(driver, "logIn");
            AssertJUnit.fail("Titles MisMatched - Expected: " + expectedTitle + ", Actual: " + actualTitle);
            logger.info("Titles MisMatched");
        }
    }
}
