import React from "react";
import StarRating from "../StarRating";

const ReviewItem = props => {
  const { item } = props;

  return (
    <div>
      <hr />

      <div className="row">
        <span className="col-md-4">
          <StarRating rating={item.rating} />{" "}
        </span>
        <div className="col-md-4 text-success">
          <small className="center-block">{item.created_At}</small>
        </div>
      </div>

      <p className=" mt-2">{item.comment}</p>
    </div>
  );
};

export default ReviewItem;
