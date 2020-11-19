import React from 'react';
import Modal from 'react-modal';
import test from '../../styles/popup.css';
import button from "../../styles/buttons.css";
import popup from "../../styles/popup.css";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {TextField} from "@material-ui/core";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";

const element = <FontAwesomeIcon icon={faSyncAlt}/>


class GeneratorTextField extends React.Component {

    constructor() {
        super();
        this.state = {
            value: "",
            errorText: "",
            errorState: false
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.generated !== this.props.generated){
            this.setState({value: this.props.generated})
        }
    }

    randomValue = v => {
        this.setState({value: "Los"})
        v.update("Los");
    }

    checkLettersOnly(input){
        if (this.props.label === "Imię") {
            if (input.match("^[A-Z]([a-z]?)*$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + " powinno zaczynać się z wielkiej litery i zawierać litery A-Z"})
                this.setState({errorState: true})
            }
        }
            else {
            if (input.match("^[A-Z][a-z]+$|^von [A-Z][a-z]+$|^Von [A-Z][a-z]+$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + "Poprawne formy nazwisk to: Von Munder | von Munder | Munder"})
                this.setState({errorState: true})
            }
        }
    }

    checkNumOnly(input) {
        if (this.props.label === "Dzień urodzenia")
            if (input.match("^[1-3][0-9]$|^[1-9]$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + " może mieć tylko wartość 1-34"})
                this.setState({errorState: true})
            }
        else if (this.props.label === "Rok urodzenia") {
            if (input.match("^0$|^[1-2][0-9]{0,3}$|^[3-9][0-9]{0,2}$|^3000$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + " może mieć tylko wartość 0-3000"})
                this.setState({errorState: true})
            }
        } else if (this.props.label === "Wzrost") {
            if (input.match("^[1-2][0-9]{2,2}$|^[5-9][0-9]{1,1}$|^300$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + " może mieć tylko wartość 50-300"})
                this.setState({errorState: true})
            }
        } else if (this.props.label === "Waga") {
            if (input.match("^[1-7][0-9]{2,2}$|^[1-9][0-9]{1,1}$|^800$") || input.match("^(?![\\s\\S])")) {
                this.setState({errorText: ""})
                this.setState({errorState: false})
            } else {
                this.setState({errorText: this.props.label + " może mieć tylko wartość 10-800"})
                this.setState({errorState: true})
            }
        }
    }



    onChangeFunction(event, v){
        this.setState({value:event.target.value});
        // v.update(event.target.value);
            if(this.props.typeOfValidation === "LetterOnly"){
                this.checkLettersOnly(event.target.value)
            }
            else if (this.props.typeOfValidation === "NumOnly"){
                this.checkNumOnly(event.target.value)
            }
    }


    onBlur = (event, v) => {
        v.update(event.target.value);
    }


    render() {
        return (
            <careerContext.Consumer>
                {v =><div className="generator-element">
                    <TextField error={this.state.errorState}
                               label={this.props.label}
                               style={this.props.style}
                               id="characterGeneratorYearOfBirth"
                               value={this.state.value}
                               helperText = {this.state.errorText}
                               onChange={event => this.onChangeFunction(event, v)}
                               onBlur={(event) => this.onBlur(event, v)}
                    />
                    {this.props.canBeGenerated && <button className="detaleButton small" onClick={() => this.props.onRandomClick()} disabled={this.props.disabled}><span>{element}</span></button>}
                    </div>
                }
            </careerContext.Consumer>
        )
    }
}

export default GeneratorTextField;
