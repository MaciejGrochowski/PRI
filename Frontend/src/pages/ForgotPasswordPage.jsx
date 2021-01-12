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
            <div className = "plainPage">
                <div className="block-component">
                    <TextField
                               label={textsPolish.register.mail}
                               value={this.state.mail}
                               onChange={event => this.setState({mail: event.target.value})}
                    />
                </div>

                {this.state.errorForgetPassword && <div>Niepoprawny mail!</div>}
                {this.state.successForgetPassword && <div>Na Twojego maila wysłaliśmy linka do zresetowania hasła!</div>}
                {/*ToDo style it*/}


                <button className = "zaloguj-button" disabled={!this.state.mail || this.validationMail() || this.state.successForgetPassword} onClick={() => this.forgotPassword()}>Przypomnij hasło</button>
            </div>
        )
    }

}

export default ForgotPasswordPage;