package com.example.readinglist.controller;

import com.example.readinglist.model.Book;
import com.example.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadingListController {

    private ReadingListRepository repository;

    @Autowired
    public ReadingListController(ReadingListRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{reader}")
    public String readersBooks(@PathVariable String reader, Model model) {

        List<Book> readingList = repository.findByReader(reader);

        if (readingList != null)
            model.addAttribute("books", readingList);

        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable String reader, Book book) {

        book.setReader(reader);

        repository.save(book);

        return "redirect:/{reader}";
    }
}
