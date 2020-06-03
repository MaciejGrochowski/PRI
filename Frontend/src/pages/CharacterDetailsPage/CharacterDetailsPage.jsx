import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import CharacterDetailsStatsView from "../../components/CharacterDetails/CharacterDetailsStatsView";
import CharacterDetailsSkillsView from "../../components/CharacterDetails/CharacterDetailsSkillsView";
import CharacterDetailsCombatStatsView from "../../components/CharacterDetails/CharacterDetailsCombatStatsView";
import pageName from "../../styles/page.css";
import CharacterCombatStats from "../../components/CharacterDetails/CharacterCombatStats";

class CharacterDetailsPage extends React.Component {

    constructor() {
        super();
        this.state = {
            isVisibleGlobalStats: true,
            isVisibleCombatStats: false,
            isVisibleHistory: false,
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

    changeVisibleState = (global, combat, history ) => {

        this.setState({isVisibleGlobalStats: global, isVisibleCombatStats: combat, isVisibleHistory: history})
    }


    render(){
        return (
            <div className = "globalStyles">
                <div className = "pageWithContext">
            <div className = "pageName">{this.state.characterData.name + " " + this.state.characterData.surname} </div>
            <div className="stats-button-element">
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(true, false, false)}>Statystyki Ogólne</button>
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(false, true, false)}>Statystyki Bojowe</button>
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(false, false, true)} disabled>Historie</button>
                </div>
            <div className = "block-element">
            {this.state.isVisibleGlobalStats &&
                <CharacterDetailsStatsView
                title="Statystyki"
                data={this.state.characterData}
                />
            }
            {this.state.isVisibleCombatStats &&
                <div>
                <CharacterCombatStats characterData = {this.state.characterData}/>
                </div>
            }
            {this.state.isVisibleHistory &&
                <div className = "historie">History Maker!</div>
            }
            </div>
            </div>
            </div>
        )
    }

}

export default CharacterDetailsPage;