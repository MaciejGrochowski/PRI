import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Cookie from "js-cookie";
import { connect } from "react-redux";
import {Redirect} from 'react-router';
import "../../styles/manual.css";


class CreateCharacterManual extends React.Component {

    render(){
        return (
        <div className = "container-manual">
            <div className = "manual-body">
                <div className="manual-title">
                    Generowanie losowych i tworzenie własnych postaci.
                </div>
                <div className= "manual-paragraf">
                    <div className = "manual-paragraf-title">paragraf title</div>
                    Tutaj teks o wygenerowaniu wszystkiego
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
export default CreateCharacterManual;