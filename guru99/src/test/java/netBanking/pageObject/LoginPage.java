package netBanking.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver ldriver;

    public LoginPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(name = "uid")
    @CacheLookup
    WebElement userName;

    @FindBy(name = "password")
    @CacheLookup
    WebElement userPassword;

    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement clickLoginBtn;

    // @FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
    @FindBy(linkText = "Log out")
    @CacheLookup
    WebElement lnkLogout;

    public void setUserName(String userId) {
        userName.sendKeys(userId);
    }

    public void setPassword(String passWord) {
        userPassword.sendKeys(passWord);
    }

    public void clickLoginButton() {
        clickLoginBtn.click();
    }

    public void clickLogOutButton() {
        lnkLogout.click();
    }
}
