import axios from "axios";
import {
  GET_RESERVATIONS,
  DELETE_RESERVATION,
  ADD_RESERVATION,
  GET_ERRORS
} from "./types";
import { getBook } from "./bookActions";

export const addReservation = isbn => async dispatch => {
  try {
    const response = await axios.post(`/api/reservation/${isbn}`);
    console.log("add Reservetion: ", response);
    // dispatch({
    //   type: ADD_RESERVATION,
    //   action: response.data
    // });
    dispatch(getBook(isbn, "null"));
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
