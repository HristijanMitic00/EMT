package emt.lab.web.service;

import emt.lab.web.model.Author;
import emt.lab.web.model.Country;
import emt.lab.web.model.dto.AuthorDto;
import emt.lab.web.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);

    Optional<Country> edit(Long id,CountryDto countryDto);

    void deleteById(Long id);
}
