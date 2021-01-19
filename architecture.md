# Architektura Ja Nie Taki Ork
Ten dokument opisuje ogólną konfigurację i architekturę, na której działa witryna https://janietakiork.projektstudencki.pl. Ideą dokumentu jest opisanie składowych potrzebnych do obsługi systemu i obecnych na repozytorium. Plik ten powinien pomóc nowym osobom rozeznać się w projekcie a także zachować ciągłość obsługi aplikacji w przypadku gwałtownej zmiany zespołu programistycznego. 
## Zawartość
Repozytorium składa się z dwóch folderów Backend i Frontend, które należy uruchamiać osobno. 
* Backend - Folder zawiera aplikację serwerową napisaną w języku *Java* z użyciem frameworków *Spring* oraz *Hibernate*. Całość została zoptymalizowana narzędziem do automatycznej budowy - *maven*.   
* Frontend - Folder zawiera aplikacje kliencką napisaną w języku *JavaScript* w oparciu o bibliotekę *React*. Do budowania aplikacji używamy *yarna*. 
Warto zwrócić uwagę, że oba foldery należy kompilować osobno. 
## Backend
Aby poprawnie uruchomić część backendową wymagana jest *Java w wersji 11* i narzędzie - *Maven*. Aby zbudować aplikację serwerową należy wykonać kilka kroków. 
1. Stworzyć pusta MySQL'ową baze danych o nazwie "orc"   (Dokładną konfiguracje można znaleźć w pliku <code>Backend/src/main/resources/application.properties</code>) kodowaną zgodnie ze standardem UTF-8.
2. Uzupełnić zmienne środowiskowe:
 * DB_PASS=Hasło do bazy danych
* SECRET_KEY=Zmienna potrzebna do generowania tokenów autoryzacyjnych przez Spring Seciurity
* SERVER_IMAGE_PATH=ścieżka do folderu zawierającego pliki graficzne. 
* MAIL_PASSWORD=Zmienna zawierająca hasło do adresu e-mail używanego przez aplikację
* URL_APPLICATION=Zmienna zawierająca adres pod jakim znajduje się aplikacja. 
  </br>Trzy ostatnie zmienne służą do prawidłowego wysyłania wiadomości e-mail do użytkowników aplikacji i nie musza być poprawnie uzupełnione do użytkowania aplikacji (z wyłączeniem omawianej funkcjonalności).
3. Wykonać komendę <code>mvn clean install</clean>
4. Uruchomić aplikację.
## Frontend
Aby uruchomić część frontendową wymagana jest instalacja narzędzia *yarn*. A następnie wykonanie kilku kroków. W terminalu należy wpisać poniższe komendy:
1. <code>yarn install</code>
2. <code>yarn start</code>
Dokładniejsza instrukcja instalacji dostępna jest we <code>Frontend/README.md</code>
## Serwer
Aplikacja obecnie hostowana jest na serwerze wydziałowym Wydziału Matematyki i Informatyki UAM. Dostęp do tej instancji aplikacji jest niemożliwy dla użytkowników zewnętrznych. Istnieje jednak opcja postawienia nowej instacji aplikacji na podobnym serwerze. 
Na serwerze powinny znajdować się:
* Ubuntu 18.04
* nginx 1.14.0 (Ubuntu)
*  apache-tomcat-9.0.34 

Aplikacja posiada skrypt budujący, który znajduje się w <code>Backend/scripts/gitCheck.sh</code>. Skrypt ten pobiera wersję aplikacji na branchu Base i dokonuje deployu aplikacji. Po odpowiednim przygotowaniu środowiska (instalacja Ubuntu, nginx, tomcat, MySQL, Java, maven, yarn), zainstalowaniu narzedzia git i ściągnięciu plików projektu pozwoli to przeprowadzić deplopy najnowszej wersji aplikacji. 

Opisany powyżej skrypt obecnie działa na serwerze i z pomocą crona zapewnia co godzinny deploy nowej wersji aplikacji (jeżeli tylko został zmieniony odpowiedni branch).
## Certyfikat
Certyfikaty do https  przy użyciu softwaru Certbot, który zapewnia nam certyfikat od Let's encrypt. Więcej informacji na stronie - [https://letsencrypt.org/about/](https://letsencrypt.org/about/)
## Uwagi inne
Do obsługi aplikacji wymagane jest otwarcie portów:
- port:80 i port:443- port defaultowy nginx
- port:8443 - port tomcata dla https
- port:8080 - port serwera
- port:587 - port używany przy komunikacji mailowej. Wiadomości e-mail wysyłane są bezpośrednio z Javy. Nie używamy żadnego zewnętrznego serwera SMTP.
