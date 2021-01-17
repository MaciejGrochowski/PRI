import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../../styles/manual.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheckSquare, faEye, faUser, faTrash} from "@fortawesome/free-solid-svg-icons";


const check = <FontAwesomeIcon icon={faCheckSquare}/>
const eye = <FontAwesomeIcon icon={faEye}/>
const profile = <FontAwesomeIcon icon={faUser}/>
const trash = <FontAwesomeIcon icon={faTrash}/>
class SessionManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Tworzenie, prowadzenie i obsługa sesji.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Udział w sesji</div>
                    Jako niezalogowany użytkownik możesz korzystać z udostępnionych ci przez MG sesji za pośrednictwem generowanych w aplikacji linków. Nie możesz sam tworzyć sesji czy umieszczać w nich postaci. Możesz natomiast przejrzeć sesjie prowadzone przez zarejestrowanych użytkowników.
                    Znając nick konkretnej osoby możesz wejść na jej profil i w zakładce <span className="yellow">Sesje</span> przeczytać ich tytuły i opisy. Nie masz wglądu w to jak rozporzadzają sesją bez specjalnego linku udostepnianego przez MG.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Tworzenie sesji</div>
                    Jeżeli, jako zalogowany użytkownik, chcesz stworzyć nową sesję, musisz wybrać z menu opcję <span className="yellow"> Moje sesje</span> a nastepnie kliknąć przycisk <span className="yellow">Nowa sesja</span>. Wypełnij dane i kliknij <span className="yellow">Zapisz</span>. Pamiętaj, że każda sesja musi posiadać <span className="yellow">Tytuł</span>. Po stworzeniu zawsze też możesz zmienić tytuł lub opis sesji. Pamiętaj jednak, że dane te beda widoczne na twoim profilu w zakładce <span className="yellow">Prowadzone sesje</span>
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Dodawanie postaci do sesji</div>
                    Jeżeli utworzyłeś już sesję i chcesz dodać do niej postaci musisz udać się na podstronę <span className="yellow">Katalog postaci</span>. Wybierz odpowiadające ci postaci z pomocą filtrów oraz mechanizmu sortowania. Zaznacz je <span className="yellow">{check}</span> i kliknij przycisk <span className="yellow">Dodaj zaznaczone sesji</span>. W pojawiającym się okienku wybierz sesję do której dołączyć ma postać i zapisz. Po wybraniu tej opcji masz możliwość edycji widoczności atrybutów postaci w sesji.
                </div>

                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Manipulacja widocznością atrybutów postaci</div>
                    Po dodaniu postaci do sesji możesz ją zobaczyć jeżeli wejdziesz w <span className="yellow"> Moje sesje</span> a nastepnie klikniesz <span className="yellow"> Więcej</span> przy konkretnej sesji do której dodałeś postać. Postać miała zakryte wszystkie dane. Możesz ustawić jakie dane chcesz pokazać swoim graczom podczas sesji. Jeżeli chcesz aby wszystkie postaci miały odkryte/zakryte pewne konkretne atrybuty użyj przycisku <span className="yellow"> Globalna widoczność</span>. Pamiętaj, że tak zaaplikowane zmiany wpływają na <span className="yellow"> wszystkie </span> postaci w sesji.
                    Jeżeli chcesz edytować konkretną postać wybierz ikonke <span className="yellow"> {eye} </span> znajdująca się pod każdą z postaci w widoku sesji. Możesz wybrać atrybuy w analogiczny sposób do tych wybieranych globalnie. Ikona <span className="yellow">{trash}</span> usuwa postać z sesji, a ikona <span className="yellow">{profile}</span> pozwala szybko przejść na profil postaci by zobaczyć wszystkie jej atrybuty czy poczytac historie. <span className="yellow"> Pamiętaj, że przyciski te sa widoczne tylko dla Mistrza Gry! Inni uczestnicy sesji beda widzieć jedynie udostępnione im informacje</span>.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Link do udostępniania sesji</div>
                    Jako Mistrz Gry, na podstronie konkretnej sesji możesz znaleźć link do udostepniania przygotowanej sesji innym graczom. Prześlij go pozostałym uczestnikokm sesji. Gracze ci nie muszą być zalogowani do aplikacji i bedą widzieć jedynie <span className="yellow">tytuł, opis i udostepnione im postaci i ich atrybuty</span>. Możesz przyjąć, że gracze widzą dokładnie taką samą stronę jak ty, jednak nie mogą niczego tam zmienić, edytować lub kliknąć. Dzięki takiemu rozwiązaniu podczas sesji, na bieżąco możesz odkrywac kolejne cechy postaci jakie napotykają bohaterowie.
                </div>

            </div>
        </div>
        )
    }

}
export default SessionManual;