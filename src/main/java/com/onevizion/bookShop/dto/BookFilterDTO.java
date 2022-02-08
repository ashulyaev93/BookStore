package com.onevizion.bookShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookFilterDTO {
    private String title;
    private String author;
}
