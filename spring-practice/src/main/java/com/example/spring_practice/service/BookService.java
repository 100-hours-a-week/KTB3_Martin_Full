package com.example.spring_practice.service;

import com.example.spring_practice.dto.BookDto;
import com.example.spring_practice.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookDto> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));

    }


    public BookDto createBook(BookDto book){
        return bookRepository.save(book);
    }
}
