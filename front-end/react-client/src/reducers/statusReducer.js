import { GET_LOAN } from "../actions/types";

const initialState = {
  loan: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_LOAN:
      return {
        ...state,
        loan: action.payload
      };
    default:
      return state;
  }
}
