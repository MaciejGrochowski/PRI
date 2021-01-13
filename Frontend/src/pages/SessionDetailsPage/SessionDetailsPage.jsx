import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import sessionService from "../../services/sessionService";
import CharacterSessionView from "../../components/CharacterSessionView/CharacterSessionView";
import {getInfoFromToken, getToken} from "../../services/util";
import {fronendUrls} from "../../commons/urls";
import {TextField} from "@material-ui/core";
import ChangeCredentialsModal from "../../components/Popup/ChangeCredentialsModal/ChangeCredentialsModal";
import ChangeVisibilityModal
    from "../../components/Popup/ChangeGlobalVisibilityModal/ChangeVisibilityModal";


class SessionDetailsPage extends React.Component {

    constructor() {
        super();
        this.state = {
            characters: [],
            title: "",
            description: "",
            createdBy: "",
            createdDate: "",
            isChanging: false,
            isGlobalVisibleChanging: false

        }
    }

    componentDidMount() {
        this.getSessionDetails();
    }

    getSessionDetails = () => {
        sessionService.getSessionDetails(this.props.match.params.hashcode)
            .then(r => this.getSessionDetailsSuccessHandler(r))
            .catch(e => console.log(e))
    }

    getSessionDetailsSuccessHandler = response => {
        const data = response.data;
        this.setState({
            characters: data.characters,
            createdBy: data.createdBy,
            createdDate: data.createdDate,
            description: data.description,
            name: data.name
        })

    }

    isMG() {
        let user = getInfoFromToken(getToken());
        let username = ""
        if (user) username = user.sub;
        return username === this.state.createdBy;
    }


    editSession = () => {
        this.setState({isChanging: false});
        sessionService.editSession(this.props.match.params.hashcode, this.state.name, this.state.description)
            .then(r => console.log(r))
            .catch(e => console.log(e))
    }

    deleteCharacter = characterId => {
        sessionService.deleteCharacterFromSession(this.props.match.params.hashcode, characterId)
            .then(r => this.getSessionDetails())
            .catch(e => console.log(e))
    }

    saveGlobalVisibility = data => {
        sessionService.setGlobalVisibility(this.props.match.params.hashcode, data)
            .then(r => this.saveGlobalVisibilitySuccessHandler(r))
            .catch(e => console.log(e))
    }

    saveGlobalVisibilitySuccessHandler = response => {
        this.setState({isGlobalVisibleChanging: false})
        this.getSessionDetails();
    }

    render() {
        return (
            <div className="pageWithContext">
                {/*ToDo this should be in more smaller components, not in one giant*/}

                <div className="pageName">{this.state.name}</div>

                <div className="flex-container-session">

                    <div className="">
                    Nazwa sesji:<br/>
                        <TextField
                            onChange={(event) => this.setState({name: event.target.value})}
                            disabled={!this.state.isChanging} value={this.state.name}/>
                        <br/><br/>
                        Autor sesji:<br/>
                        <TextField disabled={true} value={this.state.createdBy}/>
                    </div>
                    <div className="">
                            Opis sesji:<br/>
                            <TextField fullWidth={true} variant={"outlined"}
                                onChange={(event) => this.setState({description: event.target.value})}
                                disabled={!this.state.isChanging} value={this.state.description}/>

                    </div>
                    <div className="">
                        {this.isMG() && (this.state.isChanging ?
                            <button onClick={this.editSession}>Zapisz zmiany</button> :
                            <button onClick={() => this.setState({isChanging: true})}>Edytuj sesję</button>)}

                        {this.isMG() && <button onClick={() => this.setState({isGlobalVisibleChanging: true})}>Globalna
                            widoczność</button>}

                        <ChangeVisibilityModal
                            title={"Edytuj globalną widoczność postaci"}
                            isOpen={this.state.isGlobalVisibleChanging}
                            onRequestClose={() => this.setState({isGlobalVisibleChanging: false})}
                            onSave={this.saveGlobalVisibility}
                            isGlobal={true}
                        />

                        <p>{this.state.createdBy}</p>
                        <p>{this.state.createdDate}</p>
                        </div>
                </div>
                    <span>Link do udostępnienia: <Link to={window.location.href}>{window.location.href}</Link></span>

                <div className="character-box-list">
                    {this.state.characters && this.state.characters.map((character, i) => (
                        <CharacterSessionView
                            character={character}
                            isMG={this.isMG()}
                            onDeleteCharacter={this.deleteCharacter}/>
                    ))}
                </div>
            </div>

    )
    }

    }

    export default SessionDetailsPage;