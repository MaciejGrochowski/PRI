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
        this.setState({
            options: this.props.options
        })
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
            value = value.trim()
            let valueName = value.split(" ")[0]
            allOptions = allOptions.filter(c => !(c === value) && !c.startsWith(valueName))
        }
        allOptions = [...new Set(allOptions)]
        this.setState({options: allOptions});
    }

    onDelete = async (item, v) => {
        let values = this.state.values;
        values = values.filter(v => v !== item)
        await this.setState({values: values})
        if (v !== undefined) v.update(values);
        if(this.props.multiple) this.onDeleteChangeOptions(item)
    }

    onDeleteChangeOptions = value => {
        let allOptions = this.props.options;
        const values = this.state.values;
        for (let value of values) {
            let valueName = value.split(" ")[0]
            allOptions = allOptions.filter(c => !(c === value) && !c.startsWith(valueName))
        }
        this.setState({options: allOptions})


        // allOptions = allOptions.filter()
        //
        // let oldOptions = this.state.options;
        // let valueName = value.split(" ")[0]
        // let newOptions = this.props.options.filter(c => c === value || c.startsWith(valueName))
        // console.log(newOptions);
        // oldOptions.push.apply(oldOptions, newOptions);
        // console.log(oldOptions);
        // this.setState({options: oldOptions})
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
        this.setOptionsWithoutChoosenValues(this.props.generated)
        return
    }
    if (this.props.generated === "") {
        this.setState({values: []});
        return
    }
    if (!this.props.multiple) {
        this.setOptionsWithoutChoosenValues(this.props.generated)
        this.setState({values: this.props.generated})
    }
    else {
        this.setOptionsWithoutChoosenValues(this.props.generated.split(","))
        this.setState({values: this.props.generated.split(",")})
    }
}

filterOptions = (input, state) => {
        if(this.props.notSortOptions && state.inputValue==="") return input;
        let optionsContains = []
        let optionsStartWith = []
        let allOptions = []
        let inputValue = state.inputValue.toLowerCase()

        for(let option of input){
            let optionLowerCase = option.toLowerCase()
            if(optionLowerCase.startsWith(inputValue))
                optionsStartWith.push(option)
            else{
                if(optionLowerCase.includes(inputValue))
                    optionsContains.push(option)
            }
        }
        optionsContains.sort()
        optionsStartWith.sort()
        allOptions = optionsStartWith.concat(optionsContains)
        return allOptions;
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
                        options={this.props.multiple ? this.state.options : this.props.options}
                        id={this.props.multiple ? "ToDo here should be randomId" : id}
                        noOptionsText={"Brak opcji"}
                        style={{width: width}}
                        // popupIcon={<div style={{}}>ZIemniak</div>} ToDo Kasia spróbuj pokonać ziemniaka
                        renderInput={(params) => (
                            <TextField {...params} label={labelName}/>
                        )}
                        renderTags={(value, getTagProps, index) => (
                            <Tag label={this.state.values} onDelete={element => this.onDelete(element, v)} id={id}/>
                        )}
                        value={this.state.values}
                        onChange={(event, value, reason) => this.onInputChange(event, value, v)}
                        filterOptions={(input, state) => this.filterOptions(input, state)}
                    />
                    {this.props.canBeGenerated &&
                    <button className="detaleButton" onClick={() => this.randomClick()} disabled={this.props.disabled}><span>{element}</span></button>}
                </div>
            }</careerContext.Consumer>
    )
}
}


export default DefaultMultipleAutocomplete;