package testsuite;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void UserShouldLoginSuccessfullyWithValidCredentials() {
        // Enter “tomsmith” username
        sendTexTooElement(By.name("username"), "tomsmith");

        //Enter “SuperSecretPassword!” password
        sendTexTooElement(By.name("password"), "SuperSecretPassword!");

        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//button[@class='radius']"));
        // Verify the text “Secure Area”
        String expectedMessageField = "Secure Area";
        String actualMessage = getTextFromElement(By.xpath("//h2[normalize-space()='Secure Area']"));
        Assert.assertEquals("no search message passed", expectedMessageField, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Enter “tomsmith1” username
        sendTexTooElement(By.name("username"), "tomsmith1");
        //Enter “SuperSecretPassword!” password
        sendTexTooElement(By.name("password"), "SuperSecretPassword!");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//button[@class='radius']"));
        //Verify the error message “Your username
        String expectedMessageField = "Your username is invalid!\n" +
                "×";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']"));
        Assert.assertEquals("Invalid username entered", expectedMessageField, actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter “tomsmith” username
        sendTexTooElement(By.name("username"), "tomsmith");
        //Enter “SuperSecretPassword” password
        sendTexTooElement(By.name("password"), "SuperSecretPassword");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//button[@class='radius']"));
        //Verify the error message “Your password is invalid!”
         String expectedMessageField = "Your password is invalid!\n" +
                 "×";
         String actualMessage = getTextFromElement(By.xpath("//div[@class = 'flash error']"));
         Assert.assertEquals("Invalid password entered",expectedMessageField,actualMessage);
    }

    @After
    public void testDown() {
         closeBrowser();

    }

}
