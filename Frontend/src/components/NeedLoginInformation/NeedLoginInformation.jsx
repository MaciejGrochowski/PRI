import React from "react";
import {fronendUrls} from "../../commons/urls";
import {ItemMenu} from "../Menu/ExampleMenu.style";
import {Link} from "react-router-dom";



class NeedLoginInformation extends React.Component {

    render(){


        const {text} = this.props;
        return (<div className = "error-message">
                <div>{text}</div>
                <Link className="center-block" to={fronendUrls.loginPage}><ItemMenu>Zaloguj się</ItemMenu></Link>

        </div>

        )
    }

}

export default NeedLoginInformation;