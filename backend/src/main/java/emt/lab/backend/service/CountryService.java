package emt.lab.backend.service;

import emt.lab.backend.model.Country;
import emt.lab.backend.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);

    Optional<Country> edit(Long id,CountryDto countryDto);

    void deleteById(Long id);
}
