import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import MenuItem from "@material-ui/core/MenuItem";
import {starSigns} from "../../enums/StarSigns";
import {religions} from "../../enums/Religions";
import grid from "../../styles/grid.css";
import characterService from "../../services/characterService";
import {months} from "../../enums/Months";
import generatorService from "../../services/generatorService";

const mygrid = {
        width: '30px',
        height: "30px",
        border: 'solid 1px white',
        borderRadius: '25%',
        marginBotton: '5%',
        marginTop:'5%',
        textAlign: 'center',
};



class CharacterGeneratorPage extends React.Component {

    constructor() {
        super();
        this.state = {
            autocompleteData: { //To będzie z bazy danych pobierane na podobieństwo filtrów
                careerNames: [],
                placeNames: [],
                eyeColors: [],
                hairColors: [],
                personalityNames: [],
                apperanceNames: [],
                emotionNames: [],
                talentNames: [],
                skillNames: []
            }
        }
    }

    componentDidMount() {
        characterService.getAutocompleteFilters()
            .then(r => this.getAutocompleteSuccessHandler(r))
    }

    getAutocompleteSuccessHandler = response => {
        this.setState({autocompleteData: response.data})
    }

    mapFilterArrayToString = (array, options) => {
        let string = ""
        for (const element in array) {
            let name;
            name = array[element]; //ToDo prawdopodobnie jest lepsza metoda, ale wymaga analizy
            string = string + name + ","
        }
        return string.substring(0, string.length - 1);
    }

