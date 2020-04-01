package com.example.readinglist.controller;

import com.example.readinglist.ReadingListApplication;
import com.example.readinglist.model.Book;
import com.example.readinglist.properties.AmazonProperties;
import com.example.readinglist.repository.ReadingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReadingListController.class)
public class ReadingListControllerTest {

    @Autowired
    private WebApplicationContext webContext;
    @MockBean
    ReadingListRepository readingListRepository;
    @MockBean
    AmazonProperties amazonProperties;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void homePage() throws Exception {

        MockHttpServletRequestBuilder getRequest = get("/reading-list/kamal");

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", is(empty())));
    }

    @Test
    public void postBook() throws Exception {

        MockHttpServletRequestBuilder postRequest = post("/reading-list/kamal")
                                                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                        .param("reader", "kamal")
                                                        .param("title", "Book Title")
                                                        .param("author", "Book Author")
                                                        .param("isbn", "Book ISBN")
                                                        .param("description", "Book Description");
        mockMvc.perform(postRequest)
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/reading-list/kamal"));
    }

    @Test
    public void readersBookTest() throws Exception {

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("kamal");
        expectedBook.setTitle("Book Title");
        expectedBook.setAuthor("Book Author");
        expectedBook.setIsbn("Book ISBN");
        expectedBook.setDescription("Book Description");

        when(readingListRepository.findByReader(expectedBook.getReader())).thenReturn(Arrays.asList(expectedBook));

        MockHttpServletRequestBuilder getRequest = get("/reading-list/kamal");

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
    }
}
