import React from 'react';
import Modal from 'react-modal';
import test from '../../../styles/popup.css';
import button from "../../../styles/buttons.css";
import popup from "../../../styles/popup.css";
import historyService from "../../../services/historyService";
import parse from "html-react-parser";

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
    }
};

class HistoryDetailsPopup extends React.Component {

    constructor() {
        super();
        this.state = {
            historyDay: null,
            historyMonth: null,
            historyYear: null,
            historyPlace: null,
            historyCreator: "tmpUserName",
            historyDescription: null
        }
    }


    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.historyId !== this.props.historyId){
            if(this.props.historyId === 0) this.setState({historyDay: null, historyMonth: null, historyYear: null, historyPlace: null, historyCreator: "tmpUserName", historyDescription: null})
            if(this.props.historyId > 0) this.getHistoryDetails(this.props.historyId);
        }
    }

    getHistoryDetails = historyId => {
        console.warn(historyId);
        historyService.getHistoryDetails(historyId)
            .then(r => this.getHistoryDetailsSuccessHandler(r))
            .catch(e => this.getHistoryDetailsErrorHandler(e))
    }

    getHistoryDetailsErrorHandler = error => {
        console.log(error);
    }

    getHistoryDetailsSuccessHandler = response => {
        const data = response.data;
        this.setState({
            historyDay: data.day,
            historyMonth: data.month,
            historyYear: data.year,
            historyPlace: data.place,
            historyCreator: data.creator,
            historyDescription: data.description
        })
    }

    render() {
        const {isOpen, onRequestClose} = this.props;


        return (
            <div className = "dostosuj-button">
                {/*ToDo class 'dostosuj-button' should have name 'customize-button'*/}
                <Modal
                    isOpen={isOpen}
                    // onAfterOpen={() => console.log("open")}
                    onRequestClose={() => onRequestClose()}
                    style={customStyles}
                >
                    <div>Data Historii: {this.state.historyDay} {this.state.historyMonth} {this.state.historyYear}</div>
                    <div>Miejsce Historii: {this.state.historyPlace}</div>
                    <div>Twórca Historii: {this.state.historyCreator}</div>
                    <div>Opis Historii: {this.state.historyDescription && parse(this.state.historyDescription)}</div>
                </Modal>
            </div>
        );
    }
}

export default HistoryDetailsPopup;