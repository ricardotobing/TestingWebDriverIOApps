package test;

import config.AndroidConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("Password123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();

        //validate empty email, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidEmail)).getText(), "Please enter a valid email address");
    }

    @Test(priority = 1)
    public void EmptyPasswordInputTest(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("coba@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();

        //validate empty password, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidPassword)).getText(), "Please enter at least 8 characters");
    }

    @Test(priority = 2)
    public void InvalidEmailTest(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("testing");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("Password123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();

        //validate message invalid email, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidEmail)).getText(), "Please enter a valid email address");
    }

    @Test(priority = 3)
    public void InvalidPasswordTest(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("testing@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();

        //validate message invalid password, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.msgInvalidPassword)).getText(), "Please enter at least 8 characters");
    }

    @Test(priority = 4)
    public void SuccessfullyLoginTest(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInput)).sendKeys("coba@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInput)).sendKeys("Password123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton)).click();

        //validate popup dialog, when success login
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.titleSuccessLogin)).getText(), "Success");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.descSuccessLogin)).getText(), "You are logged in!");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.okPopup)).getText(), "OK");
    }

    /*
    @Test(priority = 10)
    public void successfullyLoginWithFingerPrint() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginByFingerPrint)).click();
        for (int i = 1; i <= 3; i++)
        {
            Thread.sleep(5000);
            executeProcess("adb -e emu finger touch 1");
        }
    }

    private void executeProcess(String cmd){
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run a shell command
        //processBuilder.command(cmd);
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
                output.append(eline + "");
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
     */

    @AfterClass
    public void dispatchConfig(){
        if(driver!=null) {
            driver.quit();
        }
    }


}
