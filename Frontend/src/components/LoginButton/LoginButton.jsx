import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {contactsFetched} from "../../actions";
import { connect } from "react-redux";
import {getInfoFromToken, getToken, isValidToken} from "../../services/util";
import {ItemMenu} from "../Menu/ExampleMenu.style";
import LogoutButton from "../LogoutButton/LogoutButton";



class LoginButton extends React.Component {

    componentDidMount() {
        if(!this.props.contacts && getToken() && isValidToken(getToken())) this.props.contactsFetched(true);
        if(this.props.contacts && !getToken() || !isValidToken(getToken())) this.props.contactsFetched(false);

    }


    render(){
        return (<div>{this.props.contacts ? <div>{"Witaj, " + getInfoFromToken(getToken()).sub} <LogoutButton/> </div> : this.props.notLoggedShowComponent}</div>

        )
    }

}

const mapStateToProps = (state) => {
    return {
        contacts: state.contacts // (1)
    }
};
const mapDispatchToProps = { contactsFetched }; // (2)


export default LoginButton = connect(mapStateToProps, mapDispatchToProps)(LoginButton);