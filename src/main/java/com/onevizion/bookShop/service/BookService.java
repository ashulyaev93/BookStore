package com.onevizion.bookShop.service;

import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAll(BookFilterDTO bookFilterDTO);
    Optional<BookDTO> getById(Long id);
    BookDTO save(BookDTO bookDTO);
    Optional<BookDTO> update(BookDTO bookDTO);
    void delete(Long id);
}
