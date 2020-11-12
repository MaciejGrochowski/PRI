import React from "react";
import "./App.css";
import { BrowserRouter, Route } from "react-router-dom";
import interceptors from "../src/Interceptors";
import login from "./login";
import dashboard from "./dashboard";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Route exact path="/" component={login} />
          <Route exact path="/dashboard" component={dashboard} />
        </BrowserRouter>
      </header>
    </div>
  );
}

export default App;