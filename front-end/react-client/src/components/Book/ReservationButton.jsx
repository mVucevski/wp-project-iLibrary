import React from "react";
import classnames from "classnames";

const ReservationButton = props => {
  let buttonText;
  if (props.reserved) {
    buttonText = "Remove Reservation";
  } else {
    buttonText = "Reserve It";
  }
  return (
    <React.Fragment>
      <button
        onClick={props.onClick}
        className={classnames("btn btn-block", {
          "btn-info": props.reserved,
          "btn-secondary": !props.reserved
        })}
      >
        {buttonText}
      </button>
    </React.Fragment>
  );
};

export default ReservationButton;
