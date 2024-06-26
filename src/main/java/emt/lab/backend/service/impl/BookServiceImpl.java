package emt.lab.backend.service.impl;


import emt.lab.backend.model.Book;
import emt.lab.backend.model.Country;
import emt.lab.backend.model.dto.BookDto;
import emt.lab.backend.model.Author;
import emt.lab.backend.model.enumerations.BookCategory;
import emt.lab.backend.repository.AuthorRepository;
import emt.lab.backend.repository.BookRepository;
import emt.lab.backend.repository.CountryRepository;
import emt.lab.backend.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name,BookCategory category, Long author, int availableCopies) {
        //Country c = new Country("Europe", "Europe");
        //Author a = new Author("NAme","Surname", c);
        Author a = authorRepository.findById(author).orElseThrow(RuntimeException::new);
        Book b = new Book(name,category,a,availableCopies);
        bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author a = authorRepository.findById(bookDto.getAuthor()).orElseThrow(RuntimeException::new);
        Book b = new Book(bookDto.getName(),bookDto.getBookCategory(), a, bookDto.getAvailableCopies());
        bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, BookCategory category, Long author, int availableCopies) {
        Author a = authorRepository.findById(author).orElseThrow(RuntimeException::new);
        Book b = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        b.setName(name);
        b.setCategory(category);
        b.setAuthor(a);
        b.setAvailableCopies(availableCopies);
        bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book b = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        Author a = authorRepository.findById(bookDto.getAuthor()).orElseThrow(RuntimeException::new);

        b.setName(bookDto.getName());
        b.setAuthor(a);
        b.setCategory(bookDto.getBookCategory());
        b.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(b);

        return Optional.of(b);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> IznajmenaKniga(Long id) {
        Book b = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        if (b.getAvailableCopies() <= 0){
            b.setAvailableCopies(0);
            return Optional.of(b);
        }
        b.setAvailableCopies(b.getAvailableCopies()-1);
        bookRepository.save(b);
        return Optional.of(b);
    }
}
