import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../../styles/manual.css";



class CreateHistoryManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Tworzenie historii.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Tworzenie historii</div>
                    Historie są to opowieści z życia konkretnego bohatera. Nakreślające jego przeszłość. Muszą być osadzone w czasie i dzieć się w konkretnym miejscu dlatego też <span className="yellow">wymagane jest podanie dokładnej daty</span>.
                    <span className="yellow"> Miejsce</span> wybieramy z listy. Zauważ, że na liście istnieją nie tylko konkretne <span className="yellow">miasta</span>, ale także <span className="yellow">landy</span> czy bardzo ogólny <span className="yellow">świat</span> co pozwala umiejscowić te z historii dziejacych się na szlaku.
                    Każda historia powinna mieć swój tytuł, gdyż to po nim najłatwiej będzie daną historię wyszukać.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Dodawanie postaci do historii</div>
                    W historii możemy umieścić konkretną postać z bazy danych postaci w aplikacji. Aby to zrobić w polu wpisywania historii w dowolnym momencie nalezy posłużyć się adnotacją <span className="yellow">@ImięPostaci#ID</span>. Po wpisaniu kilku początkowych liter aplikacja sama powinna podpowiedzieć możliwe opcje. Wtedy też można wybrać je po prostu z wygodnej listy. Pamiętaj, że w historii może uczestniczyc kilka postaci i wszystkie powinny być otagowane, aby było wiadomo o kim mowa.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Zalogowany czy nie?</div>
                    Aby zapisać historię musisz być zalogowany na swoje konto. Jeźeli nie jesteś zalogowany na górze strony wyświetli się odpowiednia adnotacja proszaca abyś to zrobił.
                </div>
            </div>
        </div>
        )
    }

}
export default CreateHistoryManual;