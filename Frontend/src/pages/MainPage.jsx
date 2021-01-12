import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import CookieConsent from "react-cookie-consent";
import "../styles/homepage.css"

class MainPage extends React.Component {

    render(){
        return (
            <div className = "container">
            <div className = "logo">
                        <img src={process.env.PUBLIC_URL + '/logo.png'}/>
            </div>
                        <div className="welcome-text">Aplikacja "Ja Nie Taki Ork " jest interaktywną pomocą dla graczy, jak i mistrzów gry, rozgrywających sesje w systemie Warhammer Fantasy Roleplay. Oferuje rozbudowany generator postaci dostosowany do realiów świata Warhammera i wolny od wielu bolączek losowych generatorów dostępnych na innych stronach. System pozwala przeglądać bogatą listę już stworzonych postaci i dodawać historie symulując żyjący i rozbudowany świat. Możliwość grupowania postaci w sesje  sprawia, że zapanowanie nad bałaganem informacyjnym powstającym przy toczących się online przygodach staje się możliwe.</div>
                <Link to={fronendUrls.registerPage}>Zarejestruj się!</Link>
            </div>
        )
    }

}

export default MainPage;