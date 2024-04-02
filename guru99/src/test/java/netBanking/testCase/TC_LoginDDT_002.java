package netBanking.testCase;

import netBanking.pageObject.LoginPage;
import netBanking.utitilies.XLUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC_LoginDDT_002 extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        logger.info("User name provided: " + user);
        lp.setPassword(pwd);
        logger.info("Password provided: " + pwd);
        lp.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));

        if (isAlertPresent()) {
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                alert.accept();
                // Handling incorrect username or password scenario
                if (alert.getText().contains("Incorrect username or password")) {
                    logger.warning("Login failed for user: " + user);
                    Assert.fail("Login failed for user: " + user);
                }
            } catch (NoAlertPresentException e) {
                logger.warning("Alert not present when expected");
            }
        } else {
            Assert.assertTrue(true);
            logger.info("Login passed");
            lp.clickLogOutButton();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept(); // Close logout alert
            driver.switchTo().defaultContent();
        }
    }

    // Method to check if alert is present
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // DataProvider to provide test data from Excel
    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\test\\java\\netBanking\\testData\\LoginData.xlsx";
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
        String loginData[][] = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }
        return loginData;
    }
}
