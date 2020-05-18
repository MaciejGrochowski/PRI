import React from 'react';
import {TextField} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {Tag} from "./DefaultMultipleAutocomplete.style";

class DefaultMultipleAutocomplete extends React.Component {

    constructor() {
        super();
        this.state = {
            values: []
        }
    }

    onInputChange = (event, value, reason) => {
        this.setState({values: value})
    }

    onDelete = item => {
        let values = this.state.values;
        values = values.filter(v => v!==item)
        this.setState({values: values})
    }

    render() {
        const {options, labelName, id } = this.props;
        const width = this.props.width ? this.props.width : 300;

        return(
            <Autocomplete
                multiple={this.props.multiple}
                options={options}
                id={this.props.multiple ? "ToDo here should be randomId" : id}
                noOptionsText={"Brak opcji"}
                style={{ width: width }}
                disabled={this.props.disabled}
                renderInput={(params) => (
                    <TextField {...params} label={labelName}/>
                )}
                renderTags={(value, getTagProps, index) => (
                    <Tag label={value} onDelete={this.onDelete} id={id}   />
                )}
                value={this.state.values}
                onChange={(event, value, reason) => this.onInputChange(event,value,reason)}
            />
        )
    }
}


export default DefaultMultipleAutocomplete;