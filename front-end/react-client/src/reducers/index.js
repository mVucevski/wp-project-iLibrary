import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import bookReducer from "./bookReducer";

export default combineReducers({
  errors: errorReducer,
  book: bookReducer
});
