import React, { Component } from "react";
import { getBook, deleteBook } from "../../actions/bookActions";
import {
  addReservation,
  removeReservation
} from "../../actions/reservationActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import BookDoesntExist from "./BookDoesntExist";
import ReservationButton from "./ReservationButton";
import BookStatusTable from "./BookStatus/BookStatusTable";

class BookDetails extends Component {
  constructor() {
    super();

    this.state = {
      errors: {},
      reserved: false
    };
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBook(id, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    console.log("WILL RECIVE PROPS", nextProps);

    const { book } = this.props.book;

    if (book.reservations) {
      this.setState({
        reserved: book.reservations.some(item => item.bookISBN === book.isbn)
      });
    }
  }

  onDelete(isbn) {
    this.props.deleteBook(isbn, this.props.history);
  }

  onReservIt() {
    if (this.state.reserved) {
      this.props.removeReservation(this.props.book.book.isbn);
    } else {
      this.props.addReservation(this.props.book.book.isbn);
    }
  }

  // checkReservation(book) {
  //   console.log("CEHCK RESERVATION: ");

  //   this.setState({
  //     reserved: book.reservations.some(item => item.bookISBN === book.isbn)
  //   });
  // }

  render() {
    const { id } = this.props.match.params;
    const { book } = this.props.book;
    const { errors } = this.state;

    let pageContent;
    let checkRes = false;

    // if (book.reservations) {
    //   //checkRes = book.reservations.some(item => item.bookISBN === book.isbn);
    //   // console.log("CEHCKRES:::", checkRes);
    //   // if (checkRes !== this.state.reserved) {
    //   //   this.setState({ reserved: checkRes });
    //   // }
    //   this.checkReservation(book);
    // }

    if (errors.isbn) {
      pageContent = <BookDoesntExist error={errors.isbn} />;
    } else if (errors.availableCopies) {
      // Tmp, I will fix it
      pageContent = <BookDoesntExist error={errors.availableCopies} />;
    } else {
      pageContent = (
        <div>
          <h4 style={{ fontWeight: "bold" }}>{book.title}</h4>
          <hr />
          <dt />

          <div className="row py-4">
            <div className="col-md-3">
              <img src={book.coverUrl} className="img-fluid" alt="" />
            </div>
            <div className="col-md-6">
              <p className="lh">
                <strong>By (author) </strong>
                {book.authorName}
              </p>
              <p className="lh">
                <strong>Language: </strong> {book.language}
              </p>
              <p className="lh">
                <strong>ISBN: </strong>
                {book.isbn}
              </p>
              <p className="lh">
                <strong>Category: </strong> {book.genre}
              </p>
              <p className="lh">
                <strong>Available as e-Book: </strong> No
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
                <strong>Publication date:</strong> {book.publicationDate}
              </p>
              <p className="lh">
                <strong>Seller: </strong> SellerName
              </p>
              <strong>Description:</strong>
              <p>{book.description}</p>
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

                <Link
                  to={{
                    pathname: `/statusManager`,
                    state: {
                      book_isbn: book.isbn,
                      username: ""
                    }
                  }}
                  className="btn btn-block btn-info"
                >
                  Create Loan
                </Link>

                <ReservationButton
                  reserved={this.state.reserved}
                  onReservIt={this.onReservIt.bind(this)}
                />
              </div>

              <div className="pt-2">
                <Link
                  to={`/book/${book.isbn}/edit`}
                  className="btn btn-block btn-secondary"
                >
                  Edit
                </Link>
                <button
                  className="btn btn-block btn-danger"
                  onClick={this.onDelete.bind(this, id)}
                >
                  Delete
                </button>
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

          <div>
            <h4>Status</h4>
            <hr />
            {book.reservations && book.loans && (
              <BookStatusTable
                reservations={book.reservations}
                loans={book.loans}
              />
            )}
          </div>
        </div>
      );
    }

    return <div className="container">{pageContent}</div>;
  }
}

BookDetails.propTypes = {
  book: PropTypes.object.isRequired,
  getBook: PropTypes.func.isRequired,
  deleteBook: PropTypes.func.isRequired,
  addReservation: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  book: state.book,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { getBook, deleteBook, addReservation, removeReservation }
)(BookDetails);
