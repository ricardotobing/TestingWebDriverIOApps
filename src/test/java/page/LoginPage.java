package page;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/* this class contains login page tab and element of login page container */
public class LoginPage {
    public static By tabLogin = By.xpath("//android.view.View[@content-desc='Login']/android.widget.TextView");
    public static By emailInput = By.xpath("//android.widget.EditText[@content-desc='input-email']");
    public static By passwordInput = By.xpath("//android.widget.EditText[@content-desc='input-password']");
    public static By loginButton = By.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']");
    public static By loginByFingerPrint = By.xpath("//android.view.ViewGroup[@content-desc='button-biometric']");

    //pop up success
    public static By titleSuccessLogin = By.id("android:id/alertTitle");
    public static By descSuccessLogin = By.id("android:id/message");
    public static By okPopup = By.id("android:id/button1");

    //invalid message input
    public static By msgInvalidEmail = By.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[1]");
    public static By msgInvalidPassword = By.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[2]");

    public static void cleanLoginInput(WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("");
    }

    public static void clickTabLogin(WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
    }

    public static void setupEmail(WebDriverWait wait, String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys(email);
    }
    public static void setupPassword(WebDriverWait wait, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys(password);
    }

    public static void clickLoginButton(WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();
    }

    public static String getInvalidEmailMessage(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidEmail)).getText();
    }

    public static String getInvalidPasswordMessage(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidPassword)).getText();
    }

    public static String getTitleLoginPopup(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.titleSuccessLogin)).getText();
    }

    public static String getDescLoginPopup(WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.descSuccessLogin)).getText();
    }

    public static String getOkButtonLoginPopup(WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.okPopup)).getText();
    }

    public static void clickFingerPrint(WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginByFingerPrint)).click();
    }
}
