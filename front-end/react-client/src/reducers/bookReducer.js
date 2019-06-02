import {
  GET_BOOK,
  GET_BOOKS,
  DELETE_BOOK,
  ADD_RESERVATION
} from "../actions/types";

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
    case DELETE_BOOK:
      return {
        ...state,
        books: state.books.filter(b => b.isbn !== action.payload)
      };
    case ADD_RESERVATION:
      // const updatedBook = {
      //   ...state.book,
      //   reservations:
      // }

      return {
        ...state
      };

    default:
      return state;
  }
}
