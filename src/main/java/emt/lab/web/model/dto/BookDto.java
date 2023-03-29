package emt.lab.web.model.dto;


import emt.lab.web.model.enumerations.BookCategory;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private BookCategory bookCategory;
    private Long author;
    private int availableCopies;

    public BookDto() {
    }

    public BookDto(String name, BookCategory bookCategory, Long author, int availableCopies) {
        this.name = name;
        this.bookCategory = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
