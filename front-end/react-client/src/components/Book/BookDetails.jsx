import React, { Component } from "react";
import { getBook } from "../../actions/bookActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class BookDetails extends Component {
  render() {
    return (
      <div className="container">
        <h4 style={{ fontWeight: "bold" }}>Title</h4>
        <hr />
        <dt />

        <div className="row py-4">
          <div className="col-md-3">
            <img
              src="https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/5538/9780553817188.jpg"
              className="img-fluid"
              alt=""
            />
          </div>
          <div className="col-md-6">
            <p className="lh">
              <strong>By (author) </strong>BookAuthor
            </p>
            <p className="lh">
              <strong>Language: </strong> Language
            </p>
            <p className="lh">
              <strong>ISBN: </strong>ISBN
            </p>
            <p className="lh">
              <strong>Category: </strong> Category
            </p>
            <p className="lh">
              <strong>Available for Trade: </strong> Yes
            </p>
            <div className="lh">
              <strong>Rating:</strong>
              <div className="star-ratings-sprite">
                <span
                  style={{ width: "70%" }}
                  className="star-ratings-sprite-rating"
                />
              </div>
              <span>5/5</span>
            </div>
            <br />
            <p className="lh">
              <strong>Book Condition:</strong> Description
            </p>
            <p className="lh">
              <strong>Seller: </strong> SellerName
            </p>
            <strong>Description:</strong>
            <p>BookDespript</p>
          </div>

          <div className="col-md-3 bookDetailsBorder">
            <div className="pt-2">
              {/* 
              <p style={{ fontSize: "25px" }}>
                <i className="fa fa-check-circle-o" />
                Available: 1
              </p>
            */}
              <p style={{ fontSize: "25px" }}>
                <i className="fa fa-times" /> Not Available
              </p>

              <button className="btn btn-block btn-success">Add to Cart</button>
              <button className="btn btn-block btn-danger">
                Add to Wishlist
              </button>
            </div>

            <div className="pt-2">
              <button className="btn btn-block btn-secondary">Edit</button>
              <button className="btn btn-block btn-danger">Delete</button>
            </div>

            <p style={{ fontSize: "25px" }}>Rate book:</p>
            <div id="rate">
              {/* 
                        @Html.ActionLink("1", "Rate", new { id = Model.ID, rating = 1 }, new { @className = "btn btn-outline-secondary" })
                            @Html.ActionLink("2", "Rate", new { id = Model.ID, rating = 2 }, new { @className = "btn btn-outline-secondary" })
                            @Html.ActionLink("3", "Rate", new { id = Model.ID, rating = 3 }, new { @className = "btn btn-outline-secondary" })
                            @Html.ActionLink("4", "Rate", new { id = Model.ID, rating = 4 }, new { @className = "btn btn-outline-secondary" })
                            @Html.ActionLink("5", "Rate", new { id = Model.ID, rating = 5 }, new { @className = "btn btn-outline-secondary" })
                        */}
              click to rate
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookDetails;
