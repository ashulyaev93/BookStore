package com.onevizion.bookShop.dao;

import com.onevizion.bookShop.dto.BookDTO;
import com.onevizion.bookShop.dto.BookFilterDTO;

import java.util.List;

public interface BookDaoCustom {
    List<BookDTO> findAllByParameter(BookFilterDTO bookFilterDTO);
}
