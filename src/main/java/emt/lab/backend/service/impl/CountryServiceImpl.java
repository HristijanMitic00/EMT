package emt.lab.backend.service.impl;

import emt.lab.backend.model.Country;
import emt.lab.backend.model.dto.CountryDto;
import emt.lab.backend.repository.CountryRepository;
import emt.lab.backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country c = new Country(countryDto.getName(),countryDto.getContinent());
        countryRepository.save(c);
        return Optional.of(c);
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country c = countryRepository.findById(id).orElseThrow(RuntimeException::new);
        c.setName(countryDto.getName());
        c.setContinent(countryDto.getContinent());
        countryRepository.save(c);
        return Optional.of(c);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
