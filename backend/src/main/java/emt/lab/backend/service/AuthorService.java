package emt.lab.backend.service;

import emt.lab.backend.model.dto.AuthorDto;
import emt.lab.backend.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);

}
