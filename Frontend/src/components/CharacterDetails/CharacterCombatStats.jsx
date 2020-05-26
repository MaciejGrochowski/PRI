import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import CharacterDetailsStatsView from "./CharacterDetailsStatsView";
import CharacterDetailsSkillsView from "./CharacterDetailsSkillsView";
import CharacterDetailsCombatStatsView from "./CharacterDetailsCombatStatsView";
import pageName from "../../styles/page.css";

class CharacterCombatStats extends React.Component{

    render()
    {
        const {characterData} = this.props
        console.log("bekon")
        return (
            
        <div>
            <div className = "flex-element">
                <div className = "block-element">
            <CharacterDetailsCombatStatsView
                title="Umiejętności bojowe"
                data={characterData}
                />
                </div>
            <div className = "block-element">
                <CharacterDetailsSkillsView
                title="Umiejętności"
                data={characterData.skills}
                />
                <CharacterDetailsSkillsView
                    title="Zdolności"
                    data={characterData.talents}
                />
                </div>
            </div>
        </div>)
    }
}
export default CharacterCombatStats;