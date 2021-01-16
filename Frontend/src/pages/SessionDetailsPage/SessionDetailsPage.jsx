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
import characterService from "../../services/characterService";


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
            isGlobalVisibleChanging: false,
            location: ""

        }
    }

    componentDidMount() {
        this.getSessionDetails();
        this.setState({location: window.location.href})
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
            createdDate: new Date(data.createdDate).toString().substr(0,10),
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

    saveCharacterVisibility = data => {
        sessionService.setCharacterVisibility(this.props.match.params.hashcode, data, this.state.characterVisibleChangingId)
            .then(r => this.saveCharacterVisibilitySuccessHandler(r))
            .catch(e => console.log(e));
    }

    saveCharacterVisibilitySuccessHandler = response => {
        this.setState({isCharacterVisibleChanging: false, characterVisibleChangingId: undefined})
        this.getSessionDetails();
    }

    saveGlobalVisibilitySuccessHandler = response => {
        this.setState({isGlobalVisibleChanging: false})
        this.getSessionDetails();
    }

    changeVisibilityCharacter = async characterId => {
        await this.setState({isCharacterVisibleChanging: true, characterVisibleChangingId: characterId})
    }

    getCharacterFromId = characterId => {
        return this.state.characters.filter(c => c.id === characterId)[0];
    }

    getCharacterNameFromId = characterId => {
        let character = this.getCharacterFromId(characterId);
        let output = ""
        if(character && character.name) {
            output += character.name;
            if(character.surname) output += " " + character.surname;
            return output;
        }
        else {
            return "o nieznanym imieniu.";
        }

    }

    render() {
        return (
            <div className="pageWithContext">
                {/*ToDo this should be in more smaller components, not in one giant*/}

                <div className="pageName">{this.state.name}</div>

                <div className="flex-container-session">
                    <div className="session-detail-info">
                        <div className="flex-container-session">
                            <div className="info-container-tittle">
                                Nazwa sesji:<br/>
                                <TextField fullWidth="true"
                                           onChange={(event) => this.setState({name: event.target.value})}
                                           disabled={!this.state.isChanging} value={this.state.name}/>
                            </div>
                            <div className="info-container">
                                Autor sesji:<br/> <TextField disabled={true} value={this.state.createdBy}/>
                            </div>
                            <div className="info-container">
                                Data stworzenia:<br/> <TextField disabled={true} value={this.state.createdDate}/>
                            </div>
                        </div>
                    </div>
                    <div className="session-detail-button">
                        {this.isMG() && (this.state.isChanging ?
                            <button className="sessionSaveButton addSessionButton" onClick={this.editSession}>Zapisz
                                zmiany</button> :
                            <button className="sessionButton addSessionButton"
                                    onClick={() => this.setState({isChanging: true})}>Edytuj sesję</button>)}

                        {this.isMG() && <button className="sessionButton"
                                                onClick={() => this.setState({isGlobalVisibleChanging: true})}>Globalna
                            widoczność</button>}

                        <ChangeVisibilityModal
                            title={"Edytuj globalną widoczność postaci"}
                            isOpen={this.state.isGlobalVisibleChanging}
                            onRequestClose={() => this.setState({isGlobalVisibleChanging: false})}
                            onSave={this.saveGlobalVisibility}
                            isGlobal={true}
                        />
                    </div>
                </div>
                <div className="flex-container-session">

                    <div className="">
                        Opis sesji:<br/>
                        <TextField fullWidth={true} variant={"outlined"}  rows={4} rowsMax={4} multiline
                                   onChange={(event) => this.setState({description: event.target.value})}
                                   disabled={!this.state.isChanging} value={this.state.description}/>

                    </div>
                </div>
                    <span>Link do udostępnienia: <Link to={fronendUrls.sessionDetails + "/" + this.props.match.params.hashcode }>{this.state.location}</Link></span>


                    <div className="flex-container-session-2">
                        {this.state.characters && this.state.characters.map((character, i) => (
                            <div className="character-box">
                                <CharacterSessionView
                                    character={character}
                                    isMG={this.isMG()}
                                    onDeleteCharacter={this.deleteCharacter}
                                    onChangeVisibilityClick={this.changeVisibilityCharacter}
                                />
                            </div>
                        ))}
                    </div>
                <ChangeVisibilityModal
                    title={"Edytuj widoczność postaci " + this.getCharacterNameFromId(this.state.characterVisibleChangingId)}
                    isOpen={this.state.isCharacterVisibleChanging}
                    onRequestClose={() => this.setState({isCharacterVisibleChanging: false})}
                    onSave={this.saveCharacterVisibility}
                    isGlobal={false}
                    initialValues={this.getCharacterFromId(this.state.characterVisibleChangingId)}
                />
                </div>

                )
                }

                }

                export default SessionDetailsPage;