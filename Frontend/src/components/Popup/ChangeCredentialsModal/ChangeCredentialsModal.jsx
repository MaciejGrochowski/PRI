import React from 'react';
import Modal from 'react-modal';
import button from "../../../styles/buttons.css";
import {TextField} from "@material-ui/core";
import PasswordField from "../../PasswordField/PasswordField";
import {validationPassword} from "../../../pages/RegisterPage/validation";
import {polishCodeErrors} from "../../../commons/texts-pl";


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
    },
    overlay:{
        backgroundColor: 'rgba(63, 63, 63, 0.75)'
    }
};

class ChangeCredentialsModal extends React.Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            newPassword: "",
            confirmNewPassword: "",
            errorNewPassword: {},
            errorConfirmNewPassword: {}
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

     handleChangePassword = event => this.setState({password: event.target.value});

    handleChangeUsername = event => this.setState({username: event.target.value})

    handleChangeConfirmPassword = event => {
        this.setState({newPassword: event.target.value})
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

    handleChangeConfirmNewPassword = event => {
        this.setState({confirmNewPassword: event.target.value})
        if(event.target.value !== this.state.newPassword){
            this.setState({
                errorConfirmNewPassword: {
                    errorState: true,
                    errorText: "Hasła nie są identyczne"
                }
            })
        }
        else{
            this.setState({errorConfirmNewPassword: {}})
        }
    }

    enterListener = event => {
        if (event.keyCode === 13 && !this.isButtonDisabled()) {
            this.props.onSave(this.state.username, this.state.password, this.state.newPassword);
        }
    }

    isButtonDisabled = () => {
        if(this.props.isUsernameChanging){
            return this.state.username==="" || this.state.password===""
        }
        else{
            return this.state.password==="" || this.state.newPassword==="" || this.state.errorNewPassword.errorState ||
                this.state.errorConfirmNewPassword.errorState || this.state.confirmNewPassword===""
        }
    }

    onRequestClose = () => {
        this.setState({password: "", newPassword: "", username: ""});
        this.props.onRequestClose();
    }



    render() {
        const {isOpen, isUsernameChanging, isPasswordChanging, onSave, errorCode} = this.props;


        return (
            <div className = "customize-button">
                <div className = "popup-body">
                    <Modal
                        isOpen={isOpen}
                        onRequestClose={() => this.onRequestClose()}
                        style={customStyles}
                    >
                        <div className = "positive-message">Uwaga - po zmianie użytkownika lub hasła zostaniesz automatycznie wylogowany. Możesz zalogować się ponownie przy użyciu nowych danych.</div>
<div className="modal-tittle">
<div className = "login-title">{this.props.title}</div>
<div className="block-component" id={"shortInput"}>
                        <TextField onChange={this.handleChangeUsername} disabled={!isUsernameChanging} value={this.state.username}/>
</div>
<div className="block-component" id={"shortInput"}>
    <PasswordField
        handleChangePassword={this.handleChangePassword}
        label={isUsernameChanging ? "Hasło" : "Stare hasło"}
        value={this.state.oldPassword}
        onKeyDown={(e) => this.enterListener(e)}
    />
</div>

                        {isPasswordChanging &&
                        <>
                        <div className="block-component" id={"shortInput"}><PasswordField
                            error={this.state.errorNewPassword.errorState}
                            label={"Nowe hasło"}
                            value={this.state.newPassword}
                            errorText={this.state.errorNewPassword.errorText}
                            handleChangePassword={this.handleChangeConfirmPassword}
                            onKeyDown={(e) => this.enterListener(e)}
                        />
                        </div><div className="block-component" id={"shortInput"}>
                            <PasswordField
                                error={this.state.errorConfirmNewPassword.errorState}
                                label={"Powtórz nowe hasło"}
                                value={this.state.confirmNewPassword}
                                errorText={this.state.errorConfirmNewPassword.errorText}
                                handleChangePassword={this.handleChangeConfirmNewPassword}
                                onKeyDown={(e) => this.enterListener(e)}
                            />
                        </div>

                        </>}

    {errorCode && <div className = "error-message">{polishCodeErrors[errorCode]}</div>}
<div className="block-component">

                        <button type="submit" disabled={this.isButtonDisabled()} className="zaloguj-button" onClick={() => onSave(this.state.username, this.state.password, this.state.newPassword)}>Zapisz</button>
</div>

</div>
                    </Modal>
                </div></div>
        );
    }
}

export default ChangeCredentialsModal;