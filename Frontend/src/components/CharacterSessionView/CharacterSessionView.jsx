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
                {character.race && <div className="inside-character-1">Rasa: </div>}
                {character.race && <div className="inside-character-2">{character.race}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.sex && <div className="inside-character-1">Płeć: </div>}
                {character.sex && <div className="inside-character-2">{character.sex}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.currentCareer && <div className="inside-character-1">Profesja: </div>}
                {character.currentCareer && <div className="inside-character-2">{character.currentCareer}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.livePlace && <div className="inside-character-1">Miejsce pobytu: </div>}
                {character.livePlace && <div className="inside-character-2">{character.livePlace}</div>}

                            </div>
                            <div className="flex-container-session-2">
                {character.birthPlace && <div className="inside-character-1">Miejsce urodzenia: </div>}
                {character.birthPlace && <div className="inside-character-2">{character.birthPlace}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.dayOfBirth && <div className="inside-character-1">Dzień urodzenia: </div>}
                {character.dayOfBirth && <div className="inside-character-2">{character.dayOfBirth}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.monthOfBirth && <div className="inside-character-1">Miesiąc urodzenia: </div>}
                {character.monthOfBirth && <div className="inside-character-2">{character.monthOfBirth}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.yearOfBird && <div className="inside-character-1">Rok urodzenia: </div>}
                {character.yearOfBird && <div className="inside-character-2">{character.yearOfBird}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.religion && <div className="inside-character-1">Religia: </div>}
                {character.religion && <div className="inside-character-2">{character.religion}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.height && <div className="inside-character-1">Wzrost: </div>}
                {character.height && <div className="inside-character-2">{character.height}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.weight && <div className="inside-character-1">Waga: </div>}
                {character.weight && <div className="inside-character-2">{character.weight}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.eyeColor && <div className="inside-character-1">Kolor oczu: </div>}
                {character.eyeColor && <div className="inside-character-2">{character.eyeColor}</div>}
                            </div>

                            <div className="flex-container-session-2">
                {character.hairColor && <div className="inside-character-1">Kolor włosów: </div>}
                {character.hairColor && <div className="inside-character-2">{character.hairColor}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.personality && <div className="inside-character-3">Cechy charakteru</div>}
                                {character.personality && <div className="inside-character-4">{character.personality}</div>}
                            </div>


                            <div className="flex-container-session-2">
                                {character.apperance && <div className="inside-character-3">Cechy wyglądu</div>}
                                {character.personality && <div className="inside-character-4">{character.apperance}</div>}
                            </div>
                    </div>


                    </div>


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