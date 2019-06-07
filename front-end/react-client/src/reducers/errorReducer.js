import { GET_ERRORS } from "../actions/types";
import store from "../store";
import { logout } from "../actions/securityActions";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      // console.log("ERRRR:", action.payload);
      // if (action.payload.unauthorizedAccess === "Expired token") {
      //   store.dispatch(logout());
      // }
      return action.payload;

    default:
      return state;
  }
}
