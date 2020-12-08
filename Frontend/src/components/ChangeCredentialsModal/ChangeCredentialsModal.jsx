import React from 'react';
import Modal from 'react-modal';
import button from "../../styles/buttons.css";
import {TextField} from "@material-ui/core";
import PasswordField from "material-ui-password-field";


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
        height: '60%',
        width: '60%'
    }
};

class ChangeCredentialsModal extends React.Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            newPassword: ""
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

                        <div>Uwaga - po zmianie użytkownika lub hasła zostaniesz automatycznie wylogowany. Możesz zalogować się ponownie przy użyciu nowych danych.</div>

                        <TextField onChange={(event) => this.setState({username: event.target.value})} disabled={!isUsernameChanging} value={this.state.username}/>

                        <PasswordField
                            // hintText="At least 8 characters"
                            // floatingLabelText="Enter your password"
                            // errorText="Your password is too short"
                            label="Hasło"
                            value={this.state.password}
                            onChange={event => this.setState({password:event.target.value})}
                        />

                        {isPasswordChanging && <PasswordField
                            // hintText="At least 8 characters"
                            // floatingLabelText="Enter your password"
                            // errorText="Your password is too short"
                            label="Nowe hasło"
                            value={this.state.newPassword}
                            onChange={event => this.setState({newPassword:event.target.value})}
                        />}

                        <button type="submit" className="button" onClick={() => onSave(this.state.username, this.state.password, this.state.newPassword)}>Zapisz</button>



                    </Modal>
                </div></div>
        );
    }
}

export default ChangeCredentialsModal;