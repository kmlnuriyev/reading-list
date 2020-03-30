package com.example.readinglist.controller;

import com.example.readinglist.model.Book;
import com.example.readinglist.properties.AmazonProperties;
import com.example.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReadingListController {

    private AmazonProperties amazonProperties;
    private ReadingListRepository repository;

    @Autowired
    public ReadingListController(ReadingListRepository repository, AmazonProperties amazonProperties) {
        this.repository = repository;
        this.amazonProperties = amazonProperties;
    }

    @GetMapping("/reading-list/{reader}")
    public String readersBooks(@PathVariable String reader, Model model) {

        List<Book> readingList = repository.findByReader(reader);

        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("associateID", amazonProperties.getAssociateId());
        }

        // logical view name - "readingList"
        return "readingList";
    }

    @PostMapping("/reading-list/{reader}")
    public String addToReadingList(@PathVariable String reader, Book book) {

        book.setReader(reader);

        repository.save(book);

        return "redirect:/reading-list/{reader}";
    }
}
