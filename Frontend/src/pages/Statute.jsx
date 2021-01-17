import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../styles/manual.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheckSquare, faEye, faUser, faTrash} from "@fortawesome/free-solid-svg-icons";


const check = <FontAwesomeIcon icon={faCheckSquare}/>
const eye = <FontAwesomeIcon icon={faEye}/>
const profile = <FontAwesomeIcon icon={faUser}/>
const trash = <FontAwesomeIcon icon={faTrash}/>
class Statute extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Regulamin
                </div>
                <div className = "manual-paragraf-title">§1 Postanowienia ogólne</div>
                <div className= "manual-paragraf">
                1. Niniejszy regulamin określa zasady korzystania z usługi świadczonej drogą elektroniczną umożliwiającej
                 użytkowanie internetowego serwisu, tworzenie postaci, historii i sesji w serwisie www.janietakiork.projektstudencki.pl (dalej "Aplikacja").
                </div><div className= "manual-paragraf">
                2. Usługa polega na umożliwieniu publikowania (rozpowszechniania) za pośrednictwem Aplikacji autorskich pomysłów, postaci czy historii zgodnie z zasadami określonymi w Regulaminie.
                </div><div className= "manual-paragraf">
                3. Dostęp do Aplikacji, możliwość przeglądania wpisów oraz zamieszczania własnych postaci czy historii mają wszyscy użytkownicy sieci Internet odwiedzający portal (dalej "Użytkownicy"), przy czym osoby niepełnoletnie mogą korzystać z Aplikacji za wiedzą i zgodą ich przedstawicieli ustawowych.
                </div><div className= "manual-paragraf">
                4. Rejestrując się, Użytkownik akceptuje warunki  określone w Regulaminie i zobowiązuje się do przestrzegania obowiązków wynikających z Regulaminu.
                </div><div className= "manual-paragraf">
                5. Zespół Ja Nie Taki Ork nie ma obowiązku rozpowszechniać wypowiedzi, treści i danych dostarczanych przez Użytkownika. Zespół Ja Nie Taki Ork zachowuje prawo do zaprzestania rozpowszechniania wszystkich lub tylko wybranych wypowiedzi, treści i danych upublicznionych przez Użytkownika i uniemożliwienia dostępu do tych wypowiedzi, treści i danych na stronach portalu.
                </div><div className= "manual-paragraf">
                6. Zespół Ja Nie Taki Ork zastrzega sobie prawo moderowania Aplikacji w tym stworzonych postaci oraz historii oraz redagowania treści wpisów.
                </div><div className= "manual-paragraf">
                7. Użytkownik korzystający z Aplikacji nie może mieć z tego tytułu jakichkolwiek roszczeń finansowych wobec kogokolwiek.
                </div>
                <div className= "manual-paragraf">
                8. Użytkownik jest uprawniony do zaprzestania korzystania z Aplikacji w każdym czasie, co nie pozbawia Zespół Ja Nie Taki Ork prawa do korzystania z wypowiedzi, treści i danych dostarczonych i rozpowszechnionych przez Użytkownika za pośrednictwem Aplikacji.
                </div>

                <div className = "manual-paragraf-title">§2 Zasady korzystania z Aplikacji</div>

                <div className= "manual-paragraf">
                1. Użytkownicy zobowiązani są do korzystania z Aplikacji zgodnie z postanowieniami Regulaminu i obowiązującymi przepisami prawa.
                </div>
                <div className= "manual-paragraf">
                2. Użytkownicy zobowiązani są do powstrzymania się od rozpowszechniania za pośrednictwem Aplikacji wszelkich treści o charakterze bezprawnym.
                </div>
                <div className= "manual-paragraf">
                3. Zabronione jest dokonywanie za pośrednictwem Aplikacji jakichkolwiek działań naruszających powszechnie obowiązujące przepisy prawa, w szczególności niedozwolone jest:
                </div><div className= "manual-paragraf">
                &emsp; a) rozpowszechnianie treści naruszających dobra osobiste osób trzecich (w tym innych użytkowników Forum) lub zagrażających naruszeniem takich dóbr osobistych;
                </div><div className= "manual-paragraf">
                &emsp; b) podejmowanie działań spełniających znamiona przestępstwa lub zachęcających do popełniania przestępstw (w tym zniesławienia lub pomówienia);
                </div><div className= "manual-paragraf">
                &emsp; c) wzywanie do nienawiści rasowej, wyznaniowej lub etnicznej;
                </div><div className= "manual-paragraf">
                &emsp; d) propagowanie przemocy i agresji;
                </div><div className= "manual-paragraf">
                &emsp; e) propagowanie alkoholu, środków odurzających, narkotyków oraz pornografii;
                </div><div className= "manual-paragraf">
                &emsp; f) rozpowszechnianie treści reklamowych i promocyjnych;
                </div><div className= "manual-paragraf">
                &emsp; g) rozpowszechnianie materiałów naruszających (bądź zagrażających) prawom autorskim i pokrewnym osób trzecich;
                </div><div className= "manual-paragraf">
                &emsp; h) rozpowszechnianie materiałów naruszających lub zagrażających prawom własności przemysłowej (w tym prawom z rejestracji znaku towarowego lub patentu) lub innym prawom osób trzecich;
                </div><div className= "manual-paragraf">
                &emsp; i) naruszanie praw wydawcy portalu;
                </div><div className= "manual-paragraf">
                &emsp; j) umieszczanie linków do innych stron www;
                </div><div className= "manual-paragraf">
                &emsp; k) umieszczanie treści przesyłanych na zasadzie łańcuszka;
                </div><div className= "manual-paragraf">
                &emsp; l) rozpowszechnianie danych osobowych, teleadresowych, adresów mailowych itp.;
                </div><div className= "manual-paragraf">
                &emsp; m) rozpowszechnianie danych informatycznych zawierających wirusy komputerowe lub inne informatyczne rozwiązania niszczące;
                </div><div className= "manual-paragraf">
                &emsp; n) rozpowszechnianie treści naruszających zasady współżycia społecznego;
                </div><div className= "manual-paragraf">
                &emsp; o) rozpowszechnianie wulgaryzmów, treści obraźliwych, obscenicznych lub naruszających powszechnie uznawane zasady etyczne i zasady kultury osobistej;
                </div><div className= "manual-paragraf">
                &emsp; p) rozpowszechnianie spamu.
                </div>
                <div className= "manual-paragraf">
                4. Aplikacja przeznaczone jest do rozpowszechniania treści w języku polskim. Zespół Ja Nie Taki Ork apeluje o używanie polskich liter przy tworzeniu komentarzy i wpisów, a także stosowanie wielkich liter tylko w uzasadnionych przypadkach.
                </div><div className= "manual-paragraf">
                5. Użytkownicy zobligowani są do publikowania w Aplikacji wyłącznie treści zgodnych z tematyką serwisu.
                </div><div className= "manual-paragraf">
                6. Publikowanie w Aplikacji informacji handlowych w rozumieniu ustawy o świadczeniu usług drogą elektroniczną (w tym reklam, materiałów promocyjnych, komunikatów PR, itp.) oraz innych treści o charakterze komercyjnym, bez uprzedniej pisemnej (pod rygorem nieważności) zgody zespołu Ja Nie Taki Ork jest zabronione.
                </div><div className= "manual-paragraf">
                7. Te same treści (wypowiedzi) nie mogą być publikowane więcej niż raz i nie mogą być powtarzane w różnych miejscach Aplikacji. Zespół Ja Nie Taki Ork zachowuje prawo usuwania duplikatów i powtórzeń w dowolnym momencie.
                </div><div className= "manual-paragraf">
                8. Aplikacja przeznaczona jest do otwartej wymiany informacji, dostępnej publicznie dla wszystkich Użytkowników.
                </div>

                <div className = "manual-paragraf-title">§3 Prawa autorskie</div>
                <div className= "manual-paragraf">
                1. Użytkownicy zobligowani są do rozpowszechniania za pośrednictwem Aplikacji wyłącznie treści własnego autorstwa. W przypadku posłużenia się utworami lub opublikowanymi wypowiedziami innych osób, Użytkownicy zobligowani są do jednoznacznego wskazania cytatu zgodnie z obowiązującymi przepisami prawa. Ilekroć mowa jest o "Utworze", rozumie się przez to każdą wypowiedź Użytkownika jego autorstwa zamieszczoną przez niego w Aplikacji, którą można uznać za utwór w rozumieniu ustawy z dnia 4 lutego 1994 roku o prawie autorskim i prawach pokrewnych.
                </div><div className= "manual-paragraf">
                2. W zamian za świadczenie usługi umożliwienia korzystania z Aplikacji Użytkownik upoważnia zespół Ja Nie Taki Ork do korzystania z Utworu bez ograniczeń czasowych, terytorialnych i ilościowych, na wszelkich znanych polach eksploatacji.
                </div>

                <div className = "manual-paragraf-title">§4 Postanowienia końcowe</div>
