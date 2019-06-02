import React from "react";
import classnames from "classnames";

const ReservationButton = props => {
  let buttonText;

  console.log("PROPS2313:", props);

  if (props.reserved) {
    buttonText = "Remove Reservation";
  } else {
    buttonText = "Reserve It";
  }
  return (
    <React.Fragment>
      <button
        onClick={props.onReservIt}
        className={classnames("btn btn-block", {
          "btn-info": !props.reserved,
          "btn-danger": props.reserved
        })}
      >
        {buttonText}
      </button>
    </React.Fragment>
  );
};

export default ReservationButton;
