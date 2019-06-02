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
    //console.log("add Reservetion: ", response);

    dispatch(getBook(isbn, "null"));
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const removeReservation = isbn => async dispatch => {
  try {
    await axios.delete(`/api/reservation/isbn/${isbn}`);

    dispatch(getBook(isbn, "null"));
  } catch (error) {
    //console.log("Remove Reservation Error:", error.response.data);
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
