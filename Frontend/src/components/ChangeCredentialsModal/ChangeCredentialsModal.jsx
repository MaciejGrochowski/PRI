import React from 'react';
import Modal from 'react-modal';
import test from '../../../styles/popup.css';
import button from "../../../styles/buttons.css";
import popup from "../../../styles/popup.css";
import historyService from "../../../services/historyService";
import parse from "html-react-parser";

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faAngleRight, faAngleLeft} from '@fortawesome/free-solid-svg-icons';
import {TextField} from "@material-ui/core";
import PasswordField from "material-ui-password-field";

const elementPrev = <FontAwesomeIcon icon={faAngleLeft}/>
const elementNext = <FontAwesomeIcon icon={faAngleRight}/>

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

class HistoryDetailsPopup extends React.Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            newPassword: ""
        }
    }

    componentDidMount() {
        this.setState({
            username: this.props.username
        })
    }

    render() {
        const {isOpen, onRequestClose, isUsernameChanging, isPasswordChanging} = this.props;


        return (
            <div className = "customize-button">
                <div className = "popup-body">
                    <Modal
                        isOpen={isOpen}
                        onRequestClose={() => onRequestClose()}
                        style={customStyles}
                    >

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


                    </Modal>
                </div></div>
        );
    }
}

export default HistoryDetailsPopup;