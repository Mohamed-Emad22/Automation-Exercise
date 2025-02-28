package org.example;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class basePage {
    public WebDriver driver;
    WebDriverWait wait;
    Faker fake = new Faker();
    public basePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void click(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
    protected void sendKeys(By by,String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    public void waitUntilElementIsClickable(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public void waitUntilElementIsVisible(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitUntilElementIsInvisible(By by){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public void waitUntilElementIsSelected(By by){
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }
    public void waitUntilAllElementIsVisible(By... locators) {
        for (By locator : locators) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }
    protected void selectRandomOption(By select, By option,int min) {
        int options = driver.findElements(option).size();
        int random = fake.number().numberBetween(min,options+1);
        Select selection = new Select(driver.findElement(select));
        selection.selectByIndex(random);
    }
}
