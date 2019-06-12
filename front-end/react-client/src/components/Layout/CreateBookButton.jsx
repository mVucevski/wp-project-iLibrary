import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class CreateBookButton extends Component {
  render() {
    const { role } = this.props.security;
    return (
      <React.Fragment>
        {role === "ROLE_EMPLOYEE" ? (
          <Link
            to="/addBook"
            className="btn btn-outline-secondary my-1 my-sm-1 create-btn"
          >
            <i class="fas fa-plus-square mr-1" />
            Add Book
          </Link>
        ) : (
          <Link to="/userInfo" className="btn btn-outline-secondary">
            <i className="fas fa-user" />
          </Link>
        )}
      </React.Fragment>
    );
  }
}

CreateBookButton.propTypes = {
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security
});

export default connect(mapStateToProps)(CreateBookButton);
