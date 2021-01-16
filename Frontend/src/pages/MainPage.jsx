import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import CookieConsent from "react-cookie-consent";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope, faCheckSquare} from "@fortawesome/free-solid-svg-icons";
import {faFacebook, faDiscord} from "@fortawesome/free-brands-svg-icons";
import "../styles/homepage.css"


const check = <FontAwesomeIcon icon={faCheckSquare}/>
const fb = <FontAwesomeIcon icon={faFacebook}/>
const discord = <FontAwesomeIcon icon={faDiscord}/>
const mail = <FontAwesomeIcon icon={faEnvelope}/>


class MainPage extends React.Component {

    render(){
        return (

            <div className = "container-homepage">
                <div className = "logo">
                    <img src={process.env.PUBLIC_URL + '/logo.png'}/>
                </div>
                <div className="subsection-container black-section">
                    <div className="section-title">O co chodzi?</div>
                    <div className="welcome-text">Aplikacja "Ja Nie Taki Ork " jest interaktywną pomocą dla graczy, jak i mistrzów gry, rozgrywających sesje w systemie Warhammer Fantasy Roleplay. Oferuje rozbudowany generator postaci dostosowany do realiów świata Warhammera i wolny od wielu bolączek losowych generatorów dostępnych na innych stronach. System pozwala przeglądać bogatą listę już stworzonych postaci i dodawać historie symulując żyjący i rozbudowany świat. Możliwość grupowania postaci w sesje  sprawia, że zapanowanie nad bałaganem informacyjnym powstającym przy toczących się online przygodach staje się możliwe.</div>
                </div>
                <div className="subsection-container dark-grey-section">
                    <div className="section-title">Super! Ale jak tego używać?</div>
                    <div className="welcome-text">Zdajemy sobie sprawę, że być może bedziesz potrzebować kilku wskazówek. Jeżeli chcesz efektywnie korzystać z aplikacji możesz sam się tego nauczyć lub poświęcić chwilę i przejrzec instrukcje. Pamiętaj, że zawsze możesz tu wrócić.
                        <div> Instrukcja - <Link to={fronendUrls.createCharacterManual}>tworzenie postaci</Link> </div>
                        <div> Instrukcja - <Link to={fronendUrls.createHistoryManual}>tworzenie historii</Link></div>
                        <div> Instrukcja - <Link to={fronendUrls.characterHistoryListManual}>katalogi stworzonych postaci i historii</Link></div>
                        <div> Instrukcja - <Link to={fronendUrls.sessionManual}>sesje</Link></div>
                    </div>
                </div>


                <div className="subsection-container warm-grey-section">
                    <div className="section-title">Uważasz, że to świetny pomysł i chciałbyś jakoś pomóc?</div>
                    <div className="welcome-text">
                        <div> Wspaniale! Jesteśmy Tobą zainteresowani! Aplikacja JaNieTakiOrk wciąż potrzebuje rozwoju i sporej ilości pracy. Jak możesz pomóc? </div>
                        <div className="margin-bottom"><div className="bold"> {check} <Link to={fronendUrls.registerPage}>Załóż konto!</Link></div>, twórz postacie i historie z rozegranych przez siebie sesji. Dodaj temu miejscu życia!</div>

                        <div className="margin-bottom"> <div className="bold"> {check} Dołącz do naszej społeczności na <a href="https://discord.gg/mkRcFkeU">discordzie</a>.</div> Stwórz razem z nami miłą grupę rpgowców, w której będzie można porozmawiać, umówić się na sesję i poznać innych użytkowników aplikacji.</div>

                        <div className="margin-bottom"><div className="bold"> {check} Pomóż nam w moderacji pojawiających się treści</div>, informując nas o niepożądanych treściach, takich jak złośliwie stworzone postacie lub historie, albo błędy (nawet literówki) w stworzonych materiałach. Poinformować nas o takich błędach możesz przez <a href="https://discord.gg/mkRcFkeU">discorda</a> lub <a href="mailto:janietakiork@gmail.com">maila</a>.</div>

                        <div className="margin-bottom"> <div className="bold"> {check} Zostań naszym testerem </div> Jeśli używasz aplikacji i zauważysz jakiś błąd w jej działaniu (niedziałający guzik, nieładująca się strona, etc), zgłoś nam to przez <a href="https://discord.gg/mkRcFkeU">discorda</a> lub <a href="mailto:janietakiork@gmail.com">maila</a>.</div>

                        <div className="margin-bottom"> <div className="bold">{check} Zostań programistą</div> Jesteśmy otwarci na pomoc zarówno od początkujących jak i zaawansowanych adeptów programowania. Aplikacja JaNieTakiOrk powstała w Javascript(Z użyciem frameworku React) oraz Java(z użyciem frameworku Spring). Jeśli języki te nie są Ci obce, a także chcesz pomóc w rozwoju opensourcowej aplikacji, skontaktuj się z nami lub samodzielnie dokonaj zmian i wyślij nam pull requesta na repozytorium: <a href="https://github.com/MaciejGrochowski/PRI">Github</a> (Opcja dla bardziej zaawansowanych, którzy wiedzą co robią)</div>

                        <div className="margin-bottom"> <div className="bold">{check} Pomóż nam ulepszyć generator</div> Nasz generator korzysta z ogromnej ilości różnych danych i zauważonych przez nas zależności w świecie Warhammera. Część informacji jest nam danych przez samą grę, jednak wiele innych nie zostało sprecyzowanych w systemie, a zatem jest przez nas robionych zgodnie z naszą wiedzą i wyobrażeniem o przedstawionym świecie. Jeśli jesteś doświadczonym graczem (lub MG), niestraszne Ci rozważania na temat prawdopodobieństwa w świecie gry i doskonale wiesz jak powinna rosnąć szansa na posiadanie brody w przypadku wygenerowania mężczyzny krasnoluda, to zapraszamy!</div>

                        </div>
                    </div>
                <div className="subsection-container yellow-section">
                    <div className="section-title">Pytania? Problemy? Skontaktuj się z nami!</div>
                    <div className="welcome-text">
                        <div className="bold">
                        <div className="margin-bottom">W dowolnych przypadkach można się z nami kontaktować przez serwer discord lub email.</div>
                        </div>
                        <div className="margin-bottom">{mail} <a href="mailto:janietakiork@gmail.com">janietakiork@gmail.com</a></div>
                        <div className="margin-bottom">{discord} <a href="https://discord.gg/mkRcFkeU">Discord</a></div>

                    </div>
                </div>
            </div>
        )
    }

}

export default MainPage;