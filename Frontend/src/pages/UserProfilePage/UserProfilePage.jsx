import React from "react";
import "../../styles/globalStyles.css";
import userService from "../../services/userService";
import {TextField} from "@material-ui/core";
import {getInfoFromToken, getToken} from "../../services/util";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import "../../styles/userProfile.css";


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

    saveFacebook = () => {
        this.setState({isEditingFacebook: false})
    }

    saveDiscord = () => {
        this.setState({isEditingDiscord: false})
    }

    saveDescription = () => {
        this.setState({isEditingDescription: false})
    }

    render(){
        return (
            <div className = "plainPage">
            <div className = "user-profile-main-div">
            <div className = "page-title"> {this.state.username} </div>

            <div className = "user-profile-container">

{/*                <div>Nazwa użytkownika: <TextField disabled value={this.state.username}/></div> */}

<div className = "user-profile-block">
<div className="user-profile-subtitle">Opis: </div>
<div><TextField onChange={(event) => this.setState({description: event.target.value})} disabled={!this.state.isEditingDescription} value={this.state.description}/></div>
                {this.isProfileLoggedUser() && <div>
                {!this.state.isEditingDescription && <button onClick={() => this.setState({isEditingDescription: true})}>Edytuj</button>}
                    {this.state.isEditingDescription && <button onClick={() => this.saveDescription()}>Zapisz</button>}
                </div>}
</div>
                <div>Mail: <TextField disabled value={this.state.mail}/></div>

                {/*{this.state.isEditingFacebook ?*/}
                {/*    <div>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingFacebook} value={this.state.facebook}/></div> :*/}
                {/*    <div>Facebook: <Link to={this.state.facebook}>{this.state.facebook}</Link></div>*/}
                {/*}*/}
                <div>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingFacebook} value={this.state.facebook}/></div>
                {this.isProfileLoggedUser() && <div>
                {!this.state.isEditingFacebook && <button onClick={() => this.setState({isEditingFacebook: true})}>Edytuj</button>}
                {this.state.isEditingFacebook && <button onClick={() => this.saveFacebook()}>Zapisz</button>}
                </div>}

                <div>Discord: <TextField onChange={(event) => this.setState({discord: event.target.value})} disabled={!this.state.isEditingDiscord} value={this.state.discord}/></div>
                {this.isProfileLoggedUser() && <div>
                {!this.state.isEditingDiscord && <button onClick={() => this.setState({isEditingDiscord: true})}>Edytuj</button>}
                    {this.state.isEditingDiscord && <button onClick={() => this.saveDiscord()}>Zapisz</button>}
                </div>}


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
            </div>
        )
    }

}

export default UserProfilePage;