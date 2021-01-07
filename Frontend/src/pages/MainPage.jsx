import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import CookieConsent from "react-cookie-consent";


class MainPage extends React.Component {

    render(){
        return (
            <div className = "plainPage">
                        <h1>Welcome!</h1>
                        <p>Click  to see a greeting.</p>
                <Link to={fronendUrls.registerPage}>Zarejestruj się!</Link>
            </div>
        )
    }

}

export default MainPage;