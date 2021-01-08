import React from 'react';
import Modal from 'react-modal';
import button from "../../../styles/buttons.css";
import {TextField} from "@material-ui/core";
import PasswordField from "../../PasswordField/PasswordField";
import {textsPolish} from "../../../commons/texts-pl";
import {validationPassword} from "../../../pages/RegisterPage/validation";


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
    } //ToDo delete it or set in one file, not in all modals
};

class NewSessionModal extends React.Component {

    constructor() {
        super();
        this.state = {
            title: "",
            description: ""
        }
    }
    render() {
        const {isOpen, onRequestClose, onSave, title} = this.props;


        return (
            <div className = "customize-button">
                <div className = "popup-body">
                    <Modal
                        isOpen={isOpen}
                        onRequestClose={() => onRequestClose()}
                        style={customStyles}
                    >
                        <div>{title}</div>

                        <TextField label="Tytuł" value={this.state.title} onChange={event => this.setState({title: event.target.value})}/>


                        <TextField
                            id="outlined-textarea"
                            label="Opis"
                            placeholder="Brak opisu."
                            rows={10}
                            rowsMax={10}
                            fullWidth
                            multiline
                            variant="outlined"
                            onChange={(event) => this.setState({description: event.target.value})} value={this.state.description}/>

                            <button onClick={() => onSave(this.state.title, this.state.description)}>Zapisz</button>
                    </Modal>
                </div></div>
        );
    }
}

export default NewSessionModal;