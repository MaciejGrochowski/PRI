import React from 'react';
import {TextField} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {Tag} from "./DefaultMultipleAutocomplete.style";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";

const element = <FontAwesomeIcon icon={faSyncAlt}/>


class DefaultMultipleAutocomplete extends React.Component {

    constructor() {
        super();
        this.state = {
            values: []
        }
    }

    onInputChange = (event, value, v) => {
        this.setState({values: value});
        if(v!== undefined) v.update(value);
    }

    onDelete = item => {
        let values = this.state.values;
        values = values.filter(v => v!==item)
        this.setState({values: values})
    }

    randomClick = () => {
        //ToDo here should be random value from backend, not hardcoded "Banita"
        console.log(this.state.values);
        console.log("Ustawiam banitę");
        if(!this.props.multiple) this.setState({values: "Los"});
        else this.setState({values: ["Los"]})
    }

    render() {
        const {options, labelName, id } = this.props;
        const width = this.props.width ? this.props.width : 300;

        return(
            <careerContext.Consumer>
                {v =>
                    <>
                    <Autocomplete
                        multiple={this.props.multiple}
                        options={options}
                        id={this.props.multiple ? "ToDo here should be randomId" : id}
                        noOptionsText={"Brak opcji"}
                        style={{width: width}}
                        disabled={this.props.disabled}
                        renderInput={(params) => (
                            <TextField {...params} label={labelName}/>
                        )}
                        renderTags={(value, getTagProps, index) => (
                            <Tag label={this.state.values} onDelete={this.onDelete} id={id}/>
                        )}
                        value={this.state.values}
                        onChange={(event, value, reason) => this.onInputChange(event, value, v)}
                    />
                    {this.props.canBeGenerated && <button className="detaleButton" onClick={() => this.randomClick()}><span>{element}</span></button>}
                    </>
                }</careerContext.Consumer>
        )
    }
}


export default DefaultMultipleAutocomplete;