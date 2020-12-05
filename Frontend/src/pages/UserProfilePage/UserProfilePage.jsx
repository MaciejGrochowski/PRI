import React from "react";
import "../../styles/globalStyles.css";
import userService from "../../services/userService";
import {TextField} from "@material-ui/core";
import {getInfoFromToken, getToken} from "../../services/util";
import {Link} from "react-router-dom";


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
        return getInfoFromToken(getToken()) === this.props.match.params.username;
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

                <span>Nazwa użytkownika: <TextField disabled value={this.state.username}/></span>

                <span>Mail: <TextField disabled value={this.state.mail}/></span>

                {/*{this.state.isEditingFacebook ?*/}
                {/*    <span>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingFacebook} value={this.state.facebook}/></span> :*/}
                {/*    <span>Facebook: <Link to={this.state.facebook}>{this.state.facebook}</Link></span>*/}
                {/*}*/}
                <span>Facebook: <TextField onChange={(event) => this.setState({facebook: event.target.value})} disabled={!this.state.isEditingFacebook} value={this.state.facebook}/></span>
                {this.isProfileLoggedUser() && <span>
                {!this.state.isEditingFacebook && <button onClick={() => this.setState({isEditingFacebook: true})}>Edytuj</button>}
                {this.state.isEditingFacebook && <button onClick={() => this.saveFacebook()}>Zapisz</button>}
                </span>}

                <span>Discord: <TextField onChange={(event) => this.setState({discord: event.target.value})} disabled={!this.state.isEditingDiscord} value={this.state.discord}/></span>
                {this.isProfileLoggedUser() && <span>
                {!this.state.isEditingDiscord && <button onClick={() => this.setState({isEditingDiscord: true})}>Edytuj</button>}
                    {this.state.isEditingDiscord && <button onClick={() => this.saveDiscord()}>Zapisz</button>}
                </span>}

                <span>Opis: <TextField onChange={(event) => this.setState({description: event.target.value})} disabled={!this.state.isEditingDescription} value={this.state.description}/></span>
                {this.isProfileLoggedUser() && <span>
                {!this.state.isEditingDescription && <button onClick={() => this.setState({isEditingDescription: true})}>Edytuj</button>}
                    {this.state.isEditingDescription && <button onClick={() => this.saveDescription()}>Zapisz</button>}
                </span>}

            </div>
        )
    }

}

export default UserProfilePage;