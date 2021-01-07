import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import CloseIcon from "@material-ui/icons/Close";
import {ColorTag} from "../../components/Autocomplete/DefaultMultipleAutocomplete.style";
import {getInfoFromToken, getToken} from "../../services/util";
import NewSessionModal from "../../components/Popup/NewSessionModal/NewSessionModal";


class SessionListPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            sessions: [
                {name: "Sesja1", description: "Przykładowa sesja1"},
                {name: "Sesja2", description: "Przykładowa sesja2"},
                {name: "Sesja3", description: "Przykładowa sesja3"}
            ],
            newSessionModalVisible: false
        }
    }

    isLoggedUser = () => {
        const user = getInfoFromToken(getToken());
        return user && user.sub === this.props.match.params.username
    }

    handleClickNewSession = () => {
        this.setState({newSessionModalVisible: true})
    }

    saveNewSession = (title, description) => {
        this.setState({newSessionModalVisible: false})
        console.log(title);
        console.log(description);
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
                    </div>
                ))}



            </div>
        )
    }

}

export default SessionListPage;