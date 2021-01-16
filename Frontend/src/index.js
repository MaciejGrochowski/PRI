import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App.jsx';
import * as serviceWorker from './serviceWorker';
import { ThemeProvider } from "@material-ui/core/styles";
import { createMuiTheme } from "@material-ui/core/styles"
import {store} from "./store";
import { Provider } from "react-redux";

const Theme = createMuiTheme({
    palette: {
        primary: {
            main: "#FFD859"
        },
        secondary: { main: "#FFD859" },
    },
    overrides: {
        MuiTextField: {
            root: {
                "&$focused": {
                    borderColor: "#FFD859",
                    borderWidth: 1
                }
            }
        },
        MuiFormLabel: {
            root: {
                color: "#DEDEDE",
                "&$focused": {
                    color: "#FFD859"
                }
            }
        },
        MuiInputBase: {
            root: {
                    color: "rgba(222, 222, 222, 0.42)"

            }
        },
        MuiSvgIcon:{
            root:{
                cursor: "pointer"
            }
        }
    }
});



ReactDOM.render(
  <React.StrictMode>
      <ThemeProvider theme={Theme}>
          <Provider store={store}>
    <App />
          </Provider>
      </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
