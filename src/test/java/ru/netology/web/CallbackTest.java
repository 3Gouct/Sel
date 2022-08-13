package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver ().setup ();
    }


    @Test
    public void russianLettersAndASpace() {
        driver.get ("http://localhost:9999");
        driver.findElement (By.cssSelector ("[data-test-id='name'] input")).sendKeys ("Иванов Иван");
        driver.findElement (By.cssSelector ("[data-test-id='phone'] input")).sendKeys ("+79999999999");
        driver.findElement (By.cssSelector ("[data-test-id='agreement']")).click ();
        driver.findElement (By.cssSelector ("[type=button]")).click ();
        String text = driver.findElement (By.cssSelector ("[data-test-id=order-success]")).getText ();
        assertEquals ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim ());
    }

    @Test
    public void russianLettersHyphenAndSpace() {
        driver.get ("http://localhost:9999");
        driver.findElement (By.cssSelector ("[data-test-id='name'] input")).sendKeys ("Иванова-Петрова Наталья");
        driver.findElement (By.cssSelector ("[data-test-id='phone'] input")).sendKeys ("+79999999999");
        driver.findElement (By.cssSelector ("[data-test-id='agreement']")).click ();
        driver.findElement (By.cssSelector ("[type=button]")).click ();
        String text = driver.findElement (By.cssSelector ("[data-test-id=order-success]")).getText ();
        assertEquals ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim ());
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--disable-dev-shm-usage");
        options.addArguments ("--no-sandbox");
        options.addArguments ("--headless");
        driver = new ChromeDriver (options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit ();
        driver = null;
    }

}