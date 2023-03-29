package emt.lab.web.webControllers.controllers;


import emt.lab.web.model.Author;
import emt.lab.web.model.Book;
import emt.lab.web.model.enumerations.BookCategory;
import emt.lab.web.service.AuthorService;
import emt.lab.web.service.BookService;
import emt.lab.web.service.CountryService;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/", "/books"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @GetMapping
    public String GetAllBooksPage(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "books-list";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<BookCategory> bookCategories = List.of(BookCategory.values());
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", bookCategories);
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam(required = false) Long id,
                          @RequestParam String name,
                          @RequestParam BookCategory bookCategory,
                          @RequestParam Long author,
                          @RequestParam int availabelCopies) {
        if (id == null) {
            bookService.save(name, bookCategory, author, availabelCopies);
        } else {
            bookService.edit(id, name, bookCategory, author, availabelCopies);
        }
        return "redirect:/books-list";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        Book b = bookService.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("book", b);
        List<BookCategory> bookCategories = List.of(BookCategory.values());
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", bookCategories);
        return "redirect:/add-book";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books-list";
    }

    @PostMapping("/mark-taken/{id}")
    public String markBookAsTaken(@PathVariable Long id){
        bookService.IznajmenaKniga(id);
        return "redirect:/books-list";
    }

}
