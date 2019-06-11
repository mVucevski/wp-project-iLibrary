import React from "react";
import StarRating from "../StarRating";

const ReviewItem = props => {
  const { item } = props;

  return (
    <div>
      <hr />
      <StarRating rating={item.rating} />
      <p className=" mt-2">{item.comment}</p>
    </div>
  );
};

export default ReviewItem;
