import React, { Component } from "react";
import bookLogo from "../../assets/img/book.png";
import CreateBookButton from "./CreateBookButton";
import { Link } from "react-router-dom";

class Header extends Component {
  render() {
    return (
      <div>
        <nav className="navbar-light bg-light">
          <div className="container">
            <div className="row vertical-align">
              <div className="col-md-1 d-sm-none d-none d-md-block logo">
                <img
                  className="img"
                  style={{ height: "70px" }}
                  src={bookLogo}
                  alt=""
                />
              </div>
              <div className="col-md-2">
                <Link to="/" style={{ textDecoration: "none" }}>
                  <h2 className="Toptitle">
                    i<strong>Library</strong>
                  </h2>
                </Link>
              </div>
              <div className="col-md-6">
                <form className="form-inline">
                  <input
                    className="form-control mr-sm-2"
                    style={{ width: "70%" }}
                    type="search"
                    name="search"
                    id="search"
                    placeholder="Search for books by keyword / title / author / ISBN"
                    aria-label="Search"
                  />
                  <button
                    href="#"
                    style={{ textDecoration: "none" }}
                    className="btn btn-outline-success my-2 my-sm-0"
                    type="submit"
                    value="Search"
                  >
                    Search
                  </button>
                </form>
              </div>
              <div className="col-md-3">
                <div className="float-left float-md-right float-sm-left float-xl-right float-lg-right">
                  <CreateBookButton />
                  <i className="fa fa-heart pl-4 navIcons add-pointer">
                    <strong style={{ fontSize: "15px", display: "flex" }}>
                      Wishlist
                    </strong>
                  </i>
                  <i className="fa fa-shopping-cart pl-4 navIcons add-pointer">
                    <strong style={{ fontSize: "15px", display: "flex" }}>
                      3 Items
                    </strong>
                  </i>
                </div>
              </div>
            </div>
          </div>
          <hr className="hrNav" />
        </nav>

        <nav className="navbar navbar-expand-lg navbar-light bg-light my-0">
          <div className="container">
            <button
              className="navbar-toggler navbar-toggler-right"
              type="button"
              data-toggle="collapse"
              data-target="#navbarResponsive"
              aria-controls="navbarResponsive"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>
            <div className="collapse navbar-collapse">
              <ul className="navbar-nav" style={{ fontWeight: "bold" }}>
                <li className="nav-item">
                  <a
                    className="nav-link"
                    href="/"
                    style={{ textDecoration: "none" }}
                    alt=""
                  >
                    Bestsellers
                  </a>
                </li>
                <li className="nav-item">
                  <a
                    className="nav-link"
                    href="/"
                    style={{ textDecoration: "none" }}
                    alt=""
                  >
                    Latest Deals
                  </a>
                </li>
                <li className="nav-item">
                  <a
                    className="nav-link"
                    href="/"
                    style={{ textDecoration: "none" }}
                  >
                    Trade Section
                  </a>
                </li>
                <li className="nav-item">
                  <a
                    className="nav-link"
                    href="/"
                    style={{ textDecoration: "none" }}
                  >
                    Surprise Me
                  </a>
                </li>
                <li className="nav-item dropdown">
                  <a
                    className="nav-link dropdown-toggle"
                    href="/"
                    id="navbarDropdownPortfolio"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    Search by category
                  </a>
                  <div
                    className="dropdown-menu dropdown-menu-right"
                    aria-labelledby="navbarDropdownPortfolio"
                  >
                    <a className="dropdown-item" href="/">
                      Fiction
                    </a>
                    <a className="dropdown-item" href="/">
                      Non-Fiction
                    </a>
                    <a className="dropdown-item" href="/">
                      Crime & Thriller
                    </a>
                    <a className="dropdown-item" href="/">
                      Fantasy
                    </a>
                    <a className="dropdown-item" href="/">
                      History
                    </a>
                    <a className="dropdown-item" href="/">
                      Food & Drink
                    </a>
                  </div>
                </li>
              </ul>
              <ul className="navbar-nav ml-auto" style={{ fontWeight: "bold" }}>
                <ul className="nav navbar-nav navbar-right">
                  <li className="pr-2 py-1">
                    <div className="btn btn-outline-secondary my-1 my-sm-1 create-btn">
                      Register
                    </div>
                  </li>
                  <li className="pr-2 py-1">
                    <div className="btn btn-outline-secondary my-1 my-sm-1 create-btn">
                      Login
                    </div>
                  </li>
                </ul>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    );
  }
}

export default Header;
