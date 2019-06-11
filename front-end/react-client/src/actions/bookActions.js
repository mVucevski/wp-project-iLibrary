import axios from "axios";
import {
  GET_ERRORS,
  GET_BOOKS,
  GET_BOOK,
  DELETE_BOOK,
  GET_REVIEWS
} from "./types";

export const addBook = (book, history) => async dispatch => {
  try {
    const response = await axios.post("/api/book", book);
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

export const getBooks = () => async dispatch => {
  try {
    const response = await axios.get("/api/book/all");

    dispatch({
      type: GET_BOOKS,
      payload: response.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getBook = (isbn, history) => async dispatch => {
  try {
    const response = await axios.get(`/api/book/${isbn}`);
    dispatch({
      type: GET_BOOK,
      payload: response.data
    });
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    //console.log("ERROR:", error);

    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const deleteBook = (isbn, history) => async dispatch => {
  if (window.confirm("Are you sure? This book will be deleted permanently!")) {
    await axios.delete(`/api/book/${isbn}`);
    dispatch({
      type: DELETE_BOOK,
      payload: isbn
    });
    history.push("/");
  }
};

export const updateBook = (isbn, updatedBook, history) => async dispatch => {
  try {
    await axios.patch(`/api/book/${isbn}`, updatedBook);
    history.push(`/book/${isbn}`);
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

export const addReview = (isbn, review) => async dispatch => {
  try {
    const response = await axios.post(`/api/rating/${isbn}`, review);

    dispatch(getBook(isbn, "null"));
    dispatch(getReviews(isbn));
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getReviews = isbn => async dispatch => {
  try {
    const response = await axios.get(`/api/rating/${isbn}`);
    dispatch({
      type: GET_REVIEWS,
      payload: response.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
