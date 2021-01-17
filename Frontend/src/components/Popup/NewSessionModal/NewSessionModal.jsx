import React from 'react';
import Modal from 'react-modal';
import button from "../../../styles/buttons.css";
import {TextField} from "@material-ui/core";
import "../../../styles/login-page.css";

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
            name: "",
            description: ""
        }
    }

    onSave = (name, description) => {
        this.setState({name: "", description: ""})
        this.props.onSave(name, description);
    }

    render() {
        const {isOpen, onRequestClose, title} = this.props;


        return (
            <div className = "customize-button">
                <div className = "popup-body">
                    <Modal
                        isOpen={isOpen}
                        onRequestClose={() => onRequestClose()}
                        style={customStyles}
                    >
                    <div className="popup-margin-container">
                        <div className="login-title">{title}</div>

                        <div style={{width: "100%"}}><TextField label="Tytuł" value={this.state.name} onChange={event => this.setState({name: event.target.value})}/></div>


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
<div className="margin-auto">
                            <div className="block-component"><button className = "zaloguj-button" onClick={() => this.onSave(this.state.name, this.state.description)}>Zapisz</button>
                            </div></div></div>
                    </Modal>
                </div></div>
        );
    }
}

export default NewSessionModal;