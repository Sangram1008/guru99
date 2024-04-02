package netBanking.utitilies;

// Listener Class to Generate to Extend Reports

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ExtentSparkReporter sparkReporter;

    private String screenshotPath = System.getProperty("user.dir") + "\\Screensshots\\"; // Default path

    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
        String repName =  timeStamp + "extentReport.html";

        extentReports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "./test-output/" + repName);
        System.out.println("sparkReporter" + sparkReporter);

        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Host Name", "localhost");
        extentReports.setSystemInfo("Environement", "Qa");
        extentReports.setSystemInfo("User", "Sangram");

        sparkReporter.config().setDocumentTitle("Guru99 NetBanking");
        sparkReporter.config().setReportName("Functional Test Report");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        extentTest = extentReports.createTest(tr.getName());
        extentTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult tr) {
        extentTest = extentReports.createTest(tr.getName());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenshotFilename = tr.getName() + ".png";
        File screenshotFile = new File(screenshotPath + screenshotFilename);

        if (screenshotFile.exists()) {
            try {
                extentTest.fail("Screenshot is below:" + extentTest.addScreenCaptureFromPath(screenshotFile.getAbsolutePath()));
            } catch (Exception e) {
              System.out.println("Error");
            }
        } else {
            extentTest.info("Screenshot not found at: " + screenshotFile.getAbsolutePath());
        }
    }

    public void onTestSkip(ITestResult tr) {
        extentTest = extentReports.createTest((tr.getName()));
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    public void onTestFinish() {
        extentReports.flush();
    }
}
