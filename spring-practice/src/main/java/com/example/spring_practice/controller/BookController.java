package com.example.spring_practice.controller;

import com.example.spring_practice.dto.BookDto;
import com.example.spring_practice.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);

        return "books/detail";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/books";

    }




}
