package emt.lab.web.webControllers.rest;

import emt.lab.web.model.Author;
import emt.lab.web.model.Book;
import emt.lab.web.model.dto.AuthorDto;
import emt.lab.web.model.dto.BookDto;
import emt.lab.web.model.enumerations.BookCategory;
import emt.lab.web.service.AuthorService;
import emt.lab.web.service.BookService;
import emt.lab.web.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorRestController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Author> saveBook(@RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editBook(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if (this.authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
