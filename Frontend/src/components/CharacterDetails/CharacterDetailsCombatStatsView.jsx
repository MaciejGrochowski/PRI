import React from "react";

class CharacterDetailsCombatStatsView extends React.Component {

    render(){
        const {data, title} = this.props;
        return (
            <div>
                <div>{title}</div>

                <span>BaseWW </span> <span>{data.baseWeaponSkills}</span>
                <span>BaseUS </span> <span>{data.baseBallisticSkills}</span>
                <span>BaseK </span> <span>{data.baseStrength}</span>
                <span>BaseODP </span> <span>{data.baseToughness}</span>
                <span>BaseZR </span> <span>{data.baseAgility}</span>
                <span>BaseINT </span> <span>{data.baseIntelligence}</span>
                <span>BaseSW </span> <span>{data.baseWillPower}</span>
                <span>BaseOGL </span> <span>{data.baseFellowship}</span>
                <span>BaseA </span> <span>{data.baseAttacks}</span>
                <span>BaseZyw </span> <span>{data.baseWounds}</span>
                <span>BaseSzy </span> <span>{data.baseMovement}</span>
                <span>BaseMag </span> <span>{data.baseMagic}</span>


                <span>CareerWW </span> <span>{data.careerWeaponSkills}</span>
                <span>CareerUS </span> <span>{data.careerBallisticSkills}</span>
                <span>CareerK </span> <span>{data.careerStrength}</span>
                <span>CareerODP </span> <span>{data.careerToughness}</span>
                <span>CareerZR </span> <span>{data.careerAgility}</span>
                <span>CareerINT </span> <span>{data.careerIntelligence}</span>
                <span>CareerSW </span> <span>{data.careerWillPower}</span>
                <span>CareerOGL </span> <span>{data.careerFellowship}</span>
                <span>CareerA </span> <span>{data.careerAttacks}</span>
                <span>CareerZyw </span> <span>{data.careerWounds}</span>
                <span>CareerSzy </span> <span>{data.careerMovement}</span>
                <span>CareerMag </span> <span>{data.careerMagic}</span>

                <span>EndWW </span> <span>{data.endWeaponSkills}</span>
                <span>EndUS </span> <span>{data.endBallisticSkills}</span>
                <span>EndK </span> <span>{data.endStrength}</span>
                <span>EndODP </span> <span>{data.endToughness}</span>
                <span>EndZR </span> <span>{data.endAgility}</span>
                <span>EndINT </span> <span>{data.endIntelligence}</span>
                <span>EndSW </span> <span>{data.endWillPower}</span>
                <span>EndOGL </span> <span>{data.endFellowship}</span>
                <span>EndA </span> <span>{data.endAttacks}</span>
                <span>EndZyw </span> <span>{data.endWounds}</span>
                <span>EndSzy </span> <span>{data.endMovement}</span>
                <span>EndMag </span> <span>{data.endMagic}</span>



                ))
                }


            </div>
        )
    }

}

export default CharacterDetailsCombatStatsView;