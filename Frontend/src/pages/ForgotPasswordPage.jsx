import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";
import {fronendUrls} from "../commons/urls";
import {TextField} from "@material-ui/core";
import {textsPolish} from "../commons/texts-pl";
import {validationMail} from "./RegisterPage/validation";
import loginService from "../services/loginService";
import {loginStatusChange} from "../actions";
import {connect} from "react-redux";
import {Redirect} from "react-router";
import Loader from 'react-loader-spinner'



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
        this.setState({disableButton: true})
        const requestBody = {email: this.state.mail};

        loginService.forgetPassword(requestBody)
            .then(r => this.forgotPasswordSuccessHandler(r))
            .catch(e => this.forgotPasswordErrorHandler(e))
    }

    forgotPasswordErrorHandler = e => {
        this.setState({errorForgetPassword: true, disableButton: false})
    }

    forgotPasswordSuccessHandler = r => {
        this.setState({successForgetPassword: true, errorForgetPassword: false})
    }

    validationMail = () => {
        return validationMail(this.state.mail).errorState;
    }

    enterListener = event => {
        if (event.keyCode === 13 && !(!this.state.mail || this.validationMail() || this.state.successForgetPassword)) {
            this.forgotPassword();
        }
    }


    render(){

        if(this.props.isLogged){
            return <Redirect push to={fronendUrls.mainPage} />
        }

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
                               onKeyDown={(e) => this.enterListener(e)}
                    />

</div>
<div className="block-component">
                {this.state.errorForgetPassword && <div className = "error-message">Niepoprawny mail!</div>}
                {this.state.successForgetPassword && <div className = "positive-message">Na Twojego maila wysłaliśmy linka do zresetowania hasła! <i class="fas fa-envelope"></i></div>}
                {this.state.disableButton && (!this.state.errorForgetPassword && !this.state.successForgetPassword) &&
                <div className="auto-margin">
                <Loader
                    type="ThreeDots"
                    color="#FFD859"
                    height={50}
                    width={50}
                /></div>

                }
                {/*ToDo style it*/}
</div>
<div className="block-component">
                <button className = "zaloguj-button" disabled={!this.state.mail || this.validationMail() || this.state.successForgetPassword || this.state.disableButton} onClick={() => this.forgotPassword()}>Przypomnij hasło</button>
            </div></div></div></div>
        )
    }

}
const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)


export default ForgotPasswordPage = connect(mapStateToProps, mapDispatchToProps)(ForgotPasswordPage);
