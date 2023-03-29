package emt.lab.web.service.impl;

import emt.lab.web.model.Author;
import emt.lab.web.model.Country;
import emt.lab.web.model.dto.AuthorDto;
import emt.lab.web.repository.AuthorRepository;
import emt.lab.web.repository.CountryRepository;
import emt.lab.web.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country c = countryRepository.findById(authorDto.getCountryId()).orElseThrow(RuntimeException::new);
        Author a = new Author(authorDto.getName(),authorDto.getSurname(),c);
        authorRepository.save(a);
        return Optional.of(a);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Country c = countryRepository.findById(authorDto.getCountryId()).orElseThrow(RuntimeException::new);
        Author a = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        a.setCountry(c);
        a.setName(authorDto.getName());
        a.setSurname(authorDto.getSurname());
        authorRepository.save(a);
        return Optional.of(a);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
