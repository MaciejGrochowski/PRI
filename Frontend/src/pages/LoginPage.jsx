import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import loginService from "../services/loginService";
import Cookie from "js-cookie";
import {loginStatusChange} from "../actions";
import { connect } from "react-redux";
import {fronendUrls} from "../commons/urls";
import "../styles/login-page.css";
import {Redirect} from 'react-router'
import PasswordField from "../components/PasswordField/PasswordField";
import {polishCodeErrors} from "../commons/texts-pl";



class LoginPage extends React.Component {

    constructor(){
        super();
        this.state = {
            username: "",
            password: "",
            badCredentialsError: false,
            notResponsingError: false,
            userNotActiveError: false
        }
    }

    saveTokenInCookies = async response => {

        await Cookie.set("token", response.data, {expires: 5, secure: true, sameSite: 'strict'});
        this.props.loginStatusChange(true);


    }

    login = () => {

        loginService.login(this.state.username, this.state.password)
            .then(response => this.saveTokenInCookies(response))
            .catch(error => this.loginErrorHandler(error))
    }

    loginErrorHandler = error => {
        if(error.response === undefined){
            this.setState({notResponsingError: true})
            return
        }
        if(error.response.status === 401){
            if(error.response.data.token === "USER_NOT_ACTIVE"){
                this.setState({userNotActiveError: true})
            }
            else{
                this.setState({badCredentialsError: true}) //ToDo refactor
            }
        }
    }

    handleChangePassword = event => {
        this.setState({password: event.target.value, badCredentialsError: false, notResponsingError: false, userNotActiveError: false})
    }

    enterListener = event => {
        if (event.keyCode === 13) {
            this.login();
        }
    }

    render(){
        if (this.props.isLogged) {
            return <Redirect push to={fronendUrls.mainPage} />
        }
        return (
<div className = "container-login-page">
<div className = "login-body">
<div className = "margin-login-body">
<div className = "login-title">Zaloguj się</div>
    {this.state.badCredentialsError && <div className = "error-message">{polishCodeErrors.BAD_CREDENTIALS_ERROR}</div>}
    {this.state.notResponsingError && <div className = "error-message">{polishCodeErrors.NOT_RESPONSING_ERROR}</div>}
    {this.state.userNotActiveError && <div className = "error-message">{polishCodeErrors.USER_NOT_ACTIVE_ERROR}</div>}
                <div className="block-component">
                <TextField label="Login" value={this.state.username} onChange={event => this.setState({username:event.target.value, badCredentialsError: false, notResponsingError: false, userNotActiveError: false})} />
                </div>
                <div className="block-component">
                    <PasswordField
                        handleChangePassword={this.handleChangePassword}
                        label={"Hasło"}
                        onKeyDown={(e) => this.enterListener(e)}
                    />
                </div>
                <div className="block-component"><button className = "zaloguj-button" onClick={() => this.login()} >Zaloguj</button></div>
    <div className="login-description">Nie masz jeszcze konta? <Link to={fronendUrls.registerPage}>Zarejestruj się!</Link></div>
    <div className="login-description"><Link to={fronendUrls.forgotPasswordPage}>Zapomniałeś hasła?</Link></div>

</div>
                </div>
                </div>
        )
    }

}


const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)


export default LoginPage = connect(mapStateToProps, mapDispatchToProps)(LoginPage);