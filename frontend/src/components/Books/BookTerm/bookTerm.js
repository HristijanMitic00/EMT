import React from "react";
import {Link} from "react-router-dom";

const bookTerm = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.author.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td scope={"col"} className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger m-md-1"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete</a>
                <Link to={`/books/edit/${props.term.id}`}
                    className={"btn btn-info m-md-1"} onClick={() => props.onEdit(props.term.id)}>
                    Edit</Link>
                {/*<a title={"bookTaken"} className={"btn btn-success m-md-1"}*/}
                {/*   onClick={() => props.onMark(props.term.id)}>*/}
                {/*    Book taken</a>*/}
                <Link to={`/books/taken/${props.term.id}`}
                      className={"btn btn-success m-md-1"} onClick={() => props.onEdit(props.term.id)}>
                    Book taken</Link>
            </td>
        </tr>
    )
}

export default bookTerm;