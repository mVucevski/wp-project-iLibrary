import axios from "axios";
import { ADD_LOAN, GET_LOAN, GET_ERRORS } from "./types";

export const addLoan = (isbn, username) => async dispatch => {
  try {
    const response = await axios.post(`api/loan/${isbn}`);

    dispatch({
      type: GET_LOAN,
      payload: response.data
    });

    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
