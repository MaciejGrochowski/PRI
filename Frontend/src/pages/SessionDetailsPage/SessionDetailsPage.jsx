import React from "react";
import {Link} from "react-router-dom";
import "../../styles/globalStyles.css";
import sessionService from "../../services/sessionService";
import CharacterSessionView from "../../components/CharacterSessionView/CharacterSessionView";
import {getInfoFromToken, getToken} from "../../services/util";
import {fronendUrls} from "../../commons/urls";


class SessionDetailsPage extends React.Component {

    constructor() {
        super();
        this.state = {
            characters: [],
            title: "",
            description: "",
            createdBy: "",
            createdDate: ""

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
        console.log(data)

        this.setState({
            characters: data.characters,
            createdBy: data.createdBy,
            createdDate: data.createdDate,
            description: data.description,
            name: data.name
        })

    }

    isMG(){
        let user = getInfoFromToken(getToken());
        let username = ""
        if(user) username=user.sub;
        return username===this.state.createdBy;
    }

    render(){
        return (
            <div className = "plainPage">
                <h1>{this.state.name}</h1>
                <p>{this.state.description}</p>
                <p>{this.state.createdBy}</p>
                <p>{this.state.createdDate}</p>

                <div>Link do udostępnienia: {fronendUrls.sessionDetails + "/" + this.props.match.params.hashcode}</div>

                {this.state.characters && this.state.characters.map((character, i) => (
                    <CharacterSessionView
                        character={character}
                        isMg={this.isMG()}

                    />
                ))}



            </div>
        )
    }

}

export default SessionDetailsPage;