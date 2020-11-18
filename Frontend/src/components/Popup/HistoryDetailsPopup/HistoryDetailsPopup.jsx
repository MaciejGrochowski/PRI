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
        const {isOpen, onRequestClose, changeHistoryToNext, isPreviousButtonHidden, isNextButtonHidden} = this.props;


        return (
            <div className = "customize-button">
            <div className = "popup-body">
                <Modal
                    isOpen={isOpen}
                    onRequestClose={() => onRequestClose()}
                    style={customStyles}
                >
                    <button hidden={isPreviousButtonHidden} onClick={() => changeHistoryToNext(this.props.historyId, false)}>Poprzednia</button>
                    <button hidden={isNextButtonHidden} onClick={() => changeHistoryToNext(this.props.historyId, true)}>Następna</button>
                    <div className = "flex-div"><div className = "yellow-color"> Data Historii:&nbsp;</div> {this.state.historyDay} {this.state.historyMonth} {this.state.historyYear}</div>
                    <div className = "flex-div"><div className = "yellow-color">Miejsce Historii:&nbsp; </div> {this.state.historyPlace}</div>
                    <div className = "flex-div"><div className = "yellow-color">Twórca Historii:&nbsp; </div> {this.state.historyCreator}</div>
                    <div><div className = "yellow-color">Opis Historii:&nbsp; </div>{this.state.historyDescription && parse(this.state.historyDescription)}</div>
                </Modal>
            </div></div>
        );
    }
}

export default HistoryDetailsPopup;