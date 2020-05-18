import React from "react";
import grid from "../../styles/grid.css";

const mygrid = {
        width: '30px',
        height: "30px",
        border: 'solid 1px white',
        borderRadius: '25%',
        marginBotton: '5%',
        marginTop:'5%',
        textAlign: 'center',
};

class CharacterDetailsCombatStatsView extends React.Component {

    render(){
        const {data, title} = this.props;
        return (
            <div>
                <div className = "sub-title">{title}</div>

                <div className = "block-grid">
                    <div className = "grid">
                        <div className="grid-column">
                            <div className="title-column"> ***</div>
                            <div className = "grid-name-element">Baza</div>
                            <div className = "grid-name-element">Rozwój</div>
                            <div className = "grid-name-element">Obecnie</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">WW</div>
                            <div className = "grid-element" style= {mygrid}>{data.baseWeaponSkills}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerWeaponSkills}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endWeaponSkills}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">US</div>
                            <div className = "grid-element" style= {mygrid}>{data.baseBallisticSkills}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerBallisticSkills}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endBallisticSkills}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">K</div>
                            <div className = "grid-element" style= {mygrid}>{data.baseStrength}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerStrength}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endStrength}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODP</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseToughness}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerToughness}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endToughness}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ZR</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseAgility}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerAgility}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endAgility}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">INT</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseIntelligence}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerIntelligence}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endIntelligence}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SW</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseWillPower}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerWillPower}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endWillPower}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODG</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseFellowship}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerFellowship}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endFellowship}</div>
                        </div>
                        </div>
                    </div>
                    <div className="block-grid">
                    <div className = "grid">
                    <div className="grid-column">
                            <div className="title-column"> ***</div>
                            <div className = "grid-name-element">Baza</div>
                            <div className = "grid-name-element">Rozwój</div>
                            <div className = "grid-name-element">Obecnie</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">A</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseAttacks}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerAttacks}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endAttacks}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ŻYW</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseWounds}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerWounds}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endWounds}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">S</div>
                            <div className = "grid-element"style= {mygrid}>{Math.floor(data.baseStrength/10)}</div>
                            <div className = "grid-element" style= {mygrid}>{"-"}</div>
                            <div className = "grid-element" style= {mygrid}>{Math.floor(data.endStrength/10)}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">WT</div>
                            <div className = "grid-element"style= {mygrid}>{Math.floor(data.baseToughness/10)}</div>
                            <div className = "grid-element" style= {mygrid}>{"-"}</div>
                            <div className = "grid-element" style= {mygrid}>{Math.floor(data.baseToughness/10)}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SZ</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseMovement}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerMovement}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endMovement}</div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">M</div>
                            <div className = "grid-element"style= {mygrid}>{data.baseMagic}</div>
                            <div className = "grid-element" style= {mygrid}>{data.carrerMagic}</div>
                            <div className = "grid-element" style= {mygrid}>{data.endMagic}</div>
                        </div>
                        </div>
                        </div>
                </div>

                

                // <span>CareerWW </span> <span>{data.careerWeaponSkills}</span>
                // <span>CareerUS </span> <span>{data.careerBallisticSkills}</span>
                // <span>CareerK </span> <span>{data.careerStrength}</span>
                // <span>CareerODP </span> <span>{data.careerToughness}</span>
                // <span>CareerZR </span> <span>{data.careerAgility}</span>
                // <span>CareerINT </span> <span>{data.careerIntelligence}</span>
                // <span>CareerSW </span> <span>{data.careerWillPower}</span>
                // <span>CareerOGL </span> <span>{data.careerFellowship}</span>

                // <span>CareerA </span> <span>{data.careerAttacks}</span>
                // <span>CareerZyw </span> <span>{data.careerWounds}</span>
                // <span>CareerSzy </span> <span>{data.careerMovement}</span>
                // <span>CareerMag </span> <span>{data.careerMagic}</span>
        
        )
    }

}

export default CharacterDetailsCombatStatsView;