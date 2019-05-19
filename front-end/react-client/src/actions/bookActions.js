import axios from "axios";
import { GET_ERRORS } from "./types";

export const addBook = (book, history) => async dispatch => {
  try {
    const response = await axios.post("http://localhost:8080/api/book", book);
    history.push("/");
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
