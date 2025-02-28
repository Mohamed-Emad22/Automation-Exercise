package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P001_RegisterUser extends basePage{
    public P001_RegisterUser(WebDriver driver) {
        super(driver);
    }
    private final By homePageSliderLocator = By.xpath("//section[@id=\"slider\"]");
    private final By signUpAndLoginBtnLocator = By.xpath("//a[@href=\"/login\"]");
    private final By newUserTextLocator = By.xpath("//div[@class=\"signup-form\"]/h2");
    private final By nameLocator = By.xpath("//input[@data-qa=\"signup-name\"]");
    private final By emailLocator = By.xpath("//input[@data-qa=\"signup-email\"]");
    private final By signUpBtnLocator = By.xpath("//button[@data-qa=\"signup-button\"]");
    private final By enterAccountInfoTextLocator = By.xpath("//h2[@class=\"title text-center\"]//b");
    private final By genderOptionLocator = By.xpath("//div[@class=\"radio-inline\"]");
    private final By passwordLocator =By.xpath("//input[@id=\"password\"]");
    private final By daySelectLocator = By.xpath("//select[@id=\"days\"]");
    private final By dayOptionsLocator = By.xpath("//select[@id=\"days\"]/option");
    private final By monthSelectLocator = By.xpath("//select[@id=\"months\"]");
    private final By monthOptionsLocator = By.xpath("//select[@id=\"months\"]/option");
    private final By yearSelectLocator = By.xpath("//select[@id=\"years\"]");
    private final By yearOptionsLocator = By.xpath("//select[@id=\"years\"]/option");
    private final By newsLetterLocator =By.xpath("//input[@id=\"newsletter\"]");
    private final By specialOffersLocator =By.xpath("//input[@id=\"optin\"]");
    private final By firstNameLocator =By.xpath("//input[@id=\"first_name\"]");
    private final By lastNameLocator =By.xpath("//input[@id=\"last_name\"]");
    private final By companyLocator =By.xpath("//input[@id=\"company\"]");
    private final By addressOneLocator =By.xpath("//input[@id=\"address1\"]");
    private final By addressTwoLocator =By.xpath("//input[@id=\"address2\"]");
    private final By countrySelectLocator = By.xpath("//select[@id=\"country\"]");
    private final By countryOptionsLocator = By.xpath("//select[@id=\"country\"]/option");
    private final By stateLocator =By.xpath("//input[@id=\"state\"]");
    private final By cityLocator =By.xpath("//input[@id=\"city\"]");
    private final By zipLocator =By.xpath("//input[@id=\"zipcode\"]");
    private final By mobileLocator =By.xpath("//input[@id=\"mobile_number\"]");
    private final By createAccountBtnLocator =By.xpath("//form[@action=\"/signup\"]/button[@class=\"btn btn-default\"]");
    private final By accountCreatedTextLocator = By.xpath("//h2[@class=\"title text-center\"]//b");
    private final By continueBtnLocator =By.xpath("//a[@class=\"btn btn-primary\"]");
    private final By loggedInAsTextLocator =By.xpath("//ul[@class=\"nav navbar-nav\"]//li[contains(., \"Logged in as\")]");

    public void goToSignUpPage(){
        click(signUpAndLoginBtnLocator);
    }
    public boolean homePageVisible() {
        waitUntilElementIsVisible(homePageSliderLocator);
        return driver.findElement(homePageSliderLocator).isDisplayed();
    }
    public String newUserSignUpVisible(){
        waitUntilAllElementIsVisible(newUserTextLocator);
        return driver.findElement(newUserTextLocator).getText();
    }
    public String enterAccountInfoVisible(){
        waitUntilElementIsVisible(enterAccountInfoTextLocator);
        return driver.findElement(enterAccountInfoTextLocator).getText();
    }
    public String accountCreatedVisible(){
        waitUntilAllElementIsVisible(accountCreatedTextLocator);
        return driver.findElement(accountCreatedTextLocator).getText();
    }
    public String loggedInAsVisible(){
        waitUntilElementIsVisible(loggedInAsTextLocator);
        return driver.findElement(loggedInAsTextLocator).getText();
    }
    public void firstPageSignUpValid(String name,String email){
        sendKeys(nameLocator,name);
        sendKeys(emailLocator,email);
        waitUntilAllElementIsVisible(nameLocator,emailLocator);
    }
    public void clickSignUpButton(){
        click(signUpBtnLocator);
    }

    public void chooseRandomGender(){
        int num = driver.findElements(genderOptionLocator).size();
        int random = fake.number().numberBetween(1,num+1);
        By gender = By.xpath("//*[@class=\"radio-inline\"]["+random+"]//input");
        click(gender);
        waitUntilElementIsVisible(gender);
    }
    public void chooseRandomDate(){
        waitUntilAllElementIsVisible(daySelectLocator,monthSelectLocator,yearSelectLocator);
        selectRandomOption(daySelectLocator,dayOptionsLocator,2);
        selectRandomOption(monthSelectLocator,monthOptionsLocator,2);
        selectRandomOption(yearSelectLocator,yearOptionsLocator,2);
    }
    public void clickCheckBoxes(){
        click(newsLetterLocator);
        click(specialOffersLocator);
    }
    public void secondPageSignUpValid(String password,String firstName,String lastName,String company,String address, String SecondAddress,String state,String city,String zipCode,String mobile){
        sendKeys(passwordLocator,password);
        sendKeys(firstNameLocator,firstName);
        sendKeys(lastNameLocator,lastName);
        sendKeys(companyLocator,company);
        sendKeys(addressOneLocator,address);
        sendKeys(addressTwoLocator,SecondAddress);
        sendKeys(stateLocator,state);
        sendKeys(cityLocator,city);
        sendKeys(zipLocator,zipCode);
        sendKeys(mobileLocator,mobile);
    }

    public void chooseRandomCountry(){
        selectRandomOption(countrySelectLocator,countryOptionsLocator,1);
    }
    public void createAccount(){
        click(createAccountBtnLocator);
    }
    public void continueAfterCreateAccount(){
        click(continueBtnLocator);
    }

}
