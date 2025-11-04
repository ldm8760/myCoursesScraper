package com.scraper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
    public static void Login(WebDriver driver) throws InterruptedException 
    {
        driver.get("https://mycourses.rit.edu/d2l/login");
        String firstLoginXpath = "/html/body/div/div[2]/div/div[1]/d2l-html-block/div/div/table/tbody/tr[1]/td[1]/a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstLoginXpath)));
        WebElement loginButton = driver.findElement(By.xpath(firstLoginXpath));
        loginButton.click();

        // username and password phase
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ritUsername")));
        WebElement username = driver.findElement(By.id("ritUsername"));
        username.clear();
        username.sendKeys(System.getenv("RIT_USERNAME"));

        WebElement password = driver.findElement(By.id("ritPassword"));
        password.clear();
        password.sendKeys(System.getenv("RIT_PASSWORD"));

        WebElement submitButton = driver.findElement(By.name("_eventId_proceed"));
        submitButton.click();

        // 2FA
        System.out.println("Please complete 2FA manually");

        WebDriverWait wait2fa = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Potential trust this device prompt
        wait2fa.until(ExpectedConditions.visibilityOfElementLocated(By.id("dont-trust-browser-button")));
        WebElement trustPrompt = driver.findElement(By.id("dont-trust-browser-button"));
        if (trustPrompt.isDisplayed()) 
        {
            trustPrompt.click();
        }
        System.out.println("2FA completed and redirected back. Continuing automation...");
    }

    public static void Scrape(WebDriver driver) 
    {
        driver.navigate().to("https://mycourses.rit.edu/d2l/le/worktodo/view");
        
    }

    public static void main( String[] args ) throws InterruptedException  
    {
        
        WebDriver driver = new ChromeDriver();
        Login(driver);
        // Login complete, now on home page

        Thread.sleep(10000);
        driver.quit();
    }
}
