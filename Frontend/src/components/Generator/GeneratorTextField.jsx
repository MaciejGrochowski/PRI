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

// TODO poprawić regexy bo dalej nie łapie gdy są znaki (!@#$) specjalne ale tak poza tym to git
    checkLettersOnly(input){
        if (input.match("\\b[A-Z]([a-z]?)*\\b") || input.match("^(?![\\s\\S])") || !input.match("[a-z]")) {
            this.setState({ errorText: "" })
            this.setState( {errorState: false})
        } else {
            this.setState({ errorText: this.props.label + " powinno zaczynać się z wielkiej litery i zawierać litery A-Z" })
            this.setState( {errorState: true} )
        }
    }

    twoNumOnly(input){
        if (input.match("\\b[1-3][0-9]\\b|\\b[1-9]\\b") || input.match("^(?![\\s\\S])") || !input.match("[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)]")) {
            this.setState({ errorText: "" })
            this.setState( {errorState: false})
        } else {
            this.setState({ errorText: this.props.label + " może mieć tylko wartość 1-34" })
            this.setState( {errorState: true} )
        }
    }

    yearNumOnly(input){
        if (input.match("\\b0\\b|\\b[1-2][0-9]{0,3}\\b|\\b[3-9][0-9]{0,2}\\b|\\b3000\\b")) {
            this.setState({ errorText: "" })
            this.setState( {errorState: false})
        } else {
            this.setState({ errorText: this.props.label + " może mieć tylko wartość 0-3000" })
            this.setState( {errorState: true} )
        }
    }

    onChangeFunction(event, v){
        this.setState({value:event.target.value});
        // v.update(event.target.value);
            if(this.props.typeOfValidation === "LetterOnly"){
                this.checkLettersOnly(event.target.value)
            }
            else if (this.props.typeOfValidation === "twoNumOnly"){
                this.twoNumOnly(event.target.value)
            }
            else if(this.props.typeOfValidation === "yearNumOnly"){
                this.yearNumOnly(event.target.value)
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
