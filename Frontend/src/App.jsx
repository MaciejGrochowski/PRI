import React from 'react';
import './App.css';
import CharactersListPage from "./pages/CharacterListPage/CharactersListPage";
import {Route} from 'react-router'
import Menu from "./components/Menu/Menu";
import {BrowserRouter} from "react-router-dom";
import {fronendUrls} from "./commons/urls";
import HistoriesListPage from "./pages/HistoriesListPage";

class App extends React.Component{

    render(){
    return (
            <BrowserRouter>
                <Menu/>
        <Route path={fronendUrls.characterList} component={CharactersListPage} />
        <Route path={fronendUrls.historyList} component={HistoriesListPage} />
            </BrowserRouter>
        )

  }
}
export default App;
