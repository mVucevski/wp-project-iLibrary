import React, { Component } from "react";
import { Link } from "react-router-dom";

class BookItem extends Component {
  render() {
    const { book } = this.props;
    return (
      <div className="col-lg-2 col-md-4 mb-4 d-flex align-items-stretch">
        <div className="card h-100">
          <Link to="/" style={{ textDecoration: "none" }} className="cards-a">
            <div className="card add-pointer">
              <img
                className="card-img-top card-img"
                src={book.coverUrl}
                alt=""
              />
              <div className="card-body h-100">
                <div className="card-title card-Title">{book.title}</div>
                <div className="card-text card-Author">{book.authorName}</div>
                <div className="star-ratings-sprite">
                  <span
                    style={{ width: "70%" }}
                    className="star-ratings-sprite-rating"
                  />
                </div>
                <div className="card-text">Available: Yes</div>
                <div className="card-text card-price">$15</div>
              </div>
            </div>
          </Link>
          <div className="card-footer">
            {/*
            <button className="btn btn-danger btn-block api-remove-from-cart">
            In Cart
          </button> 
            */}

            <button className="btn btn-success btn-block api-add-to-cart">
              Save
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default BookItem;
