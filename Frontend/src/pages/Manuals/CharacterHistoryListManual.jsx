import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "../../styles/manual.css";
import {faArrowUp, faSearch, faTasks, faCheckSquare, faAngleLeft, faAngleRight} from "@fortawesome/free-solid-svg-icons";

const arrow_up = <FontAwesomeIcon icon={faArrowUp}/>
const search = <FontAwesomeIcon icon={faSearch}/>
const tasks = <FontAwesomeIcon icon={faTasks}/>
const check = <FontAwesomeIcon icon={faCheckSquare}/>
const elementPrev = <FontAwesomeIcon icon={faAngleLeft}/>
const elementNext = <FontAwesomeIcon icon={faAngleRight}/>

class CharacterHistoryListManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Listy postaci i historii. Wyszukiwanie i filtrowanie.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Wyszukiwanie istniejącej postaci</div>
                    Aby wyszukać postać musisz w menu wybrać opcję <span className="yellow">Katalog Postaci</span>.
                    Na stronie katalogu znajduje się podstawowy zestaw filtrów po których możesz wyszukać postać. <span className="yellow">Wprowadź po prostu interesujące cię dane</span> i kliknij przycisk <span className="yellow">Wyszukaj {search}</span>.
                    Wyniki powinny pojawić się w tabeli poniżej.
                    <div>Pamiętaj, że konkretna postać niekoniecznie znajduje się na pierwszej stronie. Na dole tabeli znajdziesz przyciski pozwalające przemieszczać się pomiędzy stronami tabeli.
                    Obok znajduje się również lista rozwijana pozwalająca wybrać jak długa powinna być tabela na stronie. Wybranie jednej z opcji zmienia ilość postaci widocznych na stronie.</div>
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Sortowanie</div>
                    Po wypełnieniu filtrów i wyszukaniu interesujacej grupy postaci możemy je posortować, <span className="yellow">czyli ułożyć w porządku alfabetycznym wyniki wyszukiwania</span>. Jeżeli chcemy to zrobić należy <span className="yellow">kliknąc na nagłówek tabeli</span> filtra po którym chcemy sortować.
                    Zwróć uwagę, że nie po wszystkich kolumnach możemy sortować. Po najechaniu myszką na nagłówek kolumny pojawia się <span className="yellow">strzałka {arrow_up}</span>. Pojedyncze kliknięcie uporządkuje wyniki w porzadku alfabetycznym, drugie kliknięcie odwróci wynik. Trzecie kliknięcie wyłączy sortowanie po tej kolumnie.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">Zaawansowane filtrowanie</div>
                    Jeżeli nie odpowiadają ci filtry, które są widoczne na stronie możesz zawsze dostosowac je do swoich potrzeb. Zwróć uwagę, że wybrane filtry odpowiadają kolumnom tabeli w której widzisz postaci. W ten sposób możesz dostosować zarówno filtry jak i kolumny.
                    W tym celu kliknij przycisk <span className="yellow">Dostosuj filtry {tasks} </span>(znajdziesz go obok przycisku "Wyszukaj") otworzy się okienko z wszystkimi możliwymi filtrami. <span className="yellow">Zaznacz {check}</span> interesujące cię i kilknij <span className="yellow">Zastosuj</span>.
                </div>
                <div className="manual-paragraf">
                    <div className="manual-paragraf-title">Detale postaci</div>
                    Jeżeli chcesz podejrzec wszystkie statystyki danej postaci (w tym również jej statystyki bojowe czy historie) możesz kliknąc przycisk <span className="yellow">Detale</span> będacy zawsze w najbardziej skrajnej po prawej kolumnie tabeli.
                    Kliknięcie tego przycisku otworzy stronę agregującą wszystkie detale konkretnej postaci.
                </div>
                <div className="manual-paragraf">
                    <div className="manual-paragraf-title">Katalog historii</div>
                    Wyszukiwanie historii działa na bardzo podobnej zasadzie co wyszukiwanie postaci. Należy wybrać z menu opcję <span className="yellow">Katalogi Historii</span>. Po wyświetlonej tabeli możemy <span className="yellow">filtrować i sortować</span>, a także zmieniać ilość historii w liście czy <span className="yellow">wyswietlać detale</span>, aby przeczytać całą historie.
                    Z perspektywy detali historii możemy <span className="yellow">przemieszczać się między kolejnymi historiami</span> w tabelce za pomoca przycisków <span className="yellow"> {elementPrev} Poprzednia i Następna {elementNext}</span> umieszczonych na górze otwartego okna.
                </div>
            </div>
        </div>
        )
    }

}
export default CharacterHistoryListManual;