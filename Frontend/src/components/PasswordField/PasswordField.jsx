import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import {FormHelperText} from "@material-ui/core";


class PasswordField extends React.Component {
    constructor() {
        super();
        const tmp = makeStyles((theme) => ({
            root: {
                display: 'flex',
                flexWrap: 'wrap',
            },
            margin: {
                margin: theme.spacing(1),
            },
            withoutLabel: {
                marginTop: theme.spacing(3),
            },
            textField: {
                width: '25ch',
            },
        }));
        this.state = {
            amount: '',
            password: '',
            weight: '',
            weightRange: '',
            showPassword: false,
            classes: tmp
        }
    }

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
        this.props.handleChangePassword(event, this.props.valueName, this.props.setStateFunction, this.props.validationFunc);
    }

    handleClickShowPassword = () => {
        this.setState({showPassword: !this.state.showPassword})
    };

    handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    render() {
        return (<FormControl className={clsx(this.state.classes.margin, this.state.classes.textField)}>
            <InputLabel error={this.props.error} htmlFor="standard-adornment-password">{this.props.label}</InputLabel>
            <Input
                id="standard-adornment-password"
                type={this.state.showPassword ? 'text' : 'password'}
                value={this.state.password}
                error={this.props.error}
                onChange={this.handleChange('password')}
                onKeyDown={this.props.onKeyDown}
                endAdornment={
                    <InputAdornment position="end">
                        <IconButton
                            aria-label="toggle password visibility"
                            onClick={this.handleClickShowPassword}
                            onMouseDown={this.handleMouseDownPassword}
                        >
                            {this.state.showPassword ? <Visibility/> : <VisibilityOff/>}
                        </IconButton>
                    </InputAdornment>
                }
            />
            <FormHelperText id="my-helper-text" error={true}>{this.props.errorText}</FormHelperText>
        </FormControl>)


    }
}

export default PasswordField;