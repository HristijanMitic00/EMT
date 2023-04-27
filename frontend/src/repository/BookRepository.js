import axios from "../custom-axios/axios";

const BookService = {
    fetchBooks:() => {
        return axios.get('/books');
    },
    fetchCategories:() => {
        return axios.get('/categories');
    },
    fetchAuthors:() => {
        return axios.get('/authors');
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name,author,bookCategory,availableCopies) =>{
        return axios.post('/books/add', {
            "name" : name,
            "author" : author,
            "bookCategory" : bookCategory,
            "availableCopies" : availableCopies
        })
    },
    editBook: (id,name,author,bookCategory,availableCopies) =>{
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "author" : author,
            "bookCategory" : bookCategory,
            "availableCopies" : availableCopies
        })
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    bookTaken: (id) => {
        return axios.put(`/books/taken/${id}`);
    }
};

export default BookService;