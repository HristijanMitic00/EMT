import React from "react";
import {useNavigate} from "react-router-dom";
import categories from "../../Categories/categories";

const BookEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        author: 0,
        bookCategory: -1,
        availableCopies : 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;
        const bookCategory = formData.bookCategory !== -1 ? formData.bookCategory : props.book.category;

        props.onEditBook(props.book.id,name, author, bookCategory, availableCopies);
        history("/books");
    }

    return (
        <div className={"row mt-5"}>
            <div className={"col md-5"}>
                <form onSubmit={onFormSubmit}>
                    <div className={"form-group"}>
                        <label htmlFor={"name"}>Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name={"name"}
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor={"availableCopies"}>Available copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name={"availableCopies"}
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if (props.book.author !== undefined && props.book.author.id === term.id)
                                    return <option key={term.id} selected={props.book.author.id} value={term.id}>{term.name}</option>
                                else
                                    return <option key={term.id} value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <div className={"form-group"}>
                        <label>Book category</label>
                        <select name="bookCategory" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                if (props.book.category !== undefined && props.book.category === term)
                                    return <option key={term} selected={props.book.category} value={term}>{term}</option>
                                else
                                    return <option key={term} value={term}>{term}</option>
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className={"btn btn-primary"}>Submit</button>
                </form>
            </div>
        </div>

    )
};

export default BookEdit;