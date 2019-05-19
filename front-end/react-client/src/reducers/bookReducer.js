import { GET_BOOK, GET_BOOKS } from "../actions/types";

const initialState = {
  books: [],
  book: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_BOOKS:
      return {
        ...state,
        books: action.payload
      };
    case GET_BOOK:
      return {
        ...state,
        book: action.payload
      };
    default:
      return state;
  }
}
