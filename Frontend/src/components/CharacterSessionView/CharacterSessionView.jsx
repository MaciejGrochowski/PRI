import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import DeleteIcon from '@material-ui/icons/Delete';
import VisibilityIcon from '@material-ui/icons/Visibility';
import PersonIcon from '@material-ui/icons/Person';


class CharacterSessionView extends React.Component {

    render() {
        const {character, isMG, onDeleteCharacter, onChangeVisibilityClick} = this.props;
        return (
            <div className="full-character-box">
                {/*ToDo refactor (as other columnConfigs)*/}

                <div
                    className="character-name">{character.name || character.surname ? character.name + (character.surname ? " " + character.surname : "") : "Nieznana"}</div>

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
                                {character.currentCareer &&
                                <div className="inside-character-2">{character.currentCareer}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.livePlace && <div className="inside-character-1">Miejsce pobytu: </div>}
                                {character.livePlace && <div className="inside-character-2">{character.livePlace}</div>}

                            </div>
                            <div className="flex-container-session-2">
                                {character.birthPlace && <div className="inside-character-1">Miejsce urodzenia: </div>}
                                {character.birthPlace &&
                                <div className="inside-character-2">{character.birthPlace}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.dayOfBirth && <div className="inside-character-1">Dzień urodzenia: </div>}
                                {character.dayOfBirth &&
                                <div className="inside-character-2">{character.dayOfBirth}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.monthOfBirth &&
                                <div className="inside-character-1">Miesiąc urodzenia: </div>}
                                {character.monthOfBirth &&
                                <div className="inside-character-2">{character.monthOfBirth}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.yearOfBirth && <div className="inside-character-1">Rok urodzenia: </div>}
                                {character.yearOfBirth &&
                                <div className="inside-character-2">{character.yearOfBirth}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.starSign && <div className="inside-character-1">Znak gwiezdny: </div>}
                                {character.starSign && <div className="inside-character-2">{character.starSign}</div>}
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
                                {character.apperance && <div className="inside-character-4">{character.apperance}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.previousCareers && <div className="inside-character-3">Poprzednie profesje</div>}
                                {character.previousCareers && <div className="inside-character-4">{character.previousCareers}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.dominatingEmotions && <div className="inside-character-3">Dominujące emocje</div>}
                                {character.dominatingEmotions && <div className="inside-character-4">{character.dominatingEmotions}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.skills && <div className="inside-character-3">Umiejętności</div>}
                                {character.skills && <div className="inside-character-4">{character.skills}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.talents && <div className="inside-character-3">Zdolności</div>}
                                {character.talents && <div className="inside-character-4">{character.talents}</div>}
                            </div>

                            <div className="flex-container-session-2">
                                {character.prediction && <div className="inside-character-3">Przepowiednia</div>}
                                {character.prediction && <div className="inside-character-4">{character.prediction}</div>}
                            </div>


                            {(character.endWeaponSkills || character.endBallisticSkills || character.endStrength|| character.endToughness
                                || character.endAgility|| character.endIntelligence|| character.endWillPower|| character.endFellowship
                                || character.endAttacks|| character.endWounds|| character.endMagic|| character.endMovement) &&
                            <div className="flex-container-session-3">
                                <div className="inside-character-6">Statystyki</div>

                                {character.endWeaponSkills && <div className="character-statistic">
                                    <div className="inside-character-5">WW</div>
                                    <div className="inside-character-5">{character.endWeaponSkills}</div>
                                </div>}

                                {character.endBallisticSkills && <div className="character-statistic">
                                    <div className="inside-character-5">US</div>
                                    <div className="inside-character-5">{character.endBallisticSkills}</div>
                                </div>}

                                {character.endStrength && <div className="character-statistic">
                                    <div className="inside-character-5">K</div>
                                    <div className="inside-character-5">{character.endStrength}</div>
                                </div>}

                                {character.endToughness && <div className="character-statistic">
                                    <div className="inside-character-5">ODP</div>
                                    <div className="inside-character-5">{character.endToughness}</div>
                                </div>}

                                {character.endAgility &&  <div className="character-statistic">
                                   <div className="inside-character-5">ZR</div>
                                   <div className="inside-character-5">{character.endAgility}</div>
                                </div>}

                                {character.endIntelligence && <div className="character-statistic">
                                    <div className="inside-character-5">INT</div>
                                    <div className="inside-character-5">{character.endIntelligence}</div>
                                </div>}

                                {character.endWillPower && <div className="character-statistic">
                                    <div className="inside-character-5">SW</div>
                                    <div className="inside-character-5">{character.endWillPower}</div>
                                </div>}

                                {character.endFellowship && <div className="character-statistic">
                                    <div className="inside-character-5">OGD</div>
                                    <div className="inside-character-5">{character.endFellowship}</div>
                                </div>}

                                {character.endAttacks && <div className="character-statistic">
                                    <div className="inside-character-5">A</div>
                                    <div className="inside-character-5">{character.endAttacks}</div>
                                </div>}

                                {character.endWounds && <div className="character-statistic">
                                    <div className="inside-character-5">ŻYW</div>
                                    <div className="inside-character-5">{character.endWounds}</div>
                                </div>}


                                {(character.endMagic || character.endMagic === 0) && <div className="character-statistic">
                                    <div className="inside-character-5">MAG</div>
                                    <div className="inside-character-5">{character.endMagic}</div>
                                </div>}

                                {character.endMovement && <div className="character-statistic">
                                    <div className="inside-character-5">SZ</div>
                                    <div className="inside-character-5">{character.endMovement}</div>
                                </div>}
                            </div>}

                        </div>
                    </div>

                    {isMG &&
                    <div className="character-buttons">
                        <div className="inside-buttons">
                        {isMG && <Link className="more-of-character" to={fronendUrls.characterDetails + "/" + character.id}><PersonIcon fontSize={"large"}/></Link>}
                        {isMG && <DeleteIcon className="delete-character" fontSize={"large"} onClick={() => onDeleteCharacter(character.id)}/>}
                        {isMG && <VisibilityIcon className="delete-character" fontSize={"large"} onClick={() => onChangeVisibilityClick(character.id)}/>}
                        </div>
                    </div>}
                </div>


            </div>


        )
    }

}

export default CharacterSessionView;