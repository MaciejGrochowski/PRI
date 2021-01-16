import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import {TextField} from "@material-ui/core";
import {textsPolish} from "../commons/texts-pl";
import {validationMail} from "./RegisterPage/validation";
import loginService from "../services/loginService";


class ForgotPasswordPage extends React.Component {

    constructor(){
        super();
        this.state = {
            mail: "",
            errorForgetPassword: false,
            successForgetPassword: false
        }
    }

    forgotPassword = () => {
        const requestBody = {email: this.state.mail};

        loginService.forgetPassword(requestBody)
            .then(r => this.forgotPasswordSuccessHandler(r))
            .catch(e => this.forgotPasswordErrorHandler(e))
    }

    forgotPasswordErrorHandler = e => {
        this.setState({errorForgetPassword: true})
    }

    forgotPasswordSuccessHandler = r => {
        this.setState({successForgetPassword: true, errorForgetPassword: false})
    }

    validationMail = () => {
        return validationMail(this.state.mail).errorState;
    }


    render(){





        return (
            <div className = "container-login-page">
            <div className = "login-body">
            <div className = "margin-login-body">
            <div className = "login-title">Zapomniałeś hasła?</div>
            Wpisz adres e-mail na który zostało założone konto. Dostaniesz od nas e-mail z linkiem do zmiany hasła.
            <div className="block-component">
                    <TextField
                               label={textsPolish.register.mail}
                               value={this.state.mail}
                               onChange={event => this.setState({mail: event.target.value})}
                    />

</div>
<div className="block-component">
                {this.state.errorForgetPassword && <div className = "error-message">Niepoprawny mail!</div>}
                {this.state.successForgetPassword && <div className = "positive-message">Na Twojego maila wysłaliśmy linka do zresetowania hasła! <i class="fas fa-envelope"></i></div>}
                {/*ToDo style it*/}
</div>
<div className="block-component">
                <button className = "zaloguj-button" disabled={!this.state.mail || this.validationMail() || this.state.successForgetPassword} onClick={() => this.forgotPassword()}>Przypomnij hasło</button>
            </div></div></div></div>
        )
    }

}

export default ForgotPasswordPage;
