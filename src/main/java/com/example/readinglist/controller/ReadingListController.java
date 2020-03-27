package com.example.readinglist.controller;

import com.example.readinglist.model.Book;
import com.example.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository repository;

    @Autowired
    public ReadingListController(ReadingListRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable String reader, Model model) {

        List<Book> readingList = repository.findByReader(reader);

        if (readingList != null)
            model.addAttribute("books", readingList);

        // logical view name - "readingList"
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable String reader, Book book) {

        book.setReader(reader);

        repository.save(book);

        return "redirect:/{reader}";
    }
}
