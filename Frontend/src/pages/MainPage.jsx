import React from "react";
import {Link} from "react-router-dom";
import "../styles/globalStyles.css";


class MainPage extends React.Component {

    render(){
        return (
            <div className = "plainPage">
                        <h1>Welcome!</h1>
                        <p>Click  to see a greeting.</p>
            </div>
        )
    }

}

export default MainPage;