import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min";
import "./App.css";
import Home from "./Home/Home";
import Header from "./Layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddBook from "./Book/AddBook";
import { Provider } from "react-redux";
import store from "../store";
import BookDetails from "./Book/BookDetails";
import EditBook from "./Book/EditBook";
import LoanBook from "./StatusManager/LoanBook";
import { SET_CURRENT_USER } from "../actions/types";
import { logout } from "../actions/securityActions";
import jwt_decode from "jwt-decode";
import setJWTToken from "../securityUtils/setJWTToken";
import Register from "./UserManagment/Register";
import Login from "./UserManagment/Login";
import SecuredRoute from "../securityUtils/SecuredRoute";

const jwtToken = localStorage.jwtToken;
const userRole = localStorage.userRole;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: {
      user: decoded_jwtToken,
      role: userRole
    }
  });

  const currentTime = Date.now() / 1000;

  console.log("cureetnTIME:" + currentTime + " ext: " + decoded_jwtToken.ext);

  if (decoded_jwtToken.ext < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

const roleUser = "ROLE_USER";
const roleAdmin = "ROLE_ADMIN";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <hr className="hrNav" />
          <br />
          <Route exact path="/" component={Home} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/login" component={Login} />

          <SecuredRoute
            allowedRoles={[roleAdmin]}
            exact
            path="/addBook"
            component={AddBook}
          />
          <Route exact path="/book/:id" component={BookDetails} />
          <Route exact path="/book/:id/edit" component={EditBook} />

          <Route exact path="/statusManager" component={LoanBook} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;

//allowedRoles={"ROLE_USER"}
