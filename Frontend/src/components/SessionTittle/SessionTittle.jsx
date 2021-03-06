import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import {getInfoFromToken, getToken} from "../../services/util";
import NewSessionModal from "../Popup/NewSessionModal/NewSessionModal";
import {fronendUrls} from "../../commons/urls";

class SessionTittle extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    shorterDate(date) {
        date = new Date(date);
        return date.getDate().toString() + "-" + ("0" + (date.getMonth() + 1)).slice(-2).toString() + "-" + date.getFullYear().toString();
    }

    render() {
        let actualModDate = this.shorterDate(this.props.date);
        return (
            <div className="flex-container-session">
                <div className="session-title">
                    {this.props.name}
                </div>
                <div className="last-modified">
                    Ostatnia modyfikacja: {actualModDate}
                </div>
            </div>
        )
    }
}

export default SessionTittle;