import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min";
import "./App.css";
import Home from "./Home/Home";
import Header from "./Layout/Header";

function App() {
  return (
    <div className="App">
      <Header />
      <hr className="hrNav" />
      <br />
      <Home />
    </div>
  );
}

export default App;
