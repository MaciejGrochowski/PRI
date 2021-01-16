import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../../styles/manual.css";



class SessionManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Tworzenie, prowadzenie i obsługa sesji.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">paragraf title</div>
                    Paragraf
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">paragraf title</div>
                    Paragraf
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">paragraf title</div>
                    Paragraf
                </div>
            </div>
        </div>
        )
    }

}
export default SessionManual;