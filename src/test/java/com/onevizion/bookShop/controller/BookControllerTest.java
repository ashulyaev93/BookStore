package com.onevizion.bookShop.controller;

import com.onevizion.bookShop.dao.BookDao;
import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;
import com.onevizion.bookShop.mapper.BookMapper;
import com.onevizion.bookShop.model.Book;
import com.onevizion.bookShop.service.BookServiceImpl;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookController.class)
public class BookControllerTest {

    private static BookDao bookDao = mock(BookDao.class);
    private static BookServiceImpl bookService = new BookServiceImpl(bookDao);

    BookController bookController = new BookController(
            bookService
    );

    @Test
    public void save(){

        BookDTO bookDTO = BookDTO.builder()
                .id(1L)
                .title("test")
                .author("alex")
                .description("testDesc").build();

        BookMapper.INSTANCE.bookDTOtoBook(bookDTO);

        Assert.assertEquals(bookController.save(bookDTO).getStatusCodeValue(),200);
    }

    @Test
    public void getAll(){

        String title = "test";
        String author = "alex";

        BookDTO bookDTO = BookDTO.builder()
                .id(1L)
                .title("test")
                .author("alex")
                .description("testDesc").build();

        BookFilterDTO bookFilterDTO = new BookFilterDTO(title,author);

        List<BookDTO> content = new ArrayList<>();
        content.add(bookDTO);

        List<BookDTO> bookDTOs = new ArrayList<>();

        when(bookService.getAll(bookFilterDTO)).thenReturn(bookDTOs);

        Assert.assertEquals(bookController.getAll(bookFilterDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void getById(){

        Long id = 1L;
        String title = "test";
        String author = "alex";
        String description = "testDesc";

        when(bookDao.findById(1L)).thenReturn(Optional.of(new Book(id, title, author, description)));
        Assert.assertEquals(bookController.getById(1L).getStatusCodeValue(), 200);
    }

    @Test
    public void updateTest(){

        BookDTO bookDTO = BookDTO.builder()
                .id(1L)
                .title("test")
                .author("alex")
                .description("testDesc")
                .build();

        Book book = BookMapper.INSTANCE.bookDTOtoBook(bookDTO);

        bookDTO.setTitle("test2");

        when(bookDao.findById(bookDTO.getId())).thenReturn(Optional.of(book));

        Assert.assertEquals(bookController.update(bookDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void deleteTest(){

        Assert.assertEquals(bookController.delete(1L).getStatusCodeValue(), 200);
    }
}
