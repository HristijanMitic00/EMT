package emt.lab.backend.webControllers.rest;

import emt.lab.backend.model.Book;
import emt.lab.backend.model.dto.BookDto;
import emt.lab.backend.model.enumerations.BookCategory;
import emt.lab.backend.service.AuthorService;
import emt.lab.backend.service.BookService;
import emt.lab.backend.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookRestController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping("/books")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> findById(@PathVariable Long id){
        System.out.println("lala");
        return bookService.findById(id);
    }

    @GetMapping("/categories")
    public List<BookCategory> allCategories(){
        return List.of(BookCategory.values());
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto){
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping ("/books/taken/{id}")
    public ResponseEntity bookTaken(@PathVariable Long id){
//        return bookService.IznajmenaKniga(id)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
        if(this.bookService.IznajmenaKniga(id).isPresent()) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    }
