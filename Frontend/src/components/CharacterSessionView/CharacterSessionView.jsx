import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class CharacterSessionView extends React.Component {

    render(){
        const {character, isMG, onDeleteCharacter, onChangeVisibilityClick} = this.props;

        return (
            <div className="full-character-box">
                {/*ToDo refactor (as other columnConfigs)*/}

                <div className="character-name">{character.name || character.surname ? character.name + (character.surname ? " " + character.surname: "") : "Nieznana"}</div>

                <div className="character-information">
                {character.race && <div>Rasa: {character.race}</div>}

                {character.sex && <div>Płeć: {character.sex}</div>}

                {character.currentCareer && <div>Profesja: {character.currentCareer}</div>}

                {character.livePlace && <div>Miejsce pobytu: {character.livePlace}</div>}

                {character.birthPlace && <div>Miejsce urodzenia: {character.birthPlace}</div>}

                {character.dayOfBirth && <div>Dzień urodzenia: {character.dayOfBirth}</div>}

                {character.monthOfBirth && <div>Miesiąc urodzenia: {character.monthOfBirth}</div>}

                {character.yearOfBird && <div>Rok urodzenia: {character.yearOfBird}</div>}

                {character.religion && <div>Religia: {character.religion}</div>}

                {character.height && <div>Wzrost: {character.height}</div>}

                {character.weight && <div>Waga: {character.weight}</div>}

                {character.eyeColor && <div>Kolor oczu: {character.eyeColor}</div>}

                {character.hairColor && <div>Kolor włosów: {character.hairColor}</div>}

                {character.personality && <div>Cechy charakteru: {character.personality}</div>}

                {character.apperance && <div>Cechy wyglądu: {character.apperance}</div>}

                {character.previousCareers && <div>Poprzednie profesje: {character.previousCareers}</div>}

                {character.dominatingEmotions && <div>Dominujące emocje: {character.dominatingEmotions}</div>}

                {character.skills && <div>Umiejętności: {character.skills}</div>}

                {character.talents && <div>Zdolności: {character.talents}</div>}

                {character.prediction && <div>Przepowiednia: {character.prediction}</div>}

                {character.starSign && <div>Znak gwiezdny: {character.starSign}</div>}

                {character.endWeaponSkills && <div>WW: {character.endWeaponSkills}</div>}

                {character.endBallisticSkills && <div>US: {character.endBallisticSkills}</div>}

                {character.endStrength && <div>K: {character.endStrength}</div>}

                {character.endToughness && <div>ODP: {character.endToughness}</div>}

                {character.endAgility && <div>ZR: {character.endAgility}</div>}

                {character.endIntelligence && <div>INT: {character.endIntelligence}</div>}

                {character.endWillPower && <div>SW: {character.endWillPower}</div>}

                {character.endFellowship && <div>OGD: {character.endFellowship}</div>}

                {character.endAttacks && <div>A: {character.endAttacks}</div>}

                {character.endWounds && <div>ŻYW: {character.endWounds}</div>}

                {character.endMagic && <div>MAG: {character.endMagic}</div>}

                {character.endMovement && <div>SZ: {character.endMovement}</div>}


                {isMG && <Link to={fronendUrls.characterDetails + "/" + character.id}>Więcej</Link>}
                {isMG && <button onClick={() => onDeleteCharacter(character.id)}>Usuń postać</button>}
                {isMG && <button onClick={() => onChangeVisibilityClick(character.id)}>Zmień widoczność cech</button>}
                </div>


            </div>


        )
    }

}

export default CharacterSessionView;