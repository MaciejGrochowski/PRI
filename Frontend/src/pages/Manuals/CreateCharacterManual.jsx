import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../../styles/manual.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt, faInfoCircle} from "@fortawesome/free-solid-svg-icons";

const refresh = <FontAwesomeIcon icon={faSyncAlt}/>
const info = <FontAwesomeIcon icon={faInfoCircle}/>

class CreateCharacterManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Generowanie losowych i tworzenie własnych postaci.
                </div>
                Generowanie postaci dostępne jest z poziomu menu. Należy wybrac opcję <span className="yellow">Stwórz postać</span>.
                Postać wygenerować może każdy. Jeżeli jednak chcesz ją zapisać w naszej bazie i używać w czasie sesji wymagane jest abyś zalogował się na swoje konto.
                O tym, że zapomniałeś się zalogować poinformuje cię stosowna adnotacja.
                <span className="yellow"> Uwaga! Postaci nie można zmienić ani usunąć po ich zapisaniu. Stają się one ogólnie dostępne i każdy użytkownik aplikacji może z nich korzystać.</span>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Generowanie postaci</div>
                    Jeżeli chcesz szybko wygenerować postać i nie masz żadnych preferencji co do jej cech możesz skorzystać z przycisku <span className="yellow">Generuj losowe statystyki {refresh}</span>. Uruchomi on zaawansowany generator postaci uwzględniający takie dane jak poprzednie profesje przy wyborze obecnej czy procentowo większa szansa na zarost jeżeli wylosowana rasa to krasnolud. Możesz przejrzeć wynik generowania i zmienić cechy, które uznasz za takie, które ci nie pasują. Pamiętaj, że żeby uzyć postaci w trakcie sesji musisz zapisać postać.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Generowanie pojedynczej cechy</div>
                    Innym sposobem używania generatora jest losowanie pojedynczych cech. Gdy jakaś z wygenerowanych cech ci się nie spodoba możesz ją "przerzucić" z pomocą przycisku <span className="yellow">{refresh}</span> znajdującego się koło pola każdej cechy. Zwróć uwage, że większość przycisków jest wyszarzona co oznacza, że bez wypełnienia innych cech nie można wygenerować tej konkretnej. Wiąże się to z logiką generatora. Jeżeli chciałbyś wiedzieć które pola musisz wypełnić zanim wygenerujesz żądaną cechę najedź myszką na ikonę <span className="yellow">{info}</span>
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Generowanie statystyk bojowych</div>
                    Generowanie statystyk bojowych zostało rozbite na dwa przyciski <span className="yellow">Generuj statystyki bazowe {refresh}</span> i <span className="yellow">Generuj statystyki obecne{refresh}</span>
                </div>
            </div>
        </div>
        )
    }

}
export default CreateCharacterManual;
