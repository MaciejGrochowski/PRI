import React from 'react';
import Modal from 'react-modal';
import test from '../../styles/popup.css';
import button from "../../styles/buttons.css";
import popup from "../../styles/popup.css";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {TextField} from "@material-ui/core";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";

import { Tooltip } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import InfoOutlinedIcon from '@material-ui/icons/InfoOutlined';

const element = <FontAwesomeIcon icon={faSyncAlt}/>

class GeneratorTextField extends React.Component {

    constructor() {
        super();
        this.state = {
            value: ""
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


    onChangeFunction(event, v){
        this.setState({value:event.target.value});
        // v.update(event.target.value);
    }

    onBlur = (event, v) => {
        v.update(event.target.value);
    }


    render() {

        return (
            <careerContext.Consumer>
                {v =><div className="generator-element">
                    <TextField label={this.props.label}
                               style={this.props.style}
                               id="characterGeneratorYearOfBirth"
                               value={this.state.value}
                               onChange={event => this.onChangeFunction(event, v)}
                               onBlur={(event) => this.onBlur(event, v)}
                    />
                    {this.props.canBeGenerated && <button className="detaleButton small" onClick={() => this.props.onRandomClick()} disabled={this.props.disabled}><span>{element}</span></button>}
                    <Tooltip title="{this.props.tooltipText}" placement='right-start'>
                       <IconButton aria-label="info">
                          <InfoOutlinedIcon />
                       </IconButton>
                    </Tooltip>
                    </div>
                }
            </careerContext.Consumer>
        )
    }
}

export default GeneratorTextField;