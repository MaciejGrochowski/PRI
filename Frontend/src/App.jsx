import React from 'react';
import './App.css';
import CharactersListPage from "./pages/CharacterListPage/CharactersListPage";
import {Route} from 'react-router'
import Menu from "./components/Menu/Menu";
import {BrowserRouter} from "react-router-dom";
import {fronendUrls} from "./commons/urls";
import HistoriesListPage from "./pages/HistoryListPage/HistoriesListPage";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import CharacterDetailsPage from "./pages/CharacterDetailsPage/CharacterDetailsPage";
import CharacterGeneratorPage from "./pages/CharacterGeneratorPage/CharacterGeneratorPage";
import HistoryCreatorPage from "./pages/HistoryCreatorPage/HistoryCreatorPage";

class App extends React.Component{

    render(){
    return (
            <BrowserRouter history={this.props.history}>
                <Menu/>
        <Route path={fronendUrls.mainPage} exact={true} component={MainPage}/>
        <Route path={fronendUrls.characterList} component={CharactersListPage} />
        <Route path={fronendUrls.historyList} component={HistoriesListPage} />
        <Route path={fronendUrls.characterDetails} component={CharacterDetailsPage} />
        <Route path={fronendUrls.characterGenerator} component={CharacterGeneratorPage} />
        <Route path={fronendUrls.historyCreator} component={HistoryCreatorPage} />
        <Route path={fronendUrls.loginPage} component={LoginPage} />


            </BrowserRouter>
        )
  }
}
export default App;
