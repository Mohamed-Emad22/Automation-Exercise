package tests;

import org.example.P003_loginInCorrect;
import org.testng.annotations.Test;

public class T003_loginInCorrect extends testBase{
    protected P003_loginInCorrect loginInCorrect;
    @Test
    public void testCase3() {
        loginInCorrect = new P003_loginInCorrect(driver);
        test = reports.createTest("Verify that user can't login with incorrect login data");
        softAssert.assertTrue(loginInCorrect.homePageVisible());
        loginInCorrect.goToLoginPage();
        softAssert.assertEquals(loginInCorrect.loginToYourAccountVisible(),"Login to your account","Text Not Fount");
        loginInCorrect.enterInValidData();
        loginInCorrect.clickLogin();
        softAssert.assertEquals(loginInCorrect.errorMsgVisible(),"Your email or password is incorrect!","Text Not Fount");

        // Handel pass or fail messages
        softAssert.assertAll();
    }
}
