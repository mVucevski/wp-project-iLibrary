import React, { Component } from "react";

class BookItem extends Component {
  render() {
    return (
      <div className="col-lg-2 col-md-4 mb-4 d-flex align-items-stretch">
        <div className="card h-100">
          <a href="#" style={{ textDecoration: "none" }} className="cards-a">
            <div className="card add-pointer">
              <img
                className="card-img-top card-img"
                src={require("../../assets/img/startWhy.jpg")}
                alt=""
              />
              <div className="card-body h-100">
                <div className="card-title card-Title">Start with Why</div>
                <div className="card-text card-Author">Avtor Avtorski</div>
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
          </a>
          <div className="card-footer">
            {/*
            <button className="btn btn-danger btn-block api-remove-from-cart">
            In Cart
          </button> 
            */}

            <button className="btn btn-success btn-block api-add-to-cart">
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default BookItem;
