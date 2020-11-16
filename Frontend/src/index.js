import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App.jsx';
import * as serviceWorker from './serviceWorker';
import { ThemeProvider } from "@material-ui/core/styles";
import { createMuiTheme } from "@material-ui/core/styles"

const Theme = createMuiTheme({
    palette: {
        primary: {
            main: "#DEDEDE"
        },
        secondary: { main: "#FFD859" },
    },
    overrides: {
        MuiTextField: {
            root: {
                "&:hover:not($disabled):not($focused):not($error) $notchedOutline": {
                    borderColor: "#FFD859",
                },
                "&$focused": {
                    borderColor: "#DEDEDE",
                    borderWidth: 1
                }
            }
        },
        MuiFormLabel: {
            root: {
                color: "#FFD859",
                "&$focused": {
                color: "#FFD859"
                 }
            }
        }
    }
});



ReactDOM.render(
  <React.StrictMode>
      <ThemeProvider theme={Theme}>
    <App />
      </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
