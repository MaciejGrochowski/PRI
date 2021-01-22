import React from 'react';
import './App.css';
import CharactersListPage from "./pages/CharacterListPage/CharactersListPage";
import {Route} from 'react-router'
import Menu from "./components/Menu/Menu";
import {BrowserRouter, Link} from "react-router-dom";
import {fronendUrls} from "./commons/urls";
import HistoriesListPage from "./pages/HistoryListPage/HistoriesListPage";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import CharacterDetailsPage from "./pages/CharacterDetailsPage/CharacterDetailsPage";
import CharacterGeneratorPage from "./pages/CharacterGeneratorPage/CharacterGeneratorPage";
import HistoryCreatorPage from "./pages/HistoryCreatorPage/HistoryCreatorPage";
import UserProfilePage from "./pages/UserProfilePage/UserProfilePage";
import RegisterPage from "./pages/RegisterPage/RegisterPage";
import ForgotPasswordPage from "./pages/ForgotPasswordPage";
import SessionListPage from "./pages/SessionListPage/SessionListPage";
import SessionDetailsPage from "./pages/SessionDetailsPage/SessionDetailsPage";
import ActivateUserPage from "./pages/ActivateUserPage";
import NewPasswordPage from "./pages/NewPasswordPage";
import CookiesPage from "./pages/CookiesPage";
import CookieConsent from "react-cookie-consent";
import CreateCharacterManual from "./pages/Manuals/CreateCharacterManual";
import CreateHistoryManual from "./pages/Manuals/CreateHistoryManual";
import SessionManual from "./pages/Manuals/SessionManual";
import Statute from "./pages/Statute";
import CharacterHistoryListManual from "./pages/Manuals/CharacterHistoryListManual";

class App extends React.Component{

    render(){
    return (
            <BrowserRouter history={this.props.history}>
                <Menu/>
        <Route path={fronendUrls.mainPage} exact={true} component={MainPage}/>
                <Route path={fronendUrls.characterList + "(/user)?/:username?"} component={CharactersListPage} />
        <Route path={fronendUrls.historyList} component={HistoriesListPage} />
         {/*+"/:typeOfData?/:data?/(historyId)?/:historyId?"*/}
        <Route path={fronendUrls.characterDetails} component={CharacterDetailsPage} />
        <Route path={fronendUrls.characterGenerator} component={CharacterGeneratorPage} />
        <Route path={fronendUrls.historyCreator} component={HistoryCreatorPage} />
        <Route path={fronendUrls.loginPage} component={LoginPage} />
        <Route path={fronendUrls.userProfilePage + "/:username"} component={UserProfilePage} />
        <Route path={fronendUrls.registerPage} component={RegisterPage} />
        <Route path={fronendUrls.sessionList + "/:username"} component={SessionListPage} />
        <Route path={fronendUrls.sessionDetails + "/:hashcode"} component={SessionDetailsPage} />
        <Route path={fronendUrls.forgotPasswordPage} component={ForgotPasswordPage} />
        <Route path={fronendUrls.newPasswordPage + "/:username?/:hashcode?"} component={NewPasswordPage} />
        <Route path={fronendUrls.activateUser + "/:username/:uuid"} component={ActivateUserPage} />
        <Route path={fronendUrls.cookiesPage} component={CookiesPage} />
        <Route path={fronendUrls.createCharacterManual} component={CreateCharacterManual} />
        <Route path={fronendUrls.createHistoryManual} component={CreateHistoryManual} />
        <Route path={fronendUrls.sessionManual} component={SessionManual} />
        <Route path={fronendUrls.statute} component={Statute} />
        <Route path={fronendUrls.characterHistoryListManual} component={CharacterHistoryListManual} />
                <CookieConsent buttonText="Rozumiem">Strona używa plików cookies. Ich polityka jest dostępna <Link to={fronendUrls.cookiesPage}>TU</Link></CookieConsent>
            </BrowserRouter>
        )
  }
}
export default App;
