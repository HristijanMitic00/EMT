import React from "react";
import {useNavigate} from "react-router-dom";

const BookTaken = (props) =>{

    const history = useNavigate();

    const onFormSubmit = (e) => {
        e.preventDefault();
        props.onMark(props.book.id);
        history("/books");
    }


    return(
        <form onSubmit={onFormSubmit}>
        <div><h1>Are you sure about taking this book?</h1>
            <h1>{props.book.name}</h1>
            <button id="submit" type="submit" className={"btn btn-primary"}>Yes</button>
        </div>
        </form>
    )
}

export default BookTaken;