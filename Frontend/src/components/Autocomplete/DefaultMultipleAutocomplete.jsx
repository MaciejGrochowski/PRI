import React from 'react';
import {TextField} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {Tag} from "./DefaultMultipleAutocomplete.style";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";

const element = <FontAwesomeIcon icon={faSyncAlt}/>

//ToDo Opakować defaultMultipleAutocomplete w defaultGeneratorAutocomplete i pozbyć się stąd logiki generatora...
class DefaultMultipleAutocomplete extends React.Component {

    constructor() {
        super();
        this.state = {
            values: [],
            options: []
        }
    }

    componentDidMount() {
        this.setState({options: []})
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if (prevProps.generated !== this.props.generated) {
            this.setGenerated()
        }
        if (this.props.options !== prevProps.options) {
            this.setState({options: this.props.options})
        }
    }

    onInputChange = (event, value, v) => {
        this.setState({values: value});
        if (v !== undefined) v.update(value);
        if(this.props.multiple) this.setOptionsWithoutChoosenValues(value);
    }

    setOptionsWithoutChoosenValues = values => {
        let allOptions = this.props.options;
        for (let value of values) {
            let valueName = value.split(" ")[0]
            allOptions = allOptions.filter(c => !(c === value) && !c.startsWith(valueName))
        }
        allOptions = [...new Set(allOptions)]
        this.setState({options: allOptions});
    }

    onDelete = item => {
        let values = this.state.values;
        values = values.filter(v => v !== item)
        this.setState({values: values})
        if(this.props.multiple) this.onDeleteChangeOptions(item)
    }

    onDeleteChangeOptions = value => {
        let allOptions = this.props.options;
        let valueName = value.split(" ")[0]
        let newOptions = allOptions.filter(c => c === value || c.startsWith(valueName))
        allOptions.push.apply(allOptions, newOptions);
        this.setState({options: allOptions})
    }

randomClick = () => {
    if (this.props.onRandomClick) this.props.onRandomClick();
    else (console.err("Brak funkcji"))
    //ToDo here should be random value from backend, not hardcoded "Banita"
    // if(!this.props.multiple) this.setState({values: "Los"});
    // else this.setState({values: ["Los"]})
}

setGenerated = () => {
    if (Array.isArray(this.props.generated)) {
        this.setState({values: this.props.generated})
        return
    }
    if (this.props.generated === "") {
        this.setState({values: []});
        return
    }
    if (!this.props.multiple) this.setState({values: this.props.generated})
    else this.setState({values: this.props.generated.split(",")})
}

render()
{
    const {labelName, id} = this.props;
    const width = this.props.width ? this.props.width : 300;

    return (
        <careerContext.Consumer>
            {v =>
                <div className="flex-div">
                    <Autocomplete
                        multiple={this.props.multiple}
                        options={this.state.options}
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
                    {this.props.canBeGenerated &&
                    <button className="detaleButton" onClick={() => this.randomClick()}><span>{element}</span></button>}
                </div>
            }</careerContext.Consumer>
    )
}
}


export default DefaultMultipleAutocomplete;