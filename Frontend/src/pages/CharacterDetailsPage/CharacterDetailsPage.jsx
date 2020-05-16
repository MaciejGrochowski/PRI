import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import CharacterDetailsStatsView from "../../components/CharacterDetails/CharacterDetailsStatsView";
import CharacterDetailsSkillsView from "../../components/CharacterDetails/CharacterDetailsSkillsView";
import CharacterDetailsCombatStatsView from "../../components/CharacterDetails/CharacterDetailsCombatStatsView";
import pageName from "../../styles/page.css"

class CharacterDetailsPage extends React.Component {

    constructor() {
        super();
        this.state = {
            characterData: {}
        }
    }


    componentDidMount() {
        const id = this.getCharacterId();
        if(id){
            this.getCharacterById(id)
        }
    }

    getCharacterId = () => {
        const tmp = window.location.pathname.split("/");
        if(Number.isInteger(parseInt(tmp[tmp.length-1]))){
            return parseInt(tmp[tmp.length-1])
        }
        return undefined;
    }

    getCharacterById = id => {
        characterService.getCharacterById(id)
            .then(response => this.getCharacterByIdSuccessHandler(response))
    }

    getCharacterByIdSuccessHandler = response => {
        this.setState({characterData: response.data})
    }


    render(){
        return (
            <div className = "plainPage">
            <div className = "pageName">{this.state.characterData.name + " " + this.state.characterData.surname} </div>
            <div className = "flex-element">
            <div className = "block-element">
                <CharacterDetailsStatsView
                title="Statystyki"
                data={this.state.characterData}
                />
                </div>
            <div className = "block-element">
                <CharacterDetailsSkillsView
                title="Umiejętności"
                data={this.state.characterData.skills}
                />

                <CharacterDetailsSkillsView
                    title="Zdolności"
                    data={this.state.characterData.talents}
                />

                <CharacterDetailsCombatStatsView
                title="Umiejętności bojowe"
                data={this.state.characterData}

                />
            </div>
            </div>
            </div>
        )
    }

}

export default CharacterDetailsPage;