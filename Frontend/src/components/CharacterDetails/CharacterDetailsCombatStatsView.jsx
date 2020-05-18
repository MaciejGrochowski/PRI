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

const test = {
    textAlign: 'center',
    display: 'inline-block',
    paddingTop: '5%',
    verticalAlign: 'middle',
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
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseWeaponSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerWeaponSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endWeaponSkills}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">US</div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseBallisticSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerBallisticSkills}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endBallisticSkills}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">K</div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.baseStrength}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerStrength}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endStrength}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODP</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseToughness}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerToughness}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endToughness}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ZR</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseAgility}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerAgility}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endAgility}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">INT</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseIntelligence}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerIntelligence}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endIntelligence}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SW</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseWillPower}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerWillPower}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endWillPower}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODG</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseFellowship}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerFellowship}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endFellowship}</div></div>
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
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseAttacks}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerAttacks}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endAttacks}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ŻYW</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseWounds}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerWounds}</div></div>
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
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{Math.floor(data.baseToughness/10)}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SZ</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseMovement}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerMovement}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endMovement}</div></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">M</div>
                            <div className = "grid-element"style= {mygrid}><div style ={test}>{data.baseMagic}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.carrerMagic}</div></div>
                            <div className = "grid-element" style= {mygrid}><div style ={test}>{data.endMagic}</div>
                        </div>
                        </div>
                        </div>
                </div>
        
        </div>
        )
    }

}

export default CharacterDetailsCombatStatsView;