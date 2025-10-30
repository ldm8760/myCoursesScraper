package com.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// document.querySelector("body > div > div.d2l-page-main.d2l-max-width > div > div.d2l-htmlblock-wc.d2l_1_0_874 > d2l-html-block > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > a")

public class Login {
    public static void login() throws InterruptedException 
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://mycourses.rit.edu/d2l/login");
        Thread.sleep(1000);

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/d2l-html-block/div/div/table/tbody/tr[1]/td[1]/a"));
        loginButton.click();

        Thread.sleep(1000);

        // username and password phase
        WebElement username = driver.findElement(By.id("ritUsername"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys(System.getenv("RIT_USERNAME"));
        password.sendKeys(System.getenv("RIT_PASSWORD"));

        Thread.sleep(1000);

        // end of login
    }
}
