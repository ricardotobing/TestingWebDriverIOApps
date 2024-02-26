package test;

import config.AndroidConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), AndroidConfig.setupAndroidConnection());
        wait = new WebDriverWait(driver, 7);
    }

    @Test(priority = 0)
    public void EmptyEmailInputTest(){
        LoginPage.clickTabLogin(wait);
        LoginPage.setupPassword(wait,"Password123");
        LoginPage.clickLoginButton(wait);

        //validate empty email, when success login
        Assert.assertEquals(LoginPage.getInvalidEmailMessage(wait), "Please enter a valid email address");
    }

    @Test(priority = 1)
    public void EmptyPasswordInputTest(){
        LoginPage.clickTabLogin(wait);
        LoginPage.setupPassword(wait,"");
        LoginPage.setupEmail(wait, "coba@gmail.com");
        LoginPage.clickLoginButton(wait);

        //validate empty password, when success login
        Assert.assertEquals(LoginPage.getInvalidPasswordMessage(wait), "Please enter at least 8 characters");
    }

    @Test(priority = 2)
    public void InvalidEmailTest(){
        LoginPage.clickTabLogin(wait);
        LoginPage.cleanLoginInput(wait);
        LoginPage.setupEmail(wait,"testing");
        LoginPage.setupPassword(wait,"Password123");
        LoginPage.clickLoginButton(wait);

        //validate message invalid email, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidEmail)).getText(), "Please enter a valid email address");
    }

    @Test(priority = 3)
    public void InvalidPasswordTest(){
        LoginPage.clickTabLogin(wait);
        LoginPage.cleanLoginInput(wait);
        LoginPage.setupEmail(wait,"testing@gmail.com");
        LoginPage.setupPassword(wait,"test");
        LoginPage.clickLoginButton(wait);

        //validate message invalid password, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidPassword)).getText(), "Please enter at least 8 characters");
    }

    @Test(priority = 4)
    public void SuccessfullyLoginByFingerPrint() throws InterruptedException {
        LoginPage.clickTabLogin(wait);
        LoginPage.cleanLoginInput(wait);
        LoginPage.clickFingerPrint(wait);
        for (int i = 1; i <= 3; i++)
        {
            Thread.sleep(5000);
            executeProcess("adb -e emu finger touch 1");
        }

        //validate popup dialog, when success login
        Assert.assertEquals(LoginPage.getTitleLoginPopup(wait), "Success");
        Assert.assertEquals(LoginPage.getDescLoginPopup(wait), "You are logged in!");
        Assert.assertEquals(LoginPage.getOkButtonLoginPopup(wait), "OK");
    }

    private void executeProcess(String cmd){
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run a shell command
        processBuilder.command("bash", "-c", cmd);

        // Run a shell script
        //processBuilder.command("xyz.sh");

        // if running on windows

        // Run a command
        //processBuilder.command("cmd.exe", "/c", cmd);


        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + " ");
            }

            BufferedReader ereader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            String eline;
            while ((eline = ereader.readLine()) != null) {
                output.append(eline + " ");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Success");
                System.out.println(output);

            } else {
                System.out.println("Failure");
                System.out.println(output);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



@Test(priority = 5)
    public void SuccessfullyLoginTest(){
        LoginPage.clickTabLogin(wait);
        LoginPage.cleanLoginInput(wait);
        LoginPage.setupEmail(wait,"coba@gmail.com");
        LoginPage.setupPassword(wait,"Password123");
        LoginPage.clickLoginButton(wait);

        //validate popup dialog, when success login
        Assert.assertEquals(LoginPage.getTitleLoginPopup(wait), "Success");
        Assert.assertEquals(LoginPage.getDescLoginPopup(wait), "You are logged in!");
        Assert.assertEquals(LoginPage.getOkButtonLoginPopup(wait), "OK");
    }

    @AfterClass
    public void dispatchConfig(){
        if(driver!=null) {
            driver.quit();
        }
    }


}