<div className= "manual-paragraf">
1. Zespół Ja Nie Taki Ork nie odpowiada za treści publikowane w aplikacji.
</div><div className= "manual-paragraf">
2. Każdy z Użytkowników ponosi wyłączną i osobistą odpowiedzialność wobec zespołu Ja Nie Taki Ork i osób trzecich za zamieszczenie w Aplikacji treści naruszających przepisy prawa lub zasady wynikające z Regulaminu.
</div><div className= "manual-paragraf">
3. Zgodnie z art. 15 ustawy z dnia 18 lipca 2002 r. o świadczeniu usług drogą elektroniczną zespół Ja Nie Taki Ork nie jest zobowiązana do sprawdzania danych przekazywanych, przechowywanych lub udostępnianych za pośrednictwem Aplikacji. Zespół Ja Nie Taki Ork zachowuje jednak prawo do kontrolowania treści i danych dostarczanych przez Użytkowników w celu rozpowszechniania za pośrednictwem Aplikacji, ich redagowania i moderacji, a także odmowy ich rozpowszechnia lub zaprzestania dalszego rozpowszechniania.
</div><div className= "manual-paragraf">
4. Zespół Ja Nie Taki Ork zastrzega sobie prawo do ujawnienia tożsamości Użytkownika lub wszelkich informacji podanych przez Użytkownika na żądanie uprawnionego organu, jeżeli w stosunku do Użytkownika zostanie wszczęte postępowanie karne lub cywilne mające związek z aktywnością Użytkownika w Aplikacji.
</div><div className= "manual-paragraf">
5. Zespół Ja Nie Taki Ork dołoży wszelkich starań w celu zapewnienia prawidłowego działania Apikacji oraz udzielania pomocy Użytkownikom w rozwiązywaniu problemów dotyczących jego funkcjonowania.
</div><div className= "manual-paragraf">
6. Wszelkie problemy związane z funkcjonowaniem Aplikacji oraz przypadki naruszenia Regulaminu należy zgłaszać na adres: janietakiork@gmail.pl lub na serwerze Discord.
</div><div className= "manual-paragraf">
7. Zespół Ja Nie Taki Ork może zawiesić działalność Aplikacji bez podania przyczyny. Powyższe nie będzie powodowało powstania jakichkolwiek roszczeń Użytkowników wobec Zespołu Ja Nie Taki Ork.
</div><div className= "manual-paragraf">
8. Korzystanie z Aplikacji jest równoznaczne z akceptacją przez danego Użytkownika treści niniejszego Regulaminu wraz ze zobowiązaniem się tego Użytkownika do przestrzegania Regulaminu w całości.
</div><div className= "manual-paragraf">
9. Zespół Ja Nie Taki Ork zastrzega sobie prawo do wprowadzenia zmian w treści Regulaminu. Wszelkie zmiany wchodzą w życie w momencie ich opublikowania.
</div>
            </div>
        </div>
        )
    }

}
export default Statute;