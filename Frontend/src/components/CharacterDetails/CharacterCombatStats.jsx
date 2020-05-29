import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import CharacterDetailsStatsView from "./CharacterDetailsStatsView";
import CharacterDetailsSkillsView from "./CharacterDetailsSkillsView";
import CharacterDetailsCombatStatsView from "./CharacterDetailsCombatStatsView";
import pageName from "../../styles/page.css";
import "../../styles/CharacterCombatStatsStyle.css";

class CharacterCombatStats extends React.Component{

    render()
    {
        const {characterData} = this.props
        console.log("bekon")
        return (
            <div className = "container-stats">
                <div className = "column">
            <CharacterDetailsCombatStatsView
                title="Umiejętności bojowe"
                data={characterData}
                />
                </div>
            <div className = "column-s">
            <div className = "column">
                <CharacterDetailsSkillsView
                title="Umiejętności"
                data={characterData.skills}
                />
                </div>
                <div className = "column">
                <CharacterDetailsSkillsView
                    title="Zdolności"
                    data={characterData.talents}
                />
                </div>
                </div>
            </div>
            )
    }
}
export default CharacterCombatStats;