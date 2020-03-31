### IDE
React i pliki .jsx działają dobrze w IntelliJ Idea, jest to więc zalecane przeze mnie rozwiązanie z uwagi na fakt, że uczestnicy projektu są do tego IDE przyzwyczajeni. Nie jest wymagana instalacja żadnych pluginów ani wtyczek.
Inne polecane IDE to np. [Rekit Studio](https://www.freecodecamp.org/news/introducing-rekit-studio-a-real-ide-for-react-and-redux-development-baf0c99cb542/), [Nuclide](https://nuclide.io/)

### Instrukcja uruchomienia projektu:

1. Do działania projektu niezbędne są aplikacje [node.js](https://nodejs.org/en/download/) oraz [yarn](https://classic.yarnpkg.com/en/docs/install/#windows-stable), instalowane koniecznie w tej kolejności.
2. Po zainstalowaniu, można przetestować działanie powyższych aplikacji przez komendy **npm -v i yarn -v**.
3. Jeżeli obie aplikacje działają (może być konieczny restart konsoli lub aplikacji w której używa się konsoli (np. IntelliJ), wykonaj komendę **yarn install** (będąc w folderze z plikami projektu). Komenda instaluje potrzebne dependencje, biblioteki i zależności. Szczegóły instalowanych pakietów dostępne są w pliku package.json.
4. Uruchomienie tak stworzonej aplikacji odbywa się przez komendę **yarn start**.

### Dodatkowe Informacje

* Po uruchomieniu aplikacji, jest ona domyślnie dostępna pod adresem [http://localhost:3000](http://localhost:3000).

* Komenda **yarn build** umożliwia zbudowanie wersji produkcyjnej aplikacji.

* Wyłączenie aplikacji najprościej wykonać jako CTRL+C w okienku konsoli. Można też ją zabić z perspektywy menadżera procesów systemu.

* Po uruchomieniu urządzenie automatycznie wprowadza wszelkie **zapisane** zmiany w kodzie. Co za tym idzie, nie ma konieczności wyłączania i włączania yarn'a.
Przeglądarka internetowa powinna samoistnie się odświeżyć po zapisaniu zmian w dowolnym pliku .jsx.
W przypadku wystąpienia błędu kompilacji, treść jego pojawi się w przeglądarce. Pomocna może być konsola deweloperska używanej przeglądarki - niekiedy opisy błędów tam są dokładniejsze.

* Po naprawieniu błędu kompilacji może być konieczne ręczne odświeżenie strony w przeglądarce.

### Więcej informacji:

Na temat domyślnej konfiguracji więcej można przeczytać na [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

Szersze informacje na temat samego frameworku można uzyskać na stronie [React documentation](https://reactjs.org/).
