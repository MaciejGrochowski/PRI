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
        this.getSessions();
        sessionService.createSession(name, description);
    }


    render(){
        return (
            <div className = "plainPage">
                <div>Sesje użytkownika {this.props.match.params.username}</div>

                {this.isLoggedUser && <button onClick={this.handleClickNewSession}>Nowa sesja</button>}

                <NewSessionModal
                    title={"Nowa sesja"}
                    isOpen={this.state.newSessionModalVisible}
                    onRequestClose={() => this.setState({newSessionModalVisible: false})}
                    onSave={this.saveNewSession}
                />


                {this.state.sessions && this.state.sessions.map((item, i) => (
                    <div>
                        <span>{item.name}</span>
                        <span>{item.description}</span>
                        <span>{item.createdUserBy}</span>
                        <span>{item.createdDate}</span>
                        <span>{item.lastModifieKdDate}</span>
                        <Link to={fronendUrls.sessionDetails + "/" + item.id}>Więcej</Link>
                    </div>
                ))}

            </div>
        )
    }

}

export default SessionListPage;