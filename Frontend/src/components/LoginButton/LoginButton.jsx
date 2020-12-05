import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {loginStatusChange} from "../../actions";
import { connect } from "react-redux";
import {getInfoFromToken, getToken, isValidToken} from "../../services/util";
import {ItemMenu} from "../Menu/ExampleMenu.style";
import LogoutButton from "../LogoutButton/LogoutButton";



class LoginButton extends React.Component {

    componentDidMount() {
        if(!this.props.isLogged && getToken() && isValidToken(getToken())) this.props.loginStatusChange(true);
        if(this.props.isLogged && !getToken() || !isValidToken(getToken())) this.props.loginStatusChange(false);

    }


    render(){
        return (<div>{this.props.isLogged ? <div>{"Witaj, " + getInfoFromToken(getToken()).sub} <LogoutButton/> </div> : this.props.notLoggedShowComponent}</div>

        )
    }

}

const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)


export default LoginButton = connect(mapStateToProps, mapDispatchToProps)(LoginButton);