import React from "react";
import "../../styles/globalStyles.css";
import userService from "../../services/userService";
import {TextField} from "@material-ui/core";
import {getInfoFromToken, getToken, logoutCookie} from "../../services/util";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import "../../styles/userProfile.css";
import ChangeCredentialsModal from "../../components/Popup/ChangeCredentialsModal/ChangeCredentialsModal";
import loginService from "../../services/loginService";
import {loginStatusChange} from "../../actions";
import {connect} from "react-redux";
import {Redirect} from "react-router";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope} from "@fortawesome/free-solid-svg-icons";
import{faFacebook, faDiscord} from "@fortawesome/free-brands-svg-icons";
import {polishCodeErrors} from "../../commons/texts-pl";
import {validationDiscord, validationFacebook} from "../RegisterPage/validation";

const mail = <FontAwesomeIcon icon={faEnvelope}/>
const fb = <FontAwesomeIcon icon={faFacebook}/>
const discord = <FontAwesomeIcon icon={faDiscord}/>

class UserProfilePage extends React.Component {

    constructor(){
        super();
        this.state = {
            facebook: "",
            discord: "",
            username: "",
            mail: "",
            description: "",
            characters: [],
            histories: [],
            sessions: [],
            isPasswordChanging: false,
            isUsernameChanging: false,
            userDoesntExist: false
        }
    }

    componentDidMount() {
        this.getUser();
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(this.props.match.params.username !== prevProps.match.params.username){
            this.getUser();
        }
    }

    getUser = () => {
        userService.getUserByUsername(this.props.match.params.username)
            .then(r => this.getUserSuccessHandler(r))
            .catch(e => this.setState({userDoesntExist: true}))
    }


