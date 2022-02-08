package com.onevizion.bookShop.mapper;

import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Named("bookToBookDTO")
    @Mappings({
            @Mapping(source="book.id", target="id"),
            @Mapping(source="book.title", target="title"),
            @Mapping(source="book.author", target="author"),
            @Mapping(source="book.description", target="description"),
    })
    BookDTO bookToBookDTO(Book book);
    @Mappings({
            @Mapping(source="bookDTO.id", target="id"),
            @Mapping(source="bookDTO.title", target="title"),
            @Mapping(source="bookDTO.author", target="author"),
            @Mapping(source="bookDTO.description", target="description"),

    })
    Book bookDTOtoBook(BookDTO bookDTO);
}