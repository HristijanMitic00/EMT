package emt.lab.web.model;

import emt.lab.web.model.enumerations.BookCategory;
import jakarta.persistence.*;
import lombok.Data;




@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private BookCategory category;

    @ManyToOne
    private Author author;

    private int availableCopies;


    public Book() {
    }

    public Book(String name, BookCategory category, Author author, int availableCopies) {
        this.name=name;
        this.category=category;
        this.author=author;
        this.availableCopies=availableCopies;
    }
}
