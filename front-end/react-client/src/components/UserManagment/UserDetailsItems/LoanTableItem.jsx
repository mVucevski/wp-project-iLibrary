import React from "react";

const LoanTableItem = props => {
  const { item, id } = props;

  let returned = <i class="fas fa-minus-square" />;
  let trColor = "table-warning";

  if (item.returned_At) {
    trColor = "table-success";
    returned = <i class="fas fa-check-square" />;
  }

  return (
    <tr className={trColor}>
      <td>{id}</td>
      <td>{item.bookISBN}</td>
      <td>{item.created_At}</td>
      <td>{item.due_date}</td>
      <td>{item.fee}</td>
      <td>{returned}</td>
      <td />
    </tr>
  );
};

export default LoanTableItem;
