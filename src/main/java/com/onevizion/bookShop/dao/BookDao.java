package com.onevizion.bookShop.dao;

import com.onevizion.bookShop.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends PagingAndSortingRepository<Book, Long>, BookDaoCustom {

}
