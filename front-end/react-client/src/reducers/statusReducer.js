import { GET_LOAN, GET_MEMBERSHIP } from "../actions/types";

const initialState = {
  loan: {},
  membership: ""
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_LOAN:
      return {
        ...state,
        loan: action.payload
      };
    case GET_MEMBERSHIP:
      return {
        ...state,
        membership: action.payload
      };
    default:
      return state;
  }
}
