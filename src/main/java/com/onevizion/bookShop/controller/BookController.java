package com.onevizion.bookShop.controller;

import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;
import com.onevizion.bookShop.service.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Controller
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll(BookFilterDTO bookFilterDTO) {

        List<BookDTO> bookDTOs = bookService.getAll(bookFilterDTO).stream().filter(p->!p.getDescription().isEmpty()).distinct().collect(Collectors.toList())
                .stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(BookDTO::getDescription))), ArrayList::new));

        return ResponseEntity.ok(bookDTOs);
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {

        bookDTO = bookService.save(bookDTO);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {

        Optional<BookDTO> bookDTO = bookService.getById(id);

        if(bookDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(bookDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        bookService.delete(id);

        return ResponseEntity.ok("Book with id = " + id + " deleted!");
    }

    @PutMapping
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO) {

        Optional<BookDTO> optional = bookService.update(bookDTO);

        if(optional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(optional.get());
    }
}
