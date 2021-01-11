import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import {getInfoFromToken, getToken} from "../../services/util";
import NewSessionModal from "../../components/Popup/NewSessionModal/NewSessionModal";
import sessionService from "../../services/sessionService";
import {fronendUrls} from "../../commons/urls";


class SessionListPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            sessions: [],
            newSessionModalVisible: false
        }
    }

    componentDidMount() {
        this.getSessions();
    }

    getSessions = () => {
        sessionService.getSessionsByUsername(this.props.match.params.username)
            .then(r => this.getSessionsSuccessHandler(r))
            .catch(e => console.log(e))
    }

    getSessionsSuccessHandler = response => {
        this.setState({sessions: response.data})
    }

    isLoggedUser = () => {
        const user = getInfoFromToken(getToken());
        return user && user.sub === this.props.match.params.username
    }

    handleClickNewSession = () => {
        this.setState({newSessionModalVisible: true})
    }

    saveNewSession = (name, description) => {
        this.setState({newSessionModalVisible: false})
        sessionService.createSession(name, description)
            .then(r => this.getSessions())
    }


    render() {
        return (
            <div className="pageWithContext">
                <div className="pageName">Sesje użytkownika {this.props.match.params.username}</div>

                <div className="globalStyles">

                    <div className="flex-container-session">
                        <div>
                            {this.state.sessions && this.state.sessions.map((item, i) => (
                                <div className="flex-container-session">
                                    <div className="session-title">
                                        <span>{item.name}</span>
                                    </div>
                                    <div>
                                        <span>{item.lastModifiedDate}</span>
                                    </div>
                                    {/*<span>{item.description}</span><br/>*/}
                                    {/*<span>{item.createdUserBy}</span><br/>*/}
                                    {/*<span>{item.createdDate}</span><br/>*/}
                                    {/*<span>{item.lastModifiedDate}</span>*/}
                                    {/*<Link to={fronendUrls.sessionDetails + "/" + item.hashcode}>Więcej</Link>*/}
                                </div>
                            ))}
                        </div>
                        <div className="flex-button">
                            {this.isLoggedUser() &&
                            <button className="sessionButton" onClick={this.handleClickNewSession}>Nowa sesja</button>}

                            <NewSessionModal
                                title={"Nowa sesja"}
                                isOpen={this.state.newSessionModalVisible}
                                onRequestClose={() => this.setState({newSessionModalVisible: false})}
                                onSave={this.saveNewSession}
                            />
                        </div>

                    </div>
                </div>
            </div>
        )
    }

}

export default SessionListPage;