    getDataFromForm = () => {
        let output = {};
        const name = document.getElementById('characterGeneratorName');
        if (name && name.value !== "") output = {...output, name: name.value}

        const surname = document.getElementById('characterGeneratorSurname');
        if (surname && surname.value !== "") output = {...output, surname: surname.value}

        const sex = document.getElementById('characterGeneratorSex');
        if (sex && sex.value !== "") output = {...output, sex: sex.value}

        const race = document.getElementById('characterGeneratorRace');
        if (race && race.value !== "") output = {...output, race: race.value}

        const currentCareer = document.getElementById("characterGeneratorCurrentCareer");
        if (currentCareer && currentCareer.value !== "") output = {...output, currentCareer: currentCareer.value}

        const previousCareers = Array.from(document.getElementsByClassName("characterGeneratorPreviousCareers")).map(c => c.textContent);
        if (previousCareers.length > 0) output = {
            ...output,
            previousCareers: this.mapFilterArrayToString(previousCareers, this.state.autocompleteData.careerNames)
        }

        const dayOfBirth = document.getElementById('characterGeneratorDayOfBirth');
        if (dayOfBirth && dayOfBirth.value !== "") output = {...output, dayOfBirth: dayOfBirth.value}

        const monthOfBirth = document.getElementById('characterGeneratorMonthOfBirth');
        if (monthOfBirth && monthOfBirth.value !== "") output = {...output, monthOfBirth: monthOfBirth.value}

        const yearOfBirth = document.getElementById('characterGeneratorYearOfBirth');
        if (yearOfBirth && yearOfBirth.value !== "") output = {...output, yearOfBirth: yearOfBirth.value}

        const height = document.getElementById('characterGeneratorHeight');
        if (height && height.value !== "") output = {...output, height: height.value}
        const weight = document.getElementById('characterGeneratorWeight');
        if (weight && weight.value !== "") output = {...output, weight: weight.value}


        const prediction = document.getElementById('characterGeneratorPrediction');
        if (prediction && prediction.value !== "") output = {...output, prediction: prediction.value};


        const livePlace = document.getElementById("characterGeneratorLivePlace");
        if (livePlace && livePlace.value !== "") output = {...output, livePlace: livePlace.value}

        const birthPlace = document.getElementById("characterGeneratorBirthPlace");
        if (birthPlace && birthPlace.value !== "") output = {...output, birthPlace: birthPlace.value}

        const emotions = Array.from(document.getElementsByClassName("characterGeneratorEmotions")).map(c => c.textContent)
        if (emotions.length > 0) output = {
            ...output,
            dominatingEmotions: this.mapFilterArrayToString(emotions, this.state.autocompleteData.emotionNames)
        }

        const religion = document.getElementById("characterGeneratorReligion");
        if (religion && religion.value !== "") output = {...output, religion: religion.value}

        const skills = Array.from(document.getElementsByClassName("characterGeneratorSkills")).map(c => c.textContent)
        if (skills.length > 0) output = {
            ...output,
            skills: this.mapFilterArrayToString(skills, this.state.autocompleteData.skillNames)
        }

        const talents = Array.from(document.getElementsByClassName("characterGeneratorTalents")).map(c => c.textContent)
        if (talents.length > 0) output = {
            ...output,
            talents: this.mapFilterArrayToString(talents, this.state.autocompleteData.talentNames)
        }

        const personalities = Array.from(document.getElementsByClassName("characterGeneratorPersonality")).map(c => c.textContent)
        if (personalities.length > 0) output = {
            ...output,
            personality: this.mapFilterArrayToString(personalities, this.state.autocompleteData.personalityNames)
        }

        const apperances = Array.from(document.getElementsByClassName("characterGeneratorApperances")).map(c => c.textContent)
        if (apperances.length > 0) output = {
            ...output,
            apperance: this.mapFilterArrayToString(apperances, this.state.apperanceNames)
        }
        const baseWeaponSkills = document.getElementById("characterGeneratorBaseWeaponSkills");
        if (baseWeaponSkills && baseWeaponSkills.value !== "") output = {...output, baseWeaponSkills: baseWeaponSkills.value}
        const endWeaponSkills = document.getElementById("characterGeneratorEndWeaponSkills");
        if (endWeaponSkills && endWeaponSkills.value !== "") output = {...output, endWeaponSkills: endWeaponSkills.value}

        const baseBallisticSkills = document.getElementById("characterGeneratorBaseBallisticSkills");
        if (baseBallisticSkills && baseBallisticSkills.value !== "") output = {...output, baseBallisticSkills: baseBallisticSkills.value}
        const endBallisticSkills = document.getElementById("characterGeneratorEndBallisticSkills");
        if (endBallisticSkills && endBallisticSkills.value !== "") output = {...output, endBallisticSkills: endBallisticSkills.value}
        const baseStrength = document.getElementById("characterGeneratorBaseStrength");
        if (baseStrength && baseStrength.value !== "") output = {...output, baseStrength: baseStrength.value}
        const endStrength = document.getElementById("characterGeneratorEndStrength");
        if (endStrength && endStrength.value !== "") output = {...output, endStrength: endStrength.value}
        const baseToughness = document.getElementById("characterGeneratorBaseToughness");
        if (baseToughness && baseToughness.value !== "") output = {...output, baseToughness: baseToughness.value}
        const endToughness = document.getElementById("characterGeneratorEndToughness");
        if (endToughness && endToughness.value !== "") output = {...output, endToughness: endToughness.value}
        const baseAgility = document.getElementById("characterGeneratorBaseAgility");
        if (baseAgility && baseAgility.value !== "") output = {...output, baseAgility: baseAgility.value}
        const endAgility = document.getElementById("characterGeneratorEndAgility");
        if (endAgility && endAgility.value !== "") output = {...output, endAgility: endAgility.value}
        const baseIntelligence = document.getElementById("characterGeneratorBaseIntelligence");
        if (baseIntelligence && baseIntelligence.value !== "") output = {...output, baseIntelligence: baseIntelligence.value}
        const endIntelligence = document.getElementById("characterGeneratorEndIntelligence");
        if (endIntelligence && endIntelligence.value !== "") output = {...output, endIntelligence: endIntelligence.value}
        const baseWillPower = document.getElementById("characterGeneratorBaseWillPower");
        if (baseWillPower && baseWillPower.value !== "") output = {...output, baseWillPower: baseWillPower.value}
        const endWillPower = document.getElementById("characterGeneratorEndWillPower");
        if (endWillPower && endWillPower.value !== "") output = {...output, endWillPower: endWillPower.value}
        const baseFellowship = document.getElementById("characterGeneratorBaseFellowship");
        if (baseFellowship && baseFellowship.value !== "") output = {...output, baseFellowship: baseFellowship.value}
        const endFellowship = document.getElementById("characterGeneratorEndFellowship");
        if (endFellowship && endFellowship.value !== "") output = {...output, endFellowship: endFellowship.value}
        const baseAttacks = document.getElementById("characterGeneratorBaseAttacks");
        if (baseAttacks && baseAttacks.value !== "") output = {...output, baseAttacks: baseAttacks.value}
        const endAttacks = document.getElementById("characterGeneratorEndAttacks");
        if (endAttacks && endAttacks.value !== "") output = {...output, endAttacks: endAttacks.value}
        const baseWounds = document.getElementById("characterGeneratorBaseWounds");
        if (baseWounds && baseWounds.value !== "") output = {...output, baseWounds: baseWounds.value}
        const endWounds = document.getElementById("characterGeneratorEndWounds");
        if (endWounds && endWounds.value !== "") output = {...output, endWounds: endWounds.value}
        const baseMovement = document.getElementById("characterGeneratorBaseMovement");
        if (baseMovement && baseMovement.value !== "") output = {...output, baseMovement: baseMovement.value}
        const endMovement = document.getElementById("characterGeneratorEndMovement");
        if (endMovement && endMovement.value !== "") output = {...output, endMovement: endMovement.value}
        const baseMagic = document.getElementById("characterGeneratorBaseMagic");
        if (baseMagic && baseMagic.value !== "") output = {...output, baseMagic: baseMagic.value}
        const endMagic = document.getElementById("characterGeneratorEndMagic");
        if (endMagic && endMagic.value !== "") output = {...output, endMagic: endMagic.value}
        console.warn(output);
        return output;

    }


