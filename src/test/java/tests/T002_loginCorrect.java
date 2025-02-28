package tests;

import org.example.P000_accountDelete;
import org.example.P002_loginCorrect;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class T002_loginCorrect extends testBase{
    protected P000_accountDelete accountDelete;
    protected P002_loginCorrect loginCorrect;

    @Test(dependsOnMethods = "tests.T001_RegisterUser.testCase1")
    public void testCase2(ITestContext context) throws InterruptedException {
        test = reports.createTest("Verify that user can login successfully");
        accountDelete = new P000_accountDelete(driver);
        loginCorrect = new P002_loginCorrect(driver);

        // Retrieve email and password from ITestContext
        String email = (String) context.getAttribute("email");
        String password = (String) context.getAttribute("password");

        softAssert.assertTrue(loginCorrect.homePageVisible());
        loginCorrect.goToLoginPage();

        softAssert.assertEquals(loginCorrect.loginToYourAccountVisible(), "Login to your account", "Text Not Found");

        // Use email and password for login
        loginCorrect.enterValidData(
                email,
                password
        );
        System.out.println(email);
        System.out.println(password);
        loginCorrect.clickLogin();

        // Delete account
        accountDelete.clickOnDeleteButton();
        softAssert.assertEquals(accountDelete.accountDeleteVisible(), "ACCOUNT DELETED!", "Text Not Found");
        accountDelete.clickOnContinueButton();

        // Handel pass or fail messages
        softAssert.assertAll();
    }
}
