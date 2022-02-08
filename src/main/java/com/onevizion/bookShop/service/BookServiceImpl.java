package com.onevizion.bookShop.service;

import com.onevizion.bookShop.dao.BookDao;
import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;
import com.onevizion.bookShop.mapper.BookMapper;
import com.onevizion.bookShop.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDTO> getAll(BookFilterDTO bookFilterDTO) {
        return bookDao.findAllByParameter(bookFilterDTO);
    }

    @Override
    public Optional<BookDTO> getById(Long id) {
        return bookDao.findById(id).map(BookMapper.INSTANCE::bookToBookDTO);
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {

        Book book = BookMapper.INSTANCE.bookDTOtoBook(bookDTO);
        if (Objects.isNull(bookDTO)) {
            return null;
        } else {
            bookDao.save(book);
        }
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }

    @Override
    public Optional<BookDTO> update(BookDTO bookDTO) {

        Optional<Book> optionalBook = bookDao.findById(bookDTO.getId());

        if(optionalBook.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(save(bookDTO));
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteById(id);
    }
}
