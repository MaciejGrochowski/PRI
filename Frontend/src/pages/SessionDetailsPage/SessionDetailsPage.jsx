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
import {polishCodeErrors} from "../../commons/texts-pl";
import {CopyToClipboard} from "react-copy-to-clipboard/lib/Component";
import Modal from "react-modal";


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
    overlay: {
        backgroundColor: 'rgba(63, 63, 63, 0.75)'
    }
};

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
            location: "",
            errorName: false,
            errorNameText: "",
            copying: false

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
        const date = new Date(data.createdDate)
        this.setState({
            characters: data.characters,
            createdBy: data.createdBy,
            createdDate: date.getDate().toString() + "-" + ("0" + (date.getMonth() + 1)).slice(-2).toString() + "-" + date.getFullYear().toString(),
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
            // .then(r => console.log(r))
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
        if (character && character.name) {
            output += character.name;
            if (character.surname) output += " " + character.surname;
            return output;
        } else {
            return "o nieznanym imieniu.";
        }
    }

    handleChangeName = name => {
        if (!name || name === "" || name.length > 128) {
            this.setState({errorName: true, errorNameText: polishCodeErrors.NO_EMPTY_SESSION_NAME})
        } else {
            this.setState({name: name})
        }
    }

    onCopy = () => this.setState({copying: false})

    render() {
        return (
            <div className="pageWithContext">
                {/*ToDo this should be in more smaller components, not in one giant*/}

                <div className="pageName">{this.state.name}</div>

                <div className="flex-container-session" id={"width-max"}>
                    <div className="session-detail-info">
                        <div className="flex-container-session" id={"width-max"}>
                            <div className="info-container-tittle">
                                Nazwa sesji:<br/>
                                <TextField fullWidth="true"
                                           onChange={(event) => this.handleChangeName(event.target.value)}
                                           disabled={!this.state.isChanging}
                                           error={this.state.errorName}
                                           helperText={this.state.errorNameText}
                                           value={this.state.name}/>
                            </div>
                            <div className="info-container-tittle" id={"box-01"}>
                                Autor sesji:<br/> <TextField disabled={true} value={this.state.createdBy} fullWidth={true}/>
                            </div>
                            <div className="info-container-tittle" id={"box-02"}>
                                Data stworzenia:<br/> <TextField disabled={true} value={this.state.createdDate} fullWidth={true}/>
                            </div>
                        </div>
                    </div>
                    {this.isMG() && <div className="session-detail-button">
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


                        {this.isMG() &&
                        <button onClick={() => this.setState({copying: true})} className="sessionButton">Kopiuj link do
                            udostępniania</button>}
                        {this.state.copying &&
                        <Modal
                            isOpen={this.state.copying}
                            onRequestClose={() => this.setState({copying: false})}
                            style={customStyles}
                        ><>
                            <div className="container-login-page">
                                <div className="margin-login-body">
                                    <div className="login-title">Skopjuj link udostepnienia sesji</div>
                                    <div className="flex"><code className="code-message">{this.state.location}</code>
                                        <CopyToClipboard text={this.state.location} onCopy={this.onCopy}>
                                            <button className="detaleButton" style={{marginBottom: "auto"}}>Kopiuj
                                            </button>
                                        </CopyToClipboard>
                                    </div>
                                </div>
                            </div>
                        </>

                        </Modal>

                        }
                    </div>}
                </div>
                <div className="flex-container-session" id={"width-max"}>

                    <div className="">
                        Opis sesji:<br/>
                        <TextField fullWidth={true} variant={"outlined"} rows={4} rowsMax={4} multiline
                                   onChange={(event) => this.setState({description: event.target.value})}
                                   disabled={!this.state.isChanging} value={this.state.description}/>

                    </div>
                </div>
                {/*<span>Link do udostępnienia: <Link to={fronendUrls.sessionDetails + "/" + this.props.match.params.hashcode }>{this.state.location}</Link></span>*/}


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