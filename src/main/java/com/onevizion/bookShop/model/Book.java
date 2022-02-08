package com.onevizion.bookShop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table("book")
public class Book {

    @Id
    private Long id;
    @Column("title")
    private String title;
    @Column("author")
    private String author;
    @Column("description")
    private String description;
}
