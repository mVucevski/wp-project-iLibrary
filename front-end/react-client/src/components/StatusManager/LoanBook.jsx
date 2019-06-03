import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { addLoan } from "../../actions/loanActions";
import LoanDetails from "./LoanDetails";

class LoanBook extends Component {
  constructor() {
    super();

    this.state = {
      book_isbn: "",
      username: "",
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    console.log("LoanBook-nextProps:", nextProps);
  }

  componentDidMount() {
    if (this.props.location.state) {
      const { book_isbn, username } = this.props.location.state;

      this.setState({
        ...this.state,
        book_isbn: "" || book_isbn,
        username: "" || username
      });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const createdLoan = {
      book_isbn: this.state.book_isbn,
      username: this.state.username
    };

    this.props.addLoan(this.state.book_isbn, this.state.username);
  }

  render() {
    const { errors } = this.state;
    const { loan } = this.props;

    return (
      <div className="container my-2">
        <div className="row">
          <div className="col-md-12">
            <div className="card">
              <h5 className="card-header">Loan Application</h5>
              <div className="card-body">
                <form onSubmit={this.onSubmit}>
                  <div className="form-row">
                    <div className="col-md-5">
                      <label htmlFor="isbn">Book ISBN</label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="10 or 13 Digit ISBN"
                        name="book_isbn"
                        value={this.state.book_isbn}
                        onChange={this.onChange}
                      />
                    </div>
                    <div className="col-md-5">
                      <label htmlFor="username">Username</label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="User's E-Mail"
                        name="username"
                        value={this.state.username}
                        onChange={this.onChange}
                      />
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="m">&nbsp;</label>
                      <button
                        type="submit"
                        className="btn btn-success form-control"
                      >
                        Submit
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            {Object.values(errors)[0] && (
              <div className="alert alert-danger mt-2" role="alert">
                {Object.values(errors)[0]}
              </div>
            )}
          </div>
        </div>
        {loan.bookISBN && <LoanDetails loan={loan} />}
      </div>
    );
  }
}

LoanBook.propTypes = {
  errors: PropTypes.object.isRequired,
  addLoan: PropTypes.func.isRequired,
  loan: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors,
  loan: state.status.loan
});

export default connect(
  mapStateToProps,
  { addLoan }
)(LoanBook);
