package emt.lab.web.service;

import emt.lab.web.model.Author;
import emt.lab.web.model.Book;
import emt.lab.web.model.Country;
import emt.lab.web.model.dto.AuthorDto;
import emt.lab.web.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);

}
