import React, { Component } from "react";

class AddReview extends Component {
  render() {
    return (
      <form>
        <div className="form-group">
          <label htmlFor="exampleFormControlSelect1">Rating</label>
          <select className="form-control">
            <option className="btn btn-outline-secondary">1</option>
            <option className="btn btn-outline-secondary">2</option>
            <option className="btn btn-outline-secondary">3</option>
            <option className="btn btn-outline-secondary">4</option>
            <option className="btn btn-outline-secondary">5</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="exampleFormControlTextarea1">Review</label>
          <textarea className="form-control" rows="3" />
        </div>
        <div className="row">
          <div className="col-md-9" />
          <div className="col-md-3">
            <input type="submit" className="btn btn-success btn-block mt-4" />
          </div>
        </div>
      </form>
    );
  }
}

export default AddReview;
