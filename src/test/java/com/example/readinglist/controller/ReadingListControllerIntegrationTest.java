package com.example.readinglist.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
/**
 * Firstly we should up the application server (ReadingListApplication)..
 * Only after that we could run this integration test class..
  */
public class ReadingListControllerIntegrationTest {

    @Test
    public void pageNotFound() {

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://localhost:8080/reader", String.class);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
