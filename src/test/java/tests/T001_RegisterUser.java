package tests;

import org.example.P000_accountDelete;
import org.example.P001_RegisterUser;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilites.dataGenerator;

public class T001_RegisterUser extends testBase{
    protected P001_RegisterUser registerUser;
    protected P000_accountDelete accountDelete;

    @Test
    public void testCase1(ITestContext context) throws InterruptedException {

        // Save email and password in stings
        String email = dataGenerator.getEmail();
        String password = dataGenerator.getPassword();

        // Store email and password in ITestContext
        context.setAttribute("email", email);
        context.setAttribute("password", password);

        test = reports.createTest("Verify that user can register successfully");
        registerUser = new P001_RegisterUser(driver);
        accountDelete=new P000_accountDelete(driver);
        softAssert.assertTrue(registerUser.homePageVisible());
        registerUser.goToSignUpPage();
        softAssert.assertEquals(registerUser.newUserSignUpVisible(),"New User Signup!","Text Not Found");

        // Use email and password for registration
        registerUser.firstPageSignUpValid(
                password,
                email
        );
        System.out.println(email);

        // Filling the registration form
        registerUser.clickSignUpButton();
        softAssert.assertEquals(registerUser.enterAccountInfoVisible(),"ENTER ACCOUNT INFORMATION","Text Not Found");

        registerUser.chooseRandomGender();
        registerUser.chooseRandomDate();
        registerUser.clickCheckBoxes();

        // Using the fake data from dataGenerator
        registerUser.secondPageSignUpValid(
                password,
                dataGenerator.getFirstName(),
                dataGenerator.getLastName(),
                dataGenerator.getCompany(),
                dataGenerator.getFirstAddress(),
                dataGenerator.getSecondAddress(),
                dataGenerator.getState(),
                dataGenerator.getCountry(),
                dataGenerator.getZip(),
                dataGenerator.getPhoneNum()
        );
        System.out.println(password);

        registerUser.chooseRandomCountry();
        registerUser.createAccount();

        softAssert.assertEquals(registerUser.accountCreatedVisible(),"ACCOUN CREATED!","Text Not Found");

        registerUser.continueAfterCreateAccount();
        softAssert.assertEquals(registerUser.loggedInAsVisible().contains("Logged in as"), true,"User Not Logged In");
        System.out.println(registerUser.loggedInAsVisible());

        //This part is commented in order to be able to log in with same email and password in Test Case 2
        /*accountDelete.clickOnDeleteButton();
        softAssert.assertEquals(accountDelete.accountDeleteVisible(),"ACCOUNT DELETED!","Text Not Fount");
        accountDelete.clickOnContinueButton();*/

        // Handel pass or fail messages
        softAssert.assertAll();
    }
}
