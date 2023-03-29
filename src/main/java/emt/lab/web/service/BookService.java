package emt.lab.web.service;

import emt.lab.web.model.Author;
import emt.lab.web.model.Book;
import emt.lab.web.model.dto.BookDto;
import emt.lab.web.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, BookCategory category, Long author, int availableCopies);
    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id,String name,BookCategory category, Long author, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);
    void deleteById(Long id);

    Optional<Book> IznajmenaKniga(Long id);
}
