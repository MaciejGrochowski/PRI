import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class ErrorGenerator extends React.Component {

    render(){


        const {errorText} = this.props; //zmienna errorText to tekst błędu z backendu.
        return (<div className = "error-message">Error: {errorText}</div>

        )
    }

}

export default ErrorGenerator;