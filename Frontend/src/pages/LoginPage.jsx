import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import PasswordField from 'material-ui-password-field'
import loginService from "../services/loginService";
import Cookie from "js-cookie";
import {authorizationRequest, getToken} from "../services/util";
import {loginStatusChange} from "../actions";
import { connect } from "react-redux";
import {fronendUrls} from "../commons/urls";
import {Redirect} from 'react-router'



class LoginPage extends React.Component {

    constructor(){
        super();
        this.state = {
            username: "",
            password: ""
        }
    }

    saveTokenInCookies = async response => {

        await Cookie.set("token", response.data, {expires: 5, secure: true, sameSite: 'strict'});
        this.props.loginStatusChange(true);


    }

    login = () => {

        loginService.login(this.state.username, this.state.password)
            .then(response => this.saveTokenInCookies(response))
            .catch(error => console.log(error))
    }

    render(){
        if (this.props.isLogged) {
            return <Redirect push to={fronendUrls.mainPage} />
        }
        return (
            <div className = "plainPage">

                <TextField label="Login" value={this.state.username} onChange={event => this.setState({username:event.target.value})} />

                <PasswordField
                    // hintText="At least 8 characters"
                    // floatingLabelText="Enter your password"
                    // errorText="Your password is too short"
                    label="Hasło"
                    value={this.state.password}
                    onChange={event => this.setState({password:event.target.value})}
                />

                <Link to={fronendUrls.mainPage} onClick={() => this.login()}>Zaloguj</Link>
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