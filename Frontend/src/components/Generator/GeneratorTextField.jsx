import React from 'react';
import Modal from 'react-modal';
import test from '../../styles/popup.css';
import button from "../../styles/buttons.css";
import popup from "../../styles/popup.css";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {TextField} from "@material-ui/core";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";
import GeneratorTooltip from '../Tooltip/GeneratorTooltip'

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
            if(this.props.validationFunc && this.props.label !== "Nazwisko"){
                let validationEffect = this.props.validationFunc(this.props.generated.toString())
                this.setState({
                    errorText: validationEffect.errorText,
                    errorState: validationEffect.errorState
                })
            }
        }
    }

    onChangeFunction(event, v){
        this.setState({value:event.target.value});

        if(this.props.validationFunc){
            let validationEffect = this.props.validationFunc(event.target.value)
            this.setState({
                errorText: validationEffect.errorText,
                errorState: validationEffect.errorState
            })
        }
    }


    onBlur = (event, v) => {
        v.update(event.target.value);
    }

    onRandomClick = async () => {

        await this.props.onRandomClick();

        if(this.props.validationFunc){
            this.setState({
                errorText: "",
                errorState: false
            })
        }
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
                    {this.props.canBeGenerated && <button className="detaleButton small" onClick={() => this.onRandomClick()} disabled={this.props.disabled}><span>{element}</span></button>}
                    {this.props.tooltip && <GeneratorTooltip showIt={!this.props.disabled} content={this.props.tootipText}
                                    tooltipTypeName={this.props.label}
                                    raceTooltipAtribute={this.props.ifTooltipRace}
                                    sexTooltipAtribute={this.props.ifTooltipSex}
                    />}
                    </div>
                }
            </careerContext.Consumer>
        )
    }
}

export default GeneratorTextField;
