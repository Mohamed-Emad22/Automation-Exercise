package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P003_loginInCorrect extends basePage{
    public P003_loginInCorrect(WebDriver driver) {
        super(driver);
    }
    private final By homePageSliderLocator = By.xpath("//section[@id=\"slider\"]");
    private final By signUpAndLoginButton = By.xpath("//a[@href=\"/login\"]");
    private final By loginToYourAccountTextLocator = By.xpath("//div[@class=\"login-form\"]/h2");
    private final By emailLocator = By.xpath("//*[@data-qa=\"login-email\"]");
    private final By passwordLocator = By.xpath("//*[@data-qa=\"login-password\"]");
    private final By clickLoginButton = By.xpath("//*[@data-qa=\"login-button\"]");
    private final By errorMsgText = By.xpath("//*[@action=\"/login\"]//p");

    public boolean homePageVisible() {
        waitUntilElementIsVisible(homePageSliderLocator);
        return driver.findElement(homePageSliderLocator).isDisplayed();
    }
    public void goToLoginPage(){
        click(signUpAndLoginButton);
    }
    public String loginToYourAccountVisible(){
        waitUntilElementIsVisible(loginToYourAccountTextLocator);
        return driver.findElement(loginToYourAccountTextLocator).getText();
    }
    public void enterInValidData(){
        waitUntilAllElementIsVisible(emailLocator,passwordLocator);
        sendKeys(emailLocator,"mohamed@gmail.com");
        sendKeys(passwordLocator,"password");
    }
    public void clickLogin(){
        click(clickLoginButton);
    }
    public String errorMsgVisible(){
        waitUntilElementIsVisible(errorMsgText);
        return driver.findElement(errorMsgText).getText();
    }
}
