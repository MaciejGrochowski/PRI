import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import CharacterDetailsStatsView from "../../components/CharacterDetails/CharacterDetailsStatsView";
import CharacterDetailsSkillsView from "../../components/CharacterDetails/CharacterDetailsSkillsView";
import CharacterDetailsCombatStatsView from "../../components/CharacterDetails/CharacterDetailsCombatStatsView";
import pageName from "../../styles/page.css";
import CharacterCombatStats from "../../components/CharacterDetails/CharacterCombatStats";
import CharacterDetailsHistoryView from "../../components/CharacterDetails/CharacterDetailsHistoryView";
import historyService from "../../services/historyService";
import {fronendUrls} from "../../commons/urls";

class CharacterDetailsPage extends React.Component {

    constructor() {
        super();
        this.state = {
            isVisibleGlobalStats: true,
            isVisibleCombatStats: false,
            isVisibleHistory: false,
            characterData: {},
            historyData: [],
            characterTag: ""
        }
    }


    componentDidMount() {
        const id = this.getCharacterId();
        if(id){
            this.getCharacterById(id)
        }
    }

    getCharacterId = () => { //ToDo refactor with react-router
        const tmp = window.location.pathname.split("/");
        if(Number.isInteger(parseInt(tmp[tmp.length-1]))){
            return parseInt(tmp[tmp.length-1])
        }
        return undefined;
    }

    getCharacterById = async id => {
        await characterService.getCharacterById(id)
            .then(response => this.getCharacterByIdSuccessHandler(response))
        await historyService.getHistoriesByCharacter(this.state.characterData.name + (this.state.characterData.surname ? "-" + this.state.characterData.surname : ""), id)
            .then(response =>this.getHistoriesByCharacterSuccessHandler(response))
    }

    getHistoriesByCharacterSuccessHandler = response => {
        this.setState({historyData: response.data})
    }

    getCharacterByIdSuccessHandler = response => {
        this.setState({characterData: response.data})
    }

    changeVisibleState = (global, combat, history ) => {

        this.setState({isVisibleGlobalStats: global, isVisibleCombatStats: combat, isVisibleHistory: history})
    }

    onGetMoreHistoriesClick = () => {
        let characterTag = this.state.characterData.name + (this.state.characterData.surname ? "-" + this.state.characterData.surname : "");
        let id = this.getCharacterId();
        window.location.assign(fronendUrls.historyList + "/character/" + characterTag + "/" + id);
    }
    
    getLinkToMoreHistories = () => {
        let characterTag = this.state.characterData.name + (this.state.characterData.surname ? "-" + this.state.characterData.surname : "");
        let id = this.getCharacterId();
        return fronendUrls.historyList + "/character/" + characterTag + "/" + id;
    }


    render(){
        return (
            <div className = "globalStyles">
                <div className = "pageWithContext">
            <div className = "pageName">{this.state.characterData.name + (this.state.characterData.surname ? " " + this.state.characterData.surname : "") +"#" + this.getCharacterId()} </div>
            <div className="stats-button-element">
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(true, false, false)}>Statystyki Ogólne</button>
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(false, true, false)}>Statystyki Bojowe</button>
                <button className = "detailsTypeButton" onClick = {() => this.changeVisibleState(false, false, true)}>Historie</button>
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
                <CharacterDetailsHistoryView historyData={this.state.historyData} onGetMoreHistoriesClick={this.onGetMoreHistoriesClick} getLinkToMoreHistories={this.getLinkToMoreHistories} />
            }
            </div>
            </div>
            </div>
        )
    }

}

export default CharacterDetailsPage;