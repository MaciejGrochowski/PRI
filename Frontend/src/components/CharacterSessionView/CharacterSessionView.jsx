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
                    <div className="flex-container-session-2">
                        <div className="short-info">

                            <div className="flex-container-session-2">
                {character.race && <div className="inside-character-1">Rasa: </div>} {character.race && <div className="inside-character-2">{character.race}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.sex && <div className="inside-character-1">Płeć: </div>} {character.sex && <div className="inside-character-2">{character.sex}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.currentCareer && <div className="inside-character-1">Profesja: </div>} {character.currentCareer && <div className="inside-character-2">{character.currentCareer}</div>}
                            </div>
                {character.livePlace && <div>Miejsce pobytu: </div>}

                {character.birthPlace && <div>Miejsce urodzenia: </div>}

                {character.dayOfBirth && <div>Dzień urodzenia: </div>}

                {character.monthOfBirth && <div>Miesiąc urodzenia: </div>}

                {character.yearOfBird && <div>Rok urodzenia: </div>}

                {character.religion && <div>Religia: </div>}

                {character.height && <div>Wzrost: </div>}

                {character.weight && <div>Waga: </div>}

                {character.eyeColor && <div>Kolor oczu: </div>}

                {character.hairColor && <div>Kolor włosów: </div>}
                    </div>

                        {/*<div className="short-info">*/}
                        {/*    {character.race && <div>{character.race}</div>}*/}

                        {/*    {character.sex && <div>{character.sex}</div>}*/}

                        {/*    {character.currentCareer && <div>{character.currentCareer}</div>}*/}

                        {/*    {character.livePlace && <div>{character.livePlace}</div>}*/}

                        {/*    {character.birthPlace && <div>{character.birthPlace}</div>}*/}

                        {/*    {character.dayOfBirth && <div>{character.dayOfBirth}</div>}*/}

                        {/*    {character.monthOfBirth && <div>{character.monthOfBirth}</div>}*/}

                        {/*    {character.yearOfBird && <div>{character.yearOfBird}</div>}*/}

                        {/*    {character.religion && <div>{character.religion}</div>}*/}

                        {/*    {character.height && <div>{character.height}</div>}*/}

                        {/*    {character.weight && <div>{character.weight}</div>}*/}

                        {/*    {character.eyeColor && <div>{character.eyeColor}</div>}*/}

                        {/*    {character.hairColor && <div>{character.hairColor}</div>}*/}
                        {/*</div>*/}

                    </div>
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