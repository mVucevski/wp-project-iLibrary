import React from "react";

const BookStatusItem = props => {
  const { item, id } = props;
  console.log("ITENL: ", props);
  let type = "Reservation";
  let trColor = "table-warning";

  if (item.due_date) {
    type = "Loan";
    trColor = "table-danger";
  }

  return (
    <tr className={trColor}>
      <td>{id}</td>
      <td>{item.created_At}</td>
      <td>{item.end_At || item.due_date}</td>
      <td>{type}</td>
    </tr>
  );
};

export default BookStatusItem;
