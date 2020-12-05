import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {contactsFetched} from "../../actions";
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
                this.props.contactsFetched(false);
                logoutCookie();
            })


    }


    render(){
        if (this.state.logouted) {
            console.log(true);
            return <Redirect push to={fronendUrls.mainPage} /> //ToDo all redirects shouls use this method, not window/document changes.
        }

        return (<button onClick={() => this.logout()}>Wyloguj</button>

        )
    }

}

const mapStateToProps = (state) => {
    return {
        contacts: state.contacts // (1)
    }
};
const mapDispatchToProps = { contactsFetched }; // (2)


export default LogoutButton = connect(mapStateToProps, mapDispatchToProps)(LogoutButton);