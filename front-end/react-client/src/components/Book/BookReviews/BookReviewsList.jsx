import React, { Component } from "react";
import ReviewItem from "./ReviewItem";
import AddReview from "./AddReview";
import { getReviews, addReview } from "../../../actions/bookActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class BookReviewsList extends Component {
  constructor(props) {
    super();

    console.log("CONSTRUTOR:", props);
  }

  componentDidMount() {
    this.props.getReviews(this.props.isbn);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onAddReview(review) {
    if (review) {
      this.props.addReview(this.props.isbn, review);
    }
  }

  render() {
    const { reviews } = this.props.book;

    let counter = 1;
    let reviewList = "";

    if (reviews) {
      reviewList = reviews.map(r => <ReviewItem key={counter} item={r} />);
    }

    return (
      <div>
        <h4>Your Review</h4>
        <AddReview addReview={this.onAddReview.bind(this)} />
        <br />
        {reviewList}
      </div>
    );
  }
}

BookReviewsList.propTypes = {
  book: PropTypes.object.isRequired,
  getReviews: PropTypes.func.isRequired,
  addReview: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  book: state.book,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { getReviews, addReview }
)(BookReviewsList);
