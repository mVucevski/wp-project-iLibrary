import React, { Component } from "react";
import BookItem from "../Book/BookItem";

class Home extends Component {
  render() {
    return (
      <div className="container my-2">
        <h3>Bestselling Books</h3>
        <hr />
        <div className="row my-3">
          <BookItem />
          <BookItem />
          <BookItem />
        </div>
      </div>
    );
  }
}

export default Home;
