import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import loginService from "../services/loginService";


class ActivateUserPage extends React.Component {

    constructor() {
        super();
        this.state = {
            error:false
        }
    }


    componentDidMount() {
        loginService.activateAccount(this.props.match.params.username, this.props.match.params.uuid)
            .then(r => this.setState({error: false}))
            .catch(e => this.setState({error: true}))
    }

    render(){

        if(!this.props.match.params.username || !this.props.match.params.uuid || this.state.error){
            return (
                <div className="plainPage">
                    Coś poszło nie tak! Jeśli próbujesz aktywować konto, skontaktuj się z administracją
                    Wróć do <Link to={fronendUrls.mainPage}>Strony Głównej</Link>
                </div>
            )
        }

        return (
            <div className = "plainPage">
                <h1>Aktywowałeś konto</h1>
                <p>Konto użytkownika {this.props.match.params.username} zostało aktywowane. Możesz się <Link to={fronendUrls.loginPage}>zalogować</Link></p>
            </div>
        )
    }

}

export default ActivateUserPage;