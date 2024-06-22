package emt.lab.backend;

import emt.lab.backend.model.Author;
import emt.lab.backend.model.Book;
import emt.lab.backend.model.Country;
import emt.lab.backend.model.enumerations.BookCategory;
import emt.lab.backend.repository.AuthorRepository;
import emt.lab.backend.repository.BookRepository;
import emt.lab.backend.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(BookRepository bookRepository, AuthorRepository authorRepository,
                                  CountryRepository countryRepository) {
        return args -> {
            Country c1 = new Country("Macedonia","Europe");
            Country c2 = new Country("England","Europe");
            Country c3 = new Country("Serbia","Europe");
            Country c4 = new Country("Germany","Europe");
            Country c5 = new Country("France","Europe");


            countryRepository.saveAll(List.of(c1, c2, c3, c4, c5));

            // Create authors
            Author a1 = new Author("Marko","Nikolovski", c1);
            Author a2 = new Author("Roman","James", c2);
            Author a3 = new Author ("Jones","Clark", c3);
            Author a4 = new Author ("Paul","Pol", c4);
            Author a5 = new Author("Jeremy","Jonas", c5);


            authorRepository.saveAll(List.of(a1, a2, a3, a4, a5));


            Book b1 = new Book("Math1", BookCategory.DRAMA,a1,5);
            Book b2 = new Book("Calculus", BookCategory.THRILER,a2,4);
            Book b3 = new Book("Databases", BookCategory.FANTASY,a3,3);
            Book b4 = new Book("Data science", BookCategory.HISTORY,a4,2);
            Book b5 = new Book("Math 2", BookCategory.CLASSICS,a5,1);


            bookRepository.saveAll(List.of(b1, b2, b3, b4, b5));

        };
    }
}
