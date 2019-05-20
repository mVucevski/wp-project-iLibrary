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

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <hr className="hrNav" />
          <br />
          <Route exact path="/" component={Home} />
          <Route exact path="/addBook" component={AddBook} />
          <Route exact path="/book/:id" component={BookDetails} />
          <Route exact path="/book/:id/edit" component={EditBook} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
