import React from 'react';
import {TextField} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {Tag} from "./DefaultMultipleAutocomplete.style";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";
import GeneratorTooltip from '../Tooltip/GeneratorTooltip'

const element = <FontAwesomeIcon icon={faSyncAlt}/>

//ToDo Opakować defaultMultipleAutocomplete w defaultGeneratorAutocomplete i pozbyć się stąd logiki generatora...
//ToDo to już nie jest multiple defaultowo, zmień nazwę
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
        // console.log("DefaultMultipleAutocomplete zauważył zmianę swojego stanu")
        if (prevProps.generated !== this.props.generated) {
            // console.log("DefaultMultipleAutocomplete zauważył, że zmiana dotyczy wygenerowanych wartości")
            this.setGenerated()
        }
        if (this.props.options !== prevProps.options) {
            this.setState({options: this.props.options})
        }
        // if (this.state.values !== prevState.values){
        //     this.countCharsInValues()
        // }
    }

    onInputChange = (event, value, v) => {
        this.setState({values: value});
        if (v !== undefined) v.update(value);
        if(this.props.multiple) this.setOptionsWithoutChoosenValues(value);
    }

    setOptionsWithoutChoosenValues = values => {
        //console.log("Zmienia się liczba opcji dostępnych do wyboru")
        let allOptions = this.props.options;
        if(!values){
            this.setState({options: allOptions})
            return;
        }
        for (let value of values) {
            if(value===null) continue;
            value = value.trim()
            let valueName = value.split(" +")[0]
            allOptions = allOptions.filter(c => !(c === value) && !c.startsWith(valueName))
        }
        allOptions = [...new Set(allOptions)]
        // console.log("Opcje dostępne do wyboru po zdarzeniu to:")
        // console.log(allOptions)
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
            let valueName = value.split(" +")[0]
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

// countCharsInValues = () => {
//     let count = 0
//     for(let value of this.state.values){
//         count += value.length
//     }
//     console.log(count)
//     this.setState({valueChars: count})
// }

render()
{
    const {labelName, id, defaultValue} = this.props;
    const width = this.props.width ? this.props.width : 300;
    console.log(defaultValue);
    return (
        <careerContext.Consumer>
            {v =>
                <div className="flex-div">
                    <Autocomplete
                        multiple={this.props.multiple}
                        // multiline
                        options={this.props.multiple ? this.state.options : this.props.options}
                        id={this.props.multiple ? this.props.labelName : id}
                        disablePortal={this.props.disablePortal || false}
                        noOptionsText={"Brak opcji"}
                        style={{width: width}}
                        popupIcon={<div/>}
                        renderInput={(params) => (
                            <TextField {...params} multiline label={labelName} defaultValue={defaultValue}/>
                        )}
                         renderTags={(value, getTagProps, index) => (
                        //     <div className = {this.props.multiple &&
                        //     (this.state.valueChars < 25 && this.state.values.length === 2) ||
                        //     (this.state.valueChars > 20 && this.state.values.length === 3) ||
                        //     this.state.values.length > 3
                        //     ? "filter" : "block-element"}>
                               <div className = "filter">
                                   <Tag label={this.state.values} onDelete={element => this.onDelete(element, v)} id={id}/>
                            <br/></div>)}
                        value={this.state.values}
                        onChange={(event, value, reason) => this.onInputChange(event, value, v)}
                        filterOptions={(input, state) => this.filterOptions(input, state)}
                    />
                    {this.props.canBeGenerated &&
                    <button className="detaleButton small" onClick={() => this.randomClick()} disabled={this.props.disabled}><span>{element}</span></button>}
                    {this.props.tooltip && <GeneratorTooltip showIt={!this.props.disabled} content={this.props.tootipText}
                             tooltipTypeName={this.props.labelName}
                             birthPlaceTooltipAtribute={this.props.ifTooltipBirthPlace}
                             raceTooltipAtribute={this.props.ifTooltipRace}
                             professionTooltipAtribute={this.props.ifTooltipProfession}
                             yearOfBirthTooltipAtribute={this.props.ifTooltipBirthYear}
                             sexTooltipAtribute={this.props.ifTooltipSex}
                             religionTooltipAtribute={this.props.ifTooltipReligion}
                             baseStatsTooltipAtributet={this.props.ifTooltipBaseStats}
                             heightTooltipAtributet={this.props.ifTooltipHeight}
                             weightTooltipAtributet={this.props.ifTooltipWeight}
                    />}
                </div>
            }</careerContext.Consumer>
    )
}
}


export default DefaultMultipleAutocomplete;