import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import {getInfoFromToken, getToken} from "../../services/util";
import NewSessionModal from "../../components/Popup/NewSessionModal/NewSessionModal";
import {fronendUrls} from "../../commons/urls";

class SessionTittle extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    shorterDate(date) {
        let short = date.substring(0, 10)
        return short
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