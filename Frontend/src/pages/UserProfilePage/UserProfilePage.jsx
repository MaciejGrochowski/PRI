import React from "react";
import "../../styles/globalStyles.css";
import userService from "../../services/userService";
import {TextField} from "@material-ui/core";
import {getInfoFromToken, getToken} from "../../services/util";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import "../../styles/userProfile.css";
import TextareaAutosize from '@material-ui/core/TextareaAutosize';


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
            isEditingDiscord: false,
            isEditingFacebook: false,
            isEditingDescription: false
        }
    }

    componentDidMount() {
        console.log(this.props.match.params.username);
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
            sessions: response.data.sessions
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
                {this.isProfileLoggedUser() && <div>
                    {!this.state.isEditingProfile && <button onClick={this.onClickEditButton}>Edytuj użytkownika</button>}
                    {this.state.isEditingProfile && <button onClick={() => this.saveProfile()}>Zapisz</button>}
                </div>}


                <div>Mail: <TextField disabled value={this.state.mail}/></div>

                {/*{this.state.isEditingFacebook ?*/}
                {/*    <div>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingFacebook} value={this.state.facebook}/></div> :*/}
                {/*    <div>Facebook: <Link to={this.state.facebook}>{this.state.facebook}</Link></div>*/}
                {/*}*/}
                <div>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingProfile} value={this.state.facebook}/></div>

                <div>Discord: <TextField onChange={(event) => this.setState({discord: event.target.value})} disabled={!this.state.isEditingProfile} value={this.state.discord}/></div>


</div>
</div>
                Lista postaci:
                {this.state.characters && this.state.characters.map((item, i) => (
                    <div className = "one-history-brief">
                        <div>{item.id}</div>
                        <div>{item.name}</div>
                        <div>{item.surname}</div>
                        <div>{item.career}</div>
                        <div>{item.livePlace}</div>
                        <div>{item.race}</div>
                        <div>{item.sex}</div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                    </div>
                ))
                }

                Lista historii:
                {this.state.histories && this.state.histories.map((item, i) => (
                    <div className = "one-history-brief">
                        <div>{item.id}</div>
                        <div>{item.beginDescription}</div>
                        {/*<Link className = "detaleButton" to={fronendUrls.historyList + "/" + item.id}><div className = "normal-text">Więcej</div></Link>*/}
                    </div>
                ))
                }
</div>
        )
    }

}

export default UserProfilePage;