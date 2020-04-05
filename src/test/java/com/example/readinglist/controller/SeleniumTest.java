package com.example.readinglist.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

    private ChromeDriver browser;

//    @Value("${local.server.port}")
    private static int port;

    @BeforeEach
    public void openBrowser() {
        port = 8080;
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeBrowser() {
        browser.quit();
    }

    @Test
    public void addBookToEmptyList() {

        String baseURL = "http//localhost:" + port;
        browser.get(baseURL);

        assertEquals("You have no books in your book list", browser.findElementByTagName("div").getText());
    }
}
