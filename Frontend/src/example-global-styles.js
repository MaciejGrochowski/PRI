import React from "react";
import styled, { css } from 'styled-components';

//To Styl używający biblioteki styled-components
//export - odpowiada za eksportowanie obiektu poza ten plik i możliwość jego importowania
//const - zapewnia, że styl nie ulegnie zmianie podczas działania aplikacji
//cat - nazwa stylu. Użycie stylu później wymaga jedynie htmla w postaci <Cat></Cat>
//styled. - wymagane przez bibliotekę
//div - tutaj może być dowolny znacznik HTML (div, span button, table, itd) - nasza klasa zachowywać się będzie tak jak ten znacznik
//Przy stylowaniu koniecznie trzymać się podanej niżej stylistyki:
// cały styl zamykać w ``
// po każdym elemencie średnik
// Wyjaśnienie linijki 20: do tak stworzonego komponentu można potem dodawać propsy. Propsy~Argumenty fkcji
// Znaki ${(...)} oznacza, że wewnątrz stylu dochodzi to działań JS'owych. Można tu np. wrzucić console.loga
// zapis props => {} należy rozumieć jako funkcję strzałkową, do której przekazujemy propsy(atrybuty)
// props jest obiektem posiadającym atrybuty równe takim, które wpisano przy użyciu
// przykład użycia propsa: <Cat color='green'>Chomik</Cat>
// https://styled-components.com/docs/basics dokumentacja
export const Cat = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  background: inherit;
  color: ${props => props.color ? props.color : 'red'};
  border: none;
  transition: 200ms ease;
  cursor: pointer;
`;

export const Dog = styled.button`
    width: 300px;
    color: red;
`


// W niektórych, rzadkich przypadkach styl musimy przekazać jako obiekt.
// Poniżej przykład takiego stylu
// Korzystanie: <div style={randomStyle]>chomik</div>
// Ważne - wszystkie CSSowe myślniki działają tylko z camelCase (np. background-color -> backgroundColor)
export const randomStyle = {
    backgroundColor: 'blue',
    color: 'red',
    marginTop: '2px',
    justifyContent: 'center'
}
