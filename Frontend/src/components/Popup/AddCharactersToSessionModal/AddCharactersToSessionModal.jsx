import React from 'react';
import Modal from 'react-modal';
import test from '../../../styles/popup.css';
import button from "../../../styles/buttons.css";
import popup from "../../../styles/popup.css";
import DefaultMultipleAutocomplete from "../../Autocomplete/DefaultMultipleAutocomplete";
import sessionService from "../../../services/sessionService";
import {InputLabel, MenuItem, NativeSelect, Select} from "@material-ui/core";

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
        zIndex: '100!important'
    },
    overlay:{
        backgroundColor: 'rgba(63, 63, 63, 0.75)'
    }
};

class AddCharactersToSessionModal extends React.Component {

    constructor() {
        super();
        this.state = {
            sessions: [],
            selected: undefined
        }

    }


    componentDidMount() {
        this.getSessionNames();
    }

    componentDidUpdate(prevProps,) {
        if(!prevProps.isOpen && this.props.isOpen){
            this.getSessionNames();
        }
    }


    getSessionNames = () => {
        sessionService.getSessionsByUsername(this.props.username)
            .then(r => this.getSessionNamesSuccessHandler(r))
            .catch(e => console.log(e))
    }

    getSessionNamesSuccessHandler = response => {
        this.setState({sessions: response.data})
    }

    handleChange = (event) => {
        this.setState({selected: event.target.value})
    }

    render() {
        const {isOpen, onSave, onRequestClose} = this.props;



        return (
            <div className = "customize-button">
                <Modal
                    isOpen={isOpen}
                    onRequestClose={() => onRequestClose()}
                    style={customStyles}
                >
                    <div className="pageNameSessionAdd">Dodanie postaci do sesji</div>
                    <div className="textUpToSelect">Wybierz sesję do której chcesz dodać wybrane postacie.<br/></div>

                    <div className="session-select">
                    <Select id={"session"} value={this.state.selected} native onChange={event => this.handleChange(event)}>
                        <option value=""/>
                        {this.state.sessions && this.state.sessions.map(s => (
                            <option value={s.id}>{s.name}</option>
                        ))}
                    </Select>
                    </div>


                    <div className="flex-center-element">
                        <button disabled={!this.state.selected} type="submit" className="detaleButton" onClick={() => onSave(this.state.selected)}>Dodaj wybrane postacie do sesji</button>
                    </div>
                </Modal>
            </div>

        );
    }
}

export default AddCharactersToSessionModal;