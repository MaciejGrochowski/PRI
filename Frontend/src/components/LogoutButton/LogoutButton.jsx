import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {loginStatusChange} from "../../actions";
import { connect } from "react-redux";
import {getInfoFromToken, getToken, isValidToken, logoutCookie} from "../../services/util";
import {ItemMenu} from "../Menu/ExampleMenu.style";
import loginService from "../../services/loginService";
import {Redirect} from 'react-router'



class LogoutButton extends React.Component {

    constructor(){
        super();
        this.state = {
            logouted: false
        }
    }

    logout = () => {
        loginService.logout()
            .then(r => {
                this.setState({logouted: true})
                this.props.loginStatusChange(false);
                logoutCookie();
            })


    }


    render(){
        return (<Link to={fronendUrls.mainPage} onClick={() => this.logout()}>Wyloguj</Link>

        )
    }

}

const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)


export default LogoutButton = connect(mapStateToProps, mapDispatchToProps)(LogoutButton);