    getUserSuccessHandler = response => {
        for (let item of response.data.histories) {
            let tmp = item.beginDescription;
            while (tmp.match('@')) {
                tmp = tmp.replace('@', '');
            }
            while (tmp.match(/#\d+/g)) {
                tmp = tmp.replace(/#\d+/g, '');
            }
            item.beginDescription = tmp;
        }
        this.setState({
            facebook: response.data.facebook,
            discord: response.data.discord,
            username: response.data.username,
            mail: response.data.mail,
            description: response.data.description,
            characters: response.data.characters,
            histories: response.data.histories,
            sessions: response.data.sessions,
        })
    }


    isProfileLoggedUser = () => {
        let tokenInfo = getInfoFromToken(getToken());
        return tokenInfo ? getInfoFromToken(getToken()).sub === this.props.match.params.username : false;
    }

    saveProfile = () => {
        let input = {
            facebook: this.state.facebook,
            discord: this.state.discord,
            description: this.state.description
        }
        userService.editProfile(input)
            .then(r => console.log(r))
            .catch(e => console.log(e))
        this.setState({isEditingProfile: false});
    }

    saveCredentials = (username, password, newPassword) => {
        let input;
        if(newPassword){
            input = {
                username: this.state.username,
                oldPassword: password,
                newPassword: newPassword
            }
        }
        if(username !== this.state.username){
            input = {
                username: username,
                oldPassword: password,
                newPassword: password
            }
        }

        userService.editCredentials(input)
            .then(r => this.logout())
            .catch(e => this.editCredentialsErrorHandler(e))
    }

    editCredentialsErrorHandler = error => {
        this.setState({errorCode: error.response.data})
    }

    logout = () => {
        loginService.logout(getToken())
            .then(r => {
                this.props.loginStatusChange(false);
                this.setState({
                    isPasswordChanging: false,
                    isUsernameChanging: false,
                    usernameOrPasswordChanged: true
                })
                logoutCookie();
            })
            .catch(e => {
                this.props.loginStatusChange(false);
                this.setState({
                    isPasswordChanging: false,
                    isUsernameChanging: false,
                    usernameOrPasswordChanged: true
                })
                logoutCookie();
            })


    }

    shorterDate(data){
        let date = new Date(data)
        return date.getDate().toString() + "-" + ("0" + (date.getMonth() + 1)).slice(-2).toString() + "-" + date.getFullYear().toString();
    }

    onClickEditButton = () => {
        this.setState({isEditingProfile: true})
    }

    isDisabledEditProfileButton = () => {
        return validationFacebook(this.state.facebook || "").errorState || validationDiscord(this.state.discord || "").errorState
    }

    render(){

        if (this.state.usernameOrPasswordChanged) {
            return <Redirect push to={fronendUrls.mainPage} />
        }

        if(this.state.userDoesntExist){
            return <div className = "error-message">{polishCodeErrors.USER_DOESNT_EXIST}</div>
        }

        return (
            <div className = "user-profile-main-div">
            <div className = "page-title"> {this.state.username} </div>

                {this.isProfileLoggedUser() && <div className = "flex-element">
                    <button className = "detaleButton buttonOnProfile" onClick={() => this.setState({isPasswordChanging: true})}>Edytuj hasło</button>
                    <button className = "detaleButton buttonOnProfile" onClick={() => this.setState({isUsernameChanging: true})}>Edytuj nazwę użytkownika</button>
                    {!this.state.isEditingProfile && <button className = "detaleButton buttonOnProfile" onClick={this.onClickEditButton}>Edytuj profil</button>}
                    {this.state.isEditingProfile && <button className = "detaleButton buttonOnProfile" disabled={this.isDisabledEditProfileButton()} onClick={() => this.saveProfile()}>Zapisz</button>}
                </div>}

            <div className = "user-profile-container">


            <div className = "user-profile-block min-width-50">
            {/*<div className="user-profile-subtitle">Opis: </div>*/}
            <div>       <TextField
                        id="outlined-textarea"
                        label="Opis"
                        placeholder="Brak opisu."
                        rows={10}
                        rowsMax={10}
                        fullWidth
                        multiline
                        variant="outlined"
                            onChange={(event) => this.setState({description: event.target.value})} disabled={!this.state.isEditingProfile} value={this.state.description}/></div>
            </div>
<div className="mail-fb-discord-center">
            <div className = "mail-fb-dc-component">


                <div className = "user-profile-container"><div className = "text"><span>{mail}</span> <div className="text-2"><a href={"mailto:" + this.state.mail}>Wyślij maila</a></div></div></div>

                <div className = "user-profile-container"><div className = "text"><span>{fb}</span>
                    {(this.state.isEditingProfile || !this.state.facebook || this.state.facebook==="") ? <TextField
                    onChange={(event) => this.setState({facebook: event.target.value})}
                    disabled={!this.state.isEditingProfile}
                    helperText = {validationFacebook(this.state.facebook || "").errorText}
                    error={validationFacebook(this.state.facebook || "").errorState}
                    value={this.state.facebook}/>

                    :
                        <div className="text-2"><a href={this.state.facebook}>Profil na facebook</a></div>}

                    </div></div>

                <div className = "user-profile-container"><div className = "text"><span>{discord}</span> <div className="text-2"><TextField
                        onChange={(event) => this.setState({discord: event.target.value})}
                        disabled={!this.state.isEditingProfile}
                        value={this.state.discord}
                        helperText = {validationDiscord(this.state.discord || "").errorText}
                        error={validationDiscord(this.state.discord || "").errorState}

                    /></div></div></div>

            </div>
</div>


                <ChangeCredentialsModal
                title={this.state.isPasswordChanging? "Edytuj hasło": "Edytuj nazwę użytkownika"}
                isOpen={this.state.isPasswordChanging || this.state.isUsernameChanging}
                onRequestClose={() => this.setState({isPasswordChanging: false, isUsernameChanging: false, errorCode: ""})}
                isUsernameChanging={this.state.isUsernameChanging}
                isPasswordChanging={this.state.isPasswordChanging}
                onSave={this.saveCredentials}
                username={this.state.username}
                errorCode={this.state.errorCode}

                />


</div>
<div className = "user-profile-block">
                <div className = "user-profile-subtitle">Prowadzone sesje:</div>

    {this.state.sessions && this.state.sessions.reverse().slice(0,5).map((item, i) => (
        <div className = "one-element-brief">
            <div className = "user-profile-container-s"><div className = "yellow-color">Data stworzenia: </div>	&nbsp; {this.shorterDate(item.createdDate)}</div>
            <div className = "user-profile-container-s"><div className = "yellow-color">Data modyfikacji: </div>	&nbsp; {this.shorterDate(item.lastModifiedDate)}</div>
            <div className="short-history-title">
                {item.name.length > 64 ?
                    item.name.substring(0,64) + "..." : item.name }</div>
            <div>{item.description.substring(0, 1500)}{item.description.length > 1500 &&<>...</>}</div>
        </div>
    ))
    }
    {this.state.sessions.length > 0 ? <Link className="detaleButton" to={fronendUrls.sessionList + "/" + this.state.username}>Więcej sesji</Link> : <div style={{color: "white"}}>Ten użytkownik nie stworzył jeszcze sesji.</div>}
</div>

<div className = "user-profile-container">
<div className = "user-profile-block">
                <div className = "user-profile-subtitle">Lista postaci:</div>
                {/*ToDo get only a first 10 characters/histories on backend, not slice it on frontend*/}
                {this.state.characters && this.state.characters.reverse().slice(0,5).map((item, i) => (
                    <div className = "one-element-brief" id={"profileColumns"}>
                        <div className="column" id={"profileColumn"}>
                        <div className = "yellow-color">#{item.id}</div>
                        <div className = "user-profile-container-s"><div className = "yellow-color">Imię: </div></div>
                        <div className = "user-profile-container-s"><div className = "yellow-color">Nazwisko:</div></div>
                        <div className = "user-profile-container-s"><div className = "yellow-color">Rasa:</div></div>
                        <div className = "user-profile-container-s" ><div className = "yellow-color">Płeć:</div></div>
                        <div className = "user-profile-container-s"><div className = "yellow-color">Profesja:</div></div>
                        <div className = "user-profile-container-s"><div className = "yellow-color">Miejsce pobytu:</div></div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                        </div>
                        <div className="column" id={"profileColumn"}>
                            <div>{item.name}</div>
                            {item.surname && <div>{item.surname}</div>}
                            {!item.surname && <div><br/></div>}
                            <div>{item.race}</div>
                            <div>{item.sex}</div>
                            <div>{item.career}</div>
                            <div>{item.livePlace}</div>
                        </div>
                    </div>
                ))
                }

    {this.state.characters.length > 0 ? <Link className="detaleButton" to={fronendUrls.characterList + "/user/" + this.state.username}>Więcej postaci</Link> : <div style={{color: "white"}}>Ten użytkownik nie stworzył jeszcze postaci.</div>}
</div>
<div className = "user-profile-block">
                <div className = "user-profile-subtitle">Lista historii:</div>
                {this.state.histories && this.state.histories.reverse().slice(0,5).map((item, i) => (
                    <div className = "one-element-brief">
                        {/*<div>{item.id}</div>*/}
                        <div className="short-history-title">{item.title}</div>
                        <div>{item.beginDescription.substring(0, 250)}{item.beginDescription.length >= 250 && <>...</>}</div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                    </div>
                ))
                }
    {this.state.histories.length > 0 ? <Link className="detaleButton" to={fronendUrls.historyList + "/user/" + this.state.username}>Więcej historii</Link> : <div style={{color: "white"}}>Ten użytkownik nie stworzył jeszcze historii.</div>}


</div>
</div>
</div>
        )
    }

}


const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)

export default UserProfilePage = connect(mapStateToProps, mapDispatchToProps)(UserProfilePage);