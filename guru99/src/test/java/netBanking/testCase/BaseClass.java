package netBanking.testCase;

import netBanking.utitilies.ReadConfig;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BaseClass {

    // San@gmail.com
    // User ID : mngr559646
    // Password : qagubat

    ReadConfig readConfig = new ReadConfig();
    public String userId = readConfig.getUserName();
    public String passWord = readConfig.getPassword();
    public String url = readConfig.getApplicationUrl();
    public static WebDriver driver;

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional String br) {

        logger = Logger.getLogger("eBanking");

        PropertyConfigurator.configure("log4j.properties");

        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();
        } else if (br.equals("fireFox")) {
            driver = new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
        } else if (br.equals("ie")) {
            driver = new InternetExplorerDriver();
            System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
        }
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    public void captureScreenShot(WebDriver driver,String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png" );
        FileUtils.copyFile(source,target);
        System.out.println("Screenshot taken");
    }

    public boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e){
            return false;
        }
    }
}
