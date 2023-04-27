import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Books from '../Books/BookList/books'
import BookService from "../../repository/BookRepository";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/BookAdd";
import BookEdit from "../Books/BookEdit/BookEdit";
import BookTaken from "../Books/BookTaken/BookTaken";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className='container'>
                        <Routes>
                            <Route path={"/books/add"} element={<BookAdd categories={this.state.categories}
                                                                         authors={this.state.authors}
                                                                         onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element={<BookEdit categories={this.state.categories}
                                                                               authors={this.state.authors}
                                                                               onEditBook={this.editBook}
                                                                               book={this.state.selectedBook}/>}/>
                            <Route path={"/books/taken/:id"} element={<BookTaken onMark={this.bookTaken}
                                                                        book={this.state.selectedBook}/>}/>
                            <Route path={"/books"}
                                   element={<Books books={this.state.books}
                                                   onMark={this.getBook}
                                                   onDelete={this.deleteBook}
                                                   onEdit={this.getBook}/>}/>
                            <Route path={"/categories"} exact
                                   element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/"} element={<Books books={this.state.books}
                                                              onMark={this.getBook}
                                                              onDelete={this.deleteBook}
                                                              onEdit={this.getBook}/>}/>
                        </Routes>
                        {/*<Redirect to={"/books"}/>*/}
                    </div>
                </main>
            </Router>
        );
    }

    loadBooks = () => {
        BookService.fetchBooks().then((data) => {
            this.setState({
                books: data.data
            })
        })
    }

    loadCategories = () => {
        BookService.fetchCategories().then((data) => {
            this.setState({
                categories: data.data
            })
        })
    }

    loadAuthors = () => {
        BookService.fetchAuthors().then((data) => {
            this.setState({
                authors: data.data
            })
        })
    }

    deleteBook = (id) => {
        BookService.deleteBook(id).then(() => {
            this.loadBooks();
        })
    }


    addBook = (name, author, category, availableCopies) => {
        BookService.addBook(name, author, category, availableCopies).then(
            () => {
                this.loadBooks();
            })
    }

    getBook = (id) => {
        BookService.getBook(id).then((data) => {
            this.setState({
                selectedBook: data.data
            })
        })
    }

    editBook = (id, name, author, category, availableCopies) => {
        BookService.editBook(id, name, author, category, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    bookTaken = (id) => {
        BookService.bookTaken(id).then(() => {
            this.loadBooks();
        });
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }
}

export default App;
