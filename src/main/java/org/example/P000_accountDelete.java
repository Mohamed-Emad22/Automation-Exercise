package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P000_accountDelete extends basePage{

    public P000_accountDelete(WebDriver driver) {
        super(driver);
    }
    private final By accountDelete =By.xpath("//a[@href=\"/delete_account\"]");
    private final By continueButton =By.xpath("//a[@data-qa=\"continue-button\"]");
    private final By accountDeleteText =By.xpath("//h2[@data-qa=\"account-deleted\"]/b");

    public void clickOnDeleteButton(){
        click(accountDelete);
    }

    public String accountDeleteVisible(){
        waitUntilElementIsVisible(accountDeleteText);
        return driver.findElement(accountDeleteText).getText();
    }

    public void clickOnContinueButton() {
        click(continueButton);
    }
}
