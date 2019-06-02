import React, { Component } from "react";

class LoanBook extends Component {
  render() {
    return (
      <div className="container my-2">
        <div className="row">
          <div className="col-md-12">
            <div className="card">
              <h5 className="card-header">Loan Application</h5>
              <div className="card-body">
                <form>
                  <div className="form-row">
                    <div className="col-md-5">
                      <label htmlFor="isbn">Book ISBN</label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="10 or 13 Digit ISBN"
                      />
                    </div>
                    <div className="col-md-5">
                      <label htmlFor="username">Username</label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="User's E-Mail"
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
          </div>
        </div>
      </div>
    );
  }
}

export default LoanBook;
