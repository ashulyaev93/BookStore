package com.onevizion.bookShop.dao;

import lombok.var;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoCustomImpl implements BookDaoCustom{

    private JdbcTemplate jdbcTemplate;

    public BookDaoCustomImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookDTO> findAllByParameter(BookFilterDTO bookFilterDTO) {
        var table = DSL.table("book").where(where(bookFilterDTO));

        var query = DSL.select().from(table).orderBy(DSL.field("title").desc());

        List<BookDTO> bookDTOList = jdbcTemplate.query(
                query.getSQL(),
                (s, rowNum) -> BookDTO.builder()
                        .id(s.getLong(1))
                        .title(s.getString(2))
                        .author(s.getString(3))
                        .description(s.getString(4))
                        .build(),
                query.getBindValues().toArray());

        return bookDTOList;
    }

    private Condition where(BookFilterDTO bookFilterDTO){

        Condition where = DSL.noCondition();

        if (bookFilterDTO.getAuthor() != null) where = where.and(DSL.field("author").like("%" + bookFilterDTO.getAuthor() + "%"));

        return where;
    }
}