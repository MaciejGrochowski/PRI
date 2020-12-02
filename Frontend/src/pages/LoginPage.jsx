import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import PasswordField from 'material-ui-password-field'
import loginService from "../services/loginService";
import Cookie from "js-cookie";



class LoginPage extends React.Component {

    constructor(){
        super();
        this.state = {
            username: "",
            password: ""
        }
    }

    saveTokenInCookies = response => {

        var jwt = require("jsonwebtoken");
        console.log(response.data);

        console.log(jwt.decode(response.data.token));

        Cookie.set("token", response.data);
    }

    login = () => {

        loginService.login(this.state.username, this.state.password)
            .then(response => this.saveTokenInCookies(response))
            .catch(error => console.log(error))
    }

    render(){
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

                <button onClick={() => this.login()}>Loguj</button>
            </div>
        )
    }

}

export default LoginPage;