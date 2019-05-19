import React from "react";
import { Link } from "react-router-dom";

const CreateBookButton = () => {
  return (
    <React.Fragment>
      <Link
        to="/addBook"
        className="btn btn-outline-secondary my-1 my-sm-1 create-btn"
      >
        Create
      </Link>
    </React.Fragment>
  );
};

export default CreateBookButton;
