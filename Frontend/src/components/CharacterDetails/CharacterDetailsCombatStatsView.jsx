import React from "react";
import grid from "../../styles/grid.css";
import "../../styles/CharacterCombatStatsStyle.css";

const mygrid = {
        width: '35px',
        height: "30px",
        border: 'solid 1px white',
        borderRadius: '25%',
        marginBotton: '5%',
        marginTop:'5%',
        textAlign: 'center',
};

const test = {
    textAlign: 'center',
    display: 'inline-block',
    paddingTop: '10%',
    fontSize: 'medium',
    verticalAlign: 'middle',
};

class CharacterDetailsCombatStatsView extends React.Component {

    render(){
        const {data, title} = this.props;
        return (
            <div className = "block-stats-element">
                <div className = "sub-title">{title}</div>
                <div className="block-grid">
                    <div className = "grid">
                        <div className="grid-column">
                            <div className="title-column"> ***</div>
                            <div className = "grid-name-element">Baza</div>
                            <div className = "grid-name-element">Rozwój</div>
                            <div className = "grid-name-element">Obecnie</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">WW</div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseWeaponSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerWeaponSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endWeaponSkills}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">US</div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseBallisticSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerBallisticSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endBallisticSkills}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">K</div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseStrength}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerStrength}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endStrength}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODP</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseToughness}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerToughness}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endToughness}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ZR</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseAgility}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerAgility}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endAgility}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">INT</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseIntelligence}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerIntelligence}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endIntelligence}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SW</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseWillPower}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerWillPower}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endWillPower}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODG</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseFellowship}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerFellowship}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endFellowship}</div></div>
                        </div>
                    </div>
                    <div className = "grid">
                    <div className="grid-column">
                            <div className="title-column"> ***</div>
                            <div className = "grid-name-element">Baza</div>
                            <div className = "grid-name-element">Rozwój</div>
                            <div className = "grid-name-element">Obecnie</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">A</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseAttacks}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerAttacks}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endAttacks}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ŻYW</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseWounds}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerWounds}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endWounds}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">S</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{Math.floor(data.baseStrength/10)}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{"-"}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{Math.floor(data.endStrength/10)}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">WT</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{Math.floor(data.baseToughness/10)}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{"-"}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{Math.floor(data.endToughness/10)}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SZ</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseMovement}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerMovement}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endMovement}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">M</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseMagic}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.careerMagic}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endMagic}</div>
                        </div>
                        </div>
                        <div className = "fake-element"/>
                        </div>
                        </div>
                </div>

        )
    }

}

export default CharacterDetailsCombatStatsView;