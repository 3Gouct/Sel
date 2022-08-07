package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll () {
    System.setProperty ("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }


    @Test
    void test () {
        driver.get ("http://localhost:9999");
        driver.findElement (By.cssSelector ("span[data-test-id='name'] input")).sendKeys ("Иванов Иван");
        driver.findElement (By.cssSelector ("span[data-test-id='phone'] input")).sendKeys ("+79999999999");
        driver.findElement (By.className ("checkbox__box")).click();
        driver.findElement (By.className ("button_view_extra")).click ();
        String text = driver.findElement (By.className ("paragraph")).getText ();
        assertEquals ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim ());
    }

    @Test
    void test1 () {
        driver.get ("http://localhost:9999");
        driver.findElement (By.cssSelector ("span[data-test-id='name'] input")).sendKeys ("Иванова-Петрова Наталья");
        driver.findElement (By.cssSelector ("span[data-test-id='phone'] input")).sendKeys ("+79999999999");
        driver.findElement (By.className ("checkbox__box")).click();
        driver.findElement (By.className ("button_view_extra")).click ();
        String text = driver.findElement (By.className ("paragraph")).getText ();
        assertEquals ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim ());
    }

    @BeforeEach
    void setUp () {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit ();
        driver=null;
    }

}