import React from 'react';
import Modal from 'react-modal';
import test from '../../styles/popup.css';
import button from "../../styles/buttons.css";
import popup from "../../styles/popup.css";
import {careerContext} from "../../pages/CharacterGeneratorPage/context";
import {TextField} from "@material-ui/core";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSyncAlt} from "@fortawesome/free-solid-svg-icons";
import { createMuiTheme } from '@material-ui/core/styles';
import { grey, deepPurple, amber } from '@material-ui/core/colors';
import { withStyles } from "@material-ui/core/styles";



/*const CssTextField = withStyles({
    root: {
        '& label.Mui-focused': {
            color: 'green',
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: 'green',
        },
    }
})(TextField);*/
const styles = {
    root: {
        background: "black"
    },
    input: {
        color: "#2EFF22"
    }
};

const element = <FontAwesomeIcon icon={faSyncAlt}/>


class GeneratorTextField extends React.Component {

    constructor() {
        super();
        this.state = {
            value: "",
            errorText: {}
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
        if (event.target.value.match("elo")) {
            this.setState({ errorText: "no elo" })
            console.log("elo")
        } else {
            this.setState({ errorText: "Invalid format: ###-###-####" })
            console.log(this.state.errorText)
        }
    }

    onBlur = (event, v) => {
        v.update(event.target.value);
    }


    render() {
        const { classes } = this.props;
        return (
            <careerContext.Consumer>
                {v =><div className="generator-element">
                    <TextField error={false}
                               label={this.props.label}
                               style={this.props.style}
                               id="characterGeneratorYearOfBirth"
                               value={this.state.value}
                               errorText= {this.state.errorText}
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

/*
//import React from 'react';
//import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
            width: 200,
        },
    },
}));

export default function ValidationTextFields() {
    const classes = useStyles();

    return (
        <form className={classes.root} noValidate autoComplete="off">
            <div>
                <TextField error id="standard-error" label="Error" defaultValue="Hello World" />
                <TextField
                    error
                    id="standard-error-helper-text"
                    label="Error"
                    defaultValue="Hello World"
                    helperText="Incorrect entry."
                />
            </div>
            <div>
                <TextField
                    error
                    id="filled-error"
                    label="Error"
                    defaultValue="Hello World"
                    variant="filled"
                />
                <TextField
                    error
                    id="filled-error-helper-text"
                    label="Error"
                    defaultValue="Hello World"
                    helperText="Incorrect entry."
                    variant="filled"
                />
            </div>
            <div>
                <TextField
                    error
                    id="outlined-error"
                    label="Error"
                    defaultValue="Hello World"
                    variant="outlined"
                />
                <TextField
                    error
                    id="outlined-error-helper-text"
                    label="Error"
                    defaultValue="Hello World"
                    helperText="Incorrect entry."
                    variant="outlined"
                />
            </div>
        </form>
    );
}
*/
