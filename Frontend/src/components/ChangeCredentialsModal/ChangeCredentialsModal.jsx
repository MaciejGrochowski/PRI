import React from 'react';
import Modal from 'react-modal';
import button from "../../styles/buttons.css";
import {TextField} from "@material-ui/core";
import PasswordField from "../PasswordField/PasswordField";
import {textsPolish} from "../../commons/texts-pl";
import {validationPassword} from "../../pages/RegisterPage/validation";


const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
        backgroundColor: '#292F2F',
        color: 'white',
        zIndex: '100!important',
        height: '50%',
        width: '50%'
    }
};

class ChangeCredentialsModal extends React.Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            newPassword: "",
            errorNewPassword: {}
        }
    }

     componentDidUpdate(prevProps, prevState){

        if(prevProps.isOpen !== this.props.isOpen ){
            this.setState({
                username: this.props.username,
                password: "",
                newPassword: ""
            })
        }
     }

     handleChangePassword = password => this.setState({password: password});

    handleChangeConfirmPassword = event => {
        this.setState({confirmPassword: event.target.value})
        let tmp = validationPassword(event.target.value)
        if(tmp.errorState){
            this.setState({
                errorNewPassword: {
                    errorState: tmp.errorState,
                    errorText: tmp.errorText
                }
            })
        }
        else{
            this.setState({errorNewPassword: {}})
        }
    }



    render() {
        const {isOpen, onRequestClose, isUsernameChanging, isPasswordChanging, onSave} = this.props;


        return (
            <div className = "customize-button">
                <div className = "popup-body">
                    <Modal
                        isOpen={isOpen}
                        onRequestClose={() => onRequestClose()}
                        style={customStyles}
                    >
                        <div>{this.props.title}</div>
                        <div className = "positive-message">Uwaga - po zmianie użytkownika lub hasła zostaniesz automatycznie wylogowany. Możesz zalogować się ponownie przy użyciu nowych danych.</div>
<div className="login-body">
<div className="block-component">
                        <TextField onChange={(event) => this.setState({username: event.target.value})} disabled={!isUsernameChanging} value={this.state.username}/>
</div>
<div className="block-component">
    <PasswordField
        handleChangePassword={this.handleChangePassword}
        label={"Stare hasło"}
    />
</div>
<div className="block-component">
                        {isPasswordChanging &&
                        <PasswordField
                            error={this.state.errorNewPassword.errorState}
                            label={"Nowe hasło"}
                            value={this.state.confirmPassword}
                            errorText={this.state.errorNewPassword.errorText}
                            handleChangePassword={this.handleChangeConfirmPassword}
                        />}
                        </div>
<div className="block-component">
                        <button type="submit" className="zaloguj-button" onClick={() => onSave(this.state.username, this.state.password, this.state.newPassword)}>Zapisz</button>
</div>

</div>
                    </Modal>
                </div></div>
        );
    }
}

export default ChangeCredentialsModal;