import React from 'react';
import './App.css';
import CharactersListPage from "./pages/CharacterListPage/CharactersListPage";
import {Route} from 'react-router'
import Menu from "./components/Menu/Menu";
import {BrowserRouter} from "react-router-dom";
import {fronendUrls} from "./commons/urls";
import HistoriesListPage from "./pages/HistoriesListPage";
import MainPage from "./pages/MainPage";
import CharacterDetailsPage from "./pages/CharacterDetailsPage/CharacterDetailsPage";

class App extends React.Component{

    render(){
    return (
            <BrowserRouter history={this.props.history}>
                <Menu/>
        <Route path={fronendUrls.mainPage} exact={true} component={MainPage}/>
        <Route path={fronendUrls.characterList} component={CharactersListPage} />
        <Route path={fronendUrls.historyList} component={HistoriesListPage} />
        <Route path={fronendUrls.characterDetails} component={CharacterDetailsPage} />
            </BrowserRouter>
        )
  }
}
export default App;
