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
        return (
            <div className = "container-stats" id={"statisticContainer"}>
                <div className = "column" id={"stats"}>
            <CharacterDetailsCombatStatsView
                title="Umiejętności bojowe"
                data={characterData}
                />
                </div>
            <div className = "column-s">
            <div className = "column" id={"stats"}>
                <CharacterDetailsSkillsView
                title="Umiejętności"
                data={characterData.skills}
                />
                </div>
                <div className = "column" id={"stats"}>
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