package page;


import org.openqa.selenium.By;

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
}
