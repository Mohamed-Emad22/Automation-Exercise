package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P002_loginCorrect extends basePage{
    public P002_loginCorrect(WebDriver driver) {
        super(driver);
    }
    private final By homePageSliderLocator = By.xpath("//section[@id=\"slider\"]");
    private final By signUpAndLoginBtnLocator = By.xpath("//a[@href=\"/login\"]");
    private final By loginToYourAccountTextLocator = By.xpath("//div[@class=\"login-form\"]/h2");
    private final By emailLocator = By.xpath("//form[@action=\"/login\"]//input[@name=\"email\"]");
    private final By passwordLocator = By.xpath("//form[@action=\"/login\"]//input[@name=\"password\"]");
    private final By clickLoginBtnLocator = By.xpath("//form[@action=\"/login\"]//button[@class=\"btn btn-default\"]");

    public boolean homePageVisible() {
        waitUntilElementIsVisible(homePageSliderLocator);
        return driver.findElement(homePageSliderLocator).isDisplayed();
    }
    public void goToLoginPage(){
        click(signUpAndLoginBtnLocator);
    }
    public String loginToYourAccountVisible(){
        waitUntilElementIsVisible(loginToYourAccountTextLocator);
        return driver.findElement(loginToYourAccountTextLocator).getText();
    }
    public void enterValidData(String email,String password){
        waitUntilAllElementIsVisible(emailLocator,passwordLocator);
        sendKeys(emailLocator,email);
        sendKeys(passwordLocator,password);
    }
    public void clickLogin(){
        click(clickLoginBtnLocator);
    }

}
