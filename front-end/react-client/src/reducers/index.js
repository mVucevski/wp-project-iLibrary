import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import bookReducer from "./bookReducer";
import statusReducer from "./statusReducer";

export default combineReducers({
  errors: errorReducer,
  book: bookReducer,
  status: statusReducer
});