    save = () => {
        let characterInput = this.getDataFromForm();
        generatorService.save(characterInput);
    }


    render() {
        return (
            <div className="pageWithContext">
                <div className="pageName">Tworzenie postaci</div>
                <div className="block-element">
                    <div className="flex-element">
                        <div className="white-caption">Statystyki:</div>
                        <button className="detaleButton">Generuj losowe</button>
                    </div>
                </div>
                <div className="flex-element">
                    <div className="block-element">
                        <div className="flex-element">
                            <div className="generator-element"><TextField label="Imię" id="characterGeneratorName"/>
                            </div>
                            <div className="generator-element"><TextField label="Nazwisko"
                                                                          id="characterGeneratorSurname"/></div>
                        </div>
                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Profesja"
                            // disabled={true}
                            options={this.state.autocompleteData.careerNames || []}
                            id="characterGeneratorCurrentCareer"
                        /></div>
                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Miejsce pobytu"
                            options={this.state.autocompleteData.placeNames || []}
                            id="characterGeneratorLivePlace"
                        /></div>
                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Miejsce urodzenia"
                            options={this.state.autocompleteData.placeNames || []}
                            id="characterGeneratorBirthPlace"
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Rasa"
                            options={["Człowiek", "Elf", "Krasnolud", "Niziołek"]}
                            id="characterGeneratorRace"
                            width={135}
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Płeć"
                            options={["Mężczyzna", "Kobieta"]}
                            id="characterGeneratorSex"
                            width={138}
                        /></div>

                        <div className="flex-element">
                            <div className="generator-element"><TextField label="Dzień urodzenia"
                                                                          id="characterGeneratorDayOfBirth"/></div>


                            <div className="generator-element"><DefaultMultipleAutocomplete
                                labelName="Miesiąc"
                                options={months}
                                id="characterGeneratorMonthOfBirth"
                                width={150}
                            /></div>
                            <div className="generator-element"><TextField label="Rok urodzenia"
                                                                          id="characterGeneratorYearOfBirth"/></div>

                        </div>
                        <div className="flex-element">
                            <div className="generator-element"><TextField label="Wzrost" id="characterGeneratorHeight"/>
                            </div>
                            <div className="generator-element"><TextField label="Waga" id="characterGeneratorWeight"/>
                            </div>
                        </div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Kolor oczu"
                            options={this.state.autocompleteData.eyeColors || []}
                            id="characterGeneratorEyeColor"
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Kolor włosów"
                            options={this.state.autocompleteData.hairColors || []}
                            id="characterGeneratorHairColor"
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Cechy charakteru"
                            options={this.state.autocompleteData.personalityNames || []}
                            id="characterGeneratorPersonality"
                            multiple
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Cechy wyglądu"
                            options={this.state.autocompleteData.apperanceNames || []}
                            id="characterGeneratorApperances"
                            multiple
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Poprzednie profesje"
                            options={this.state.autocompleteData.careerNames || []}
                            id="characterGeneratorPreviousCareers"
                            multiple
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Dominujące emocje"
                            options={this.state.autocompleteData.emotionNames || []}
                            id="characterGeneratorEmotions"
                            multiple
                        /></div>

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Religia"
                            options={religions || []}
                            id="characterGeneratorReligion"
                        /></div>
                        {/*<div className = "generator-element"><div className="ziemniak">*/}
                        {/*    <TextField id="characterGeneratorReligion" defaultValue="0" label="Religia" select>*/}
                        {/*        {religions && religions.map((item, i) => (*/}
                        {/*                <MenuItem value={i}>{item}</MenuItem>*/}
                        {/*            )*/}
                        {/*        )}*/}
                        {/*    </TextField>*/}
                        {/*</div></div>*/}

                        <div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Umiejętności"
                            options={this.state.autocompleteData.skillNames || []}
                            id="characterGeneratorSkills"
                            multiple
                        /></div>

                        <               div className="generator-element"><DefaultMultipleAutocomplete
                            labelName="Zdolności"
                            options={this.state.autocompleteData.talentNames || []}
                            id="characterGeneratorTalents"
                            multiple
                        /></div>

                        <div className="generator-element"><TextField label="Przepowiednia"
                                                                      id="characterGeneratorPrediction"/></div>
                    </div>
                    <div className="block-element">
                        <div className="white-caption">Umiejętności bojowe</div>
                        <div className="block-grid">
                            <div className="grid">
                                <div className="grid-column">
                                    <div className="title-column"> ***</div>
                                    <div className="grid-name-element">Baza</div>
                                    <div className="grid-name-element">Rozwój</div>
                                    <div className="grid-name-element">Obecnie</div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">WW</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseWeaponSkills"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerWeaponSkills"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndWeaponSkills"/>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">US</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseBallisticSkills"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerBallisticSkills"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndBallisticSkills"/>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">K</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseStrength"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerStrength"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndStrength"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ODP</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseToughness"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerWeaponSkills"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndToughness"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ZR</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseAgility"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerAgility"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndAgility"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">INT</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseIntelligence"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerIntelligence"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndIntelligence"/>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SW</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseWillPower"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerWillPower"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndWillPower"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">OGD</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseFellowship"/>
                                    </div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerFellowship"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndFellowship"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="block-grid">
                            <div className="grid">
                                <div className="grid-column">
                                    <div className="title-column"> ***</div>
                                    <div className="grid-name-element">Baza</div>
                                    <div className="grid-name-element">Rozwój</div>
                                    <div className="grid-name-element">Obecnie</div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">A</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseAttacks"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerAttacks"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndAttacks"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ŻYW</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseWounds"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerWounds"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndWounds"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">S</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseStrengthBonus"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerStrengthBonus"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndStrengthBonus"
                                                                             disabled/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">WT</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseToughnessBonus"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerToughnessBonus"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndToughnessBonus"
                                                                             disabled/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SZ</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseMovement"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerMovement"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndMovement"/></div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">M</div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorBaseMagic"/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorCareerMagic"
                                                                             disabled/></div>
                                    <div className="grid-element"><TextField style={mygrid}
                                                                             id="characterGeneratorEndMagic"/></div>
                                </div>
                            </div>
                        </div>
                        <div className="flex-element">
                            <button className="green-button" onClick={this.save}>Zapisz</button>
                            <button className="red-button">Anuluj</button>
                        </div>
            </div>
            </div>
            </div>
        )
    }

}

export default CharacterGeneratorPage;