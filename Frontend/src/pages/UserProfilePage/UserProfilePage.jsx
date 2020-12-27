import React from "react";
import "../../styles/globalStyles.css";
import userService from "../../services/userService";
import {TextField} from "@material-ui/core";
import {getInfoFromToken, getToken} from "../../services/util";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import "../../styles/userProfile.css";
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import ChangeCredentialsModal from "../../components/ChangeCredentialsModal/ChangeCredentialsModal";


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
            isUsernameChanging: false
        }
    }

    componentDidMount() {
        this.getUser();
    }

    getUser = () => {
        userService.getUserByUsername(this.props.match.params.username)
            .then(r => this.getUserSuccessHandler(r))
    }

    getUserSuccessHandler = response => {
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
                username: this.state.username, //ToDo zmiana uzytkownika LUB
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
            .then(r => console.log(r))
            .catch(e => console.log(e))


        this.setState({
            isPasswordChanging: false,
            isUsernameChanging: false
        })

    }

    onClickEditButton = () => {
        this.setState({isEditingProfile: true})
    }

    render(){
        return (
            <div className = "user-profile-main-div">
            <div className = "page-title"> {this.state.username} </div>

            <div className = "user-profile-container">

{/*                <div>Nazwa użytkownika: <TextField disabled value={this.state.username}/></div> */}

            <div className = "user-profile-block">
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

            <div className = "user-profile-block">
                {this.isProfileLoggedUser() && <div className = "user-profile-container">
                    <button className = "detaleButton" onClick={() => this.setState({isPasswordChanging: true})}>Edytuj hasło</button>
                    <button className = "detaleButton" onClick={() => this.setState({isUsernameChanging: true})}>Edytuj nazwę użytkownika</button>
                    {!this.state.isEditingProfile && <button className = "detaleButton" onClick={this.onClickEditButton}>Edytuj profil</button>}
                    {this.state.isEditingProfile && <button className = "detaleButton" onClick={() => this.saveProfile()}>Zapisz</button>}
                </div>}

                <ChangeCredentialsModal
                title={this.state.isPasswordChanging? "Edytuj hasło": "Edytuj nazwę użytkownika"}
                isOpen={this.state.isPasswordChanging || this.state.isUsernameChanging}
                onRequestClose={() => this.setState({isPasswordChanging: false, isUsernameChanging: false})}
                isUsernameChanging={this.state.isUsernameChanging}
                isPasswordChanging={this.state.isPasswordChanging}
                onSave={this.saveCredentials}
                username={this.state.username}

                />


                <div className = "user-profile-container"><div className = "text">Mail: <TextField disabled value={this.state.mail}/></div></div>

                <div className = "user-profile-container"><div className = "text">Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingProfile} value={this.state.facebook}/></div></div>

                <div className = "user-profile-container"><div className = "text">Discord: <TextField onChange={(event) => this.setState({discord: event.target.value})} disabled={!this.state.isEditingProfile} value={this.state.discord}/></div></div>


</div>
</div>
<div className = "user-profile-container">
<div className = "user-profile-block">
                <div className = "user-profile-subtitle">Lista postaci:</div>
                {/*ToDo get only a first 10 characters/histories on backend, not slice it on frontend*/}
                {this.state.characters && this.state.characters.slice(0,10).map((item, i) => (
                    <div className = "one-element-brief">
                        <div className = "yellow-color">#{item.id}</div>
                        <div className = "user-profile-container"><div className = "yellow-color">Imię: </div>	&nbsp; {item.name}</div>
                        <div className = "user-profile-container"><div className = "yellow-color">Nazwisko:	&nbsp; </div>{item.surname}</div>
                        <div className = "user-profile-container"><div className = "yellow-color">Rasa:	&nbsp; </div>{item.race}</div>
                        <div className = "user-profile-container" ><div className = "yellow-color">Płeć: 	&nbsp;</div>{item.sex}</div>
                        <div className = "user-profile-container"><div className = "yellow-color">Profesja: 	&nbsp;</div>{item.career}</div>
                        <div className = "user-profile-container"><div className = "yellow-color">Miejsce pobytu:	&nbsp; </div>{item.livePlace}</div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                    </div>
                ))
                }

                <Link className="detaleButton" to={fronendUrls.characterList + "/user/" + this.state.username}>Więcej postaci</Link>
</div>
<div className = "user-profile-block">
                <div className = "user-profile-subtitle">Lista historii:</div>
                {this.state.histories && this.state.histories.slice(0,10).map((item, i) => (
                    <div className = "one-element-brief">
                        {/*<div>{item.id}</div>*/}
                        <div className="short-history-title">{item.title}</div>
                        <div>{item.beginDescription}</div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                    </div>
                ))
                }
                <Link className="detaleButton" to={fronendUrls.historyList + "/user/" + this.state.username}>Więcej historii</Link>
</div>
</div>
</div>
        )
    }

}

export default UserProfilePage;