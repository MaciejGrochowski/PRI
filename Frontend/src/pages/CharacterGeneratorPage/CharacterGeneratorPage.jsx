import React from "react";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import {religions} from "../../enums/Religions";
import characterService from "../../services/characterService";
import {months} from "../../enums/Months";
import generatorService from "../../services/generatorService";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSyncAlt} from '@fortawesome/free-solid-svg-icons';
import ErrorGenerator from "../../components/ErrorLayout/ErrorGenerator";
import {careerContext} from "./context";
import GeneratorTextField from "../../components/Generator/GeneratorTextField";
import {textsPolish} from "../../commons/texts-pl";
import {
    fullRandomGenerateSuccessHandler,
    generateOneAttributeSuccessHandler,
    mapArrayToStringWithoutSpaces,
    mapFilterArrayToString,
    mapSkillsArrayToString,
    mapTalentsArrayToString
} from "./util";

//TODO refactor 
const mygrid = {
    all: 'none',
    width: '30px',
    height: "30px",
    border: 'solid 1px white',
    borderRadius: '25%',
    marginBotton: '5%',
    marginTop: '5%',
    textAlign: 'center',
};
const element = <FontAwesomeIcon icon={faSyncAlt}/>
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
            },
            fullGenerated: {}
        }
    }

    componentDidMount() {
        characterService.getAutocompleteFilters()
            .then(r => this.getAutocompleteSuccessHandler(r))
    }

    getAutocompleteSuccessHandler = response => {
        this.setState({autocompleteData: response.data})
    }

    getDataFromForm = () => {
        let output = {
            currentCareer: this.state.currentCareer,
            sex: this.state.sex,
            race: this.state.race,
            previousCareers: Array.isArray(this.state.previousCareers) ? mapFilterArrayToString(this.state.previousCareers) : this.state.previousCareers,
            monthOfBirth: this.state.monthOfBirth,
            eyeColor: this.state.eyeColor,
            hairColor: this.state.hairColor,
            livePlace: this.state.livePlace,
            birthPlace: this.state.birthPlace,
            dominatingEmotions: Array.isArray(this.state.dominatingEmotions) ? this.mapFilterArrayToString(this.state.dominatingEmotions) : this.state.dominatingEmotions,
            religion: this.state.religion,
            skills: Array.isArray(this.state.skills) ? mapArrayToStringWithoutSpaces(this.state.skills) : this.state.skills,
            talents: Array.isArray(this.state.talents) ? mapArrayToStringWithoutSpaces(this.state.talents) : this.state.talents,
            apperance: Array.isArray(this.state.apperances) ? mapFilterArrayToString(this.state.apperances) : this.state.apperances,
            personality: Array.isArray(this.state.personalities) ? mapFilterArrayToString(this.state.personalities) : this.state.personalities,
            name: this.state.name, surname: this.state.surname, dayOfBirth: this.state.dayOfBirth,
            yearOfBirth: this.state.yearOfBirth, height: this.state.height, weight: this.state.weight,
            prediction: this.state.prediction,
            baseWeaponSkills: this.state.baseWeaponSkills, endWeaponSkills: this.state.endWeaponSkills,
            baseBallisticSkills: this.state.baseBallisticSkills, endBallisticSkills: this.state.endBallisticSkills,
            baseStrength: this.state.baseStrength, endStrength: this.state.endStrength,
            baseToughness: this.state.baseToughness, endToughness: this.state.endToughness,
            baseAgility: this.state.baseAgility, endAgility: this.state.endAgility,
            baseIntelligence: this.state.baseIntelligence, endIntelligence: this.state.endIntelligence,
            baseWillPower: this.state.baseWillPower, endWillPower: this.state.endWillPower,
            baseFellowship: this.state.baseFellowship, endFellowship: this.state.endFellowship,
            baseAttacks: this.state.baseAttacks, endAttacks: this.state.endAttacks,
            baseWounds: this.state.baseWounds, endWounds: this.state.endWounds,
            baseMovement: this.state.baseMovement, endMovement: this.state.endMovement,
            baseMagic: this.state.baseMagic, endMagic: this.state.endMagic
        };
        return output;
    }


    save = () => {
        let characterInput = this.getDataFromForm();
        generatorService.save(characterInput)
            .then(r => this.saveSuccessHandler(r))
            .catch(e => this.saveErrorHandler(e))
    }

    saveErrorHandler = error => {
        if(error.response.data.errors){
            const errorMsg = error.response.data.errors.map(e => e.defaultMessage)[0]
            this.setState({isError: true, errorText: errorMsg, generated: false})
        }
        else this.setState({isError: true, errorText: error.response.data.message, generated: false})
    }

    saveSuccessHandler = response => {
        this.setState({generated: true, href: "/characterDetails/" + response.data, isError: false})
    }

    fullRandomGenerate = () => {
        generatorService.fullRandomGenerate()
            .then(response => this.fullRandomGenerateSuccessHandler(response))
            .catch(error => console.log(error))
    }

    fullRandomGenerateSuccessHandler = response => {
        this.setState(fullRandomGenerateSuccessHandler(response))
    }

    generateOneAttribute = attrName => {
        const characterInput = this.getDataFromForm();
        generatorService.generateOneAttribute(attrName, characterInput)
            .then(response => this.generateOneAttributeSuccessHandler(attrName, response))
            .catch(error => this.generateOneAttributeErrorHandler(error))
    }

    generateOneAttributeErrorHandler = error => {this.setState({isError: true, errorText: error.response.data.message})}

    generateOneAttributeSuccessHandler = (attrName, response) => {this.setState(generateOneAttributeSuccessHandler(attrName, response));}

    render() {
        return (
            <div className="pageWithContext">
                <div className="pageName">Tworzenie postaci</div>
                <div className="block-element">
                    <div className="flex-component space-between-component">
                        <div className="white-caption">Statystyki:</div>
                        <button className="detaleButton" onClick={() => this.fullRandomGenerate()}><div className = "text-with-fafa">Generuj losowe statystyki
                        </div><span>{element}</span>
                        </button>
                    </div>
                </div>
                <div className = "container-stats">
                        <div className = "column-1">
                            <careerContext.Provider value={{update: (val) => {this.setState({name: val})},}}>
                                <GeneratorTextField label="Imię" generated={this.state.name} canBeGenerated onRandomClick={() => this.generateOneAttribute("Imię")}
                                                    disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null ||
                                                    this.state.sex===undefined || this.state.sex==="" || this.state.sex === null }
                                                    tooltip tootipText={textsPolish.toolTipName}
                                                    ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                                                    ifTooltipSex={(this.state.sex===undefined || this.state.sex==="" || this.state.sex === null)}
                                                    />               
                            </careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({surname: val})},}}>
                                <GeneratorTextField label="Nazwisko" generated={this.state.surname} canBeGenerated onRandomClick={() => this.generateOneAttribute("Nazwisko")}
                                                    disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null ||
                                                    this.state.sex===undefined || this.state.sex==="" || this.state.sex === null} tooltip tootipText={textsPolish.toolTipSurname}
                                                    ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                                                    ifTooltipSex={(this.state.sex===undefined || this.state.sex==="" || this.state.sex === null)}
                                />
                            </careerContext.Provider>


                            <careerContext.Provider value={{
                                update: (val) => {this.setState({race: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Rasa"
                                options={["Człowiek", "Elf", "Krasnolud", "Niziołek"]}
                                id="characterGeneratorRace"
                                width={135}
                                canBeGenerated
                                disablePortal={true}
                                generated={this.state.race}
                                onRandomClick={() => this.generateOneAttribute("Rasa")}
                                disabled={this.state.birthPlace===undefined || this.state.birthPlace==="" || this.state.birthPlace === null}
                                tooltip tootipText={textsPolish.toolTipRace}
                                ifTooltipBirthPlace={this.state.birthPlace===undefined || this.state.birthPlace==="" || this.state.birthPlace === null}
                            />
                            </careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({sex: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Płeć"
                                tooltip tootipText={textsPolish.toolTipSex}
                                options={["Mężczyzna", "Kobieta"]}
                                id="characterGeneratorSex"
                                width={138}
                                canBeGenerated
                                disablePortal
                                generated={this.state.sex}
                                onRandomClick={() => this.generateOneAttribute("Płeć")}
                                disabled={this.state.race === undefined || this.state.race === "" || this.state.race === null}
                                ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                            /></careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({currentCareer: val})},}}>
                                <DefaultMultipleAutocomplete
                                    labelName="Profesja"
                                    options={this.state.autocompleteData.careerNames || []}
                                    id="characterGeneratorCurrentCareer"
                                    canBeGenerated
                                    disablePortal
                                    tooltip tootipText={textsPolish.toolTipProfession}
                                    generated={this.state.currentCareer}
                                    onRandomClick={() => this.generateOneAttribute("Profesja")}
                                    disabled={this.state.race === undefined || this.state.race === "" || this.state.race === null
                                    || this.state.sex === undefined || this.state.sex === "" || this.state.sex === null ||
                                    this.state.birthPlace === undefined || this.state.birthPlace === "" || this.state.birthPlace === null ||
                                    this.state.baseWeaponSkills === undefined || this.state.baseWeaponSkills === "" || this.state.baseWeaponSkills === null ||
                                    this.state.baseBallisticSkills === undefined || this.state.baseBallisticSkills === "" || this.state.baseBallisticSkills === null ||
                                    this.state.baseStrength === undefined || this.state.baseStrength === "" || this.state.baseStrength === null ||
                                    this.state.baseToughness === undefined || this.state.baseToughness === "" || this.state.baseToughness === null ||
                                    this.state.baseAgility === undefined || this.state.baseAgility === "" || this.state.baseAgility === null ||
                                    this.state.baseIntelligence === undefined || this.state.baseIntelligence === "" || this.state.baseIntelligence === null ||
                                    this.state.baseWillPower === undefined || this.state.baseWillPower === "" || this.state.baseWillPower === null ||
                                    this.state.baseFellowship === undefined || this.state.baseFellowship === "" || this.state.baseFellowship === null ||
                                    this.state.baseAttacks === undefined || this.state.baseAttacks === "" || this.state.baseAttacks === null ||
                                    this.state.baseWounds === undefined || this.state.baseWounds === "" || this.state.baseWounds === null ||
                                    this.state.baseMovement === undefined || this.state.baseMovement === "" || this.state.baseMovement === null ||
                                    this.state.baseMagic === undefined || this.state.baseMagic === "" || this.state.baseMagic === null
                                        //ToDo warunki disabled powinny być najlepiej wyrzucone do osobnego pliku
                                    }
                                />
                            </careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({livePlace: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Miejsce pobytu"
                                tooltip tootipText={textsPolish.toolTipPlaceOfBeing}
                                options={this.state.autocompleteData.placeNames || []}
                                id="characterGeneratorLivePlace"
                                canBeGenerated
                                disablePortal
                                generated={this.state.livePlace}
                                onRandomClick={() => this.generateOneAttribute("Miejsce pobytu")}
                                disabled={this.state.birthPlace === undefined || this.state.birthPlace === "" || this.state.birthPlace === null ||
                                this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.religion === undefined || this.state.religion === "" || this.state.religion === null ||
                                this.state.race === undefined || this.state.race === "" || this.state.race === null ||
                                    this.state.sex=== undefined || this.state.sex === "" || this.state.sex === null
                                }
                                ifTooltipRace={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                ifTooltipBirthPlace={this.state.birthPlace===undefined || this.state.birthPlace==="" || this.state.birthPlace === null}
                                ifTooltipSex={this.state.sex===undefined || this.state.sex==="" || this.state.sex === null}
                                ifTooltipProfession={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null}
                                ifTooltipReligion={this.state.religion === undefined || this.state.religion === "" || this.state.religion === null}

                            />
                            </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({birthPlace: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Miejsce urodzenia"
                                options={this.state.autocompleteData.placeNames || []}
                                id="characterGeneratorBirthPlace"
                                generated={this.state.birthPlace}
                                canBeGenerated
                                disablePortal
                                onRandomClick={() => this.generateOneAttribute("Miejsce urodzenia")}
                            />
                            </careerContext.Provider>
                                <careerContext.Provider value={{
                                    update: (val) => {this.setState({dayOfBirth: val})},}}>
                                    <GeneratorTextField label="Dzień urodzenia" generated={this.state.dayOfBirth}
                                    tooltip tootipText={textsPolish.toolTipDayOfBorn}/>
                                </careerContext.Provider>

                                <careerContext.Provider value={{
                                    update: (val) => {this.setState({monthOfBirth: val})},
                                }}><DefaultMultipleAutocomplete
                                    labelName="Miesiąc urodzenia"
                                    options={months}
                                    TooltipReplacement wd={"ad"}
                                    id="characterGeneratorMonthOfBirth"
                                    width={150}
                                    tooltip tootipText={textsPolish.toolTipMonthOfBorn}
                                    disablePortal
                                    generated={this.state.monthOfBirth}
                                    notSortOptions
                                />
                                </careerContext.Provider>
                                <careerContext.Provider value={{
                                    update: (val) => {this.setState({yearOfBirth: val})},}}>
                                    <GeneratorTextField label="Rok urodzenia" generated={this.state.yearOfBirth}
                                                        canBeGenerated
                                                        onRandomClick={() => this.generateOneAttribute("Data urodzenia")}
                                                        disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null} 
                                                        tooltip tootipText={textsPolish.toolTipYearOfBorn}
                                                        ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                                    />
                                </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({religion: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Religia"
                                options={religions || []}
                                id="characterGeneratorReligion"
                                canBeGenerated
                                disablePortal
                                tooltip tootipText={textsPolish.toolTipReligion}
                                generated={this.state.religion}
                                onRandomClick={() => this.generateOneAttribute("Religia")}
                                notSortOptions
                                disabled={this.state.race === undefined || this.state.race === "" || this.state.race === null ||
                                this.state.sex === undefined || this.state.sex === "" || this.state.sex === null ||
                                this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.birthPlace === undefined || this.state.birthPlace === "" || this.state.birthPlace === null
                                }
                                ifTooltipRace={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                ifTooltipBirthPlace={this.state.birthPlace===undefined || this.state.birthPlace==="" || this.state.birthPlace === null}
                                ifTooltipSex={this.state.sex===undefined || this.state.sex==="" || this.state.sex === null}
                                ifTooltipProfession={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null}
                            />
                            </careerContext.Provider>
                </div>
                <div className = "column-1">
                                <careerContext.Provider value={{
                                    update: (val) => {this.setState({height: val})},}}>
                                    <GeneratorTextField label="Wzrost" generated={this.state.height} canBeGenerated onRandomClick={() => this.generateOneAttribute("Wzrost")} disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null || this.state.sex===undefined || this.state.sex==="" || this.state.sex === null} tooltip tootipText={textsPolish.toolTipHeight}
                                                        ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                                                        ifTooltipSex={(this.state.sex===undefined || this.state.sex==="" || this.state.sex === null)}
                                    />
                                </careerContext.Provider>
                                <careerContext.Provider value={{
                                    update: (val) => {this.setState({weight: val})},}}>
                                <GeneratorTextField label="Waga" generated={this.state.weight} canBeGenerated onRandomClick={() => this.generateOneAttribute("Waga")} disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null || this.state.sex===undefined || this.state.sex==="" || this.state.sex === null} tooltip tootipText={textsPolish.toolTipWeight}
                                                    ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                                                    ifTooltipSex={(this.state.sex===undefined || this.state.sex==="" || this.state.sex === null)}
                                />
                                </careerContext.Provider>
                                <careerContext.Provider value={{
                                update: (val) => {this.setState({eyeColor: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Kolor oczu"
                                tooltip tootipText={textsPolish.toolTipColorOfEyes}
                                options={this.state.autocompleteData.eyeColors || []}
                                id="characterGeneratorEyeColor"
                                canBeGenerated
                                disablePortal
                                generated={this.state.eyeColor}
                                onRandomClick={() => this.generateOneAttribute("Kolor oczu")}
                                disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                            /></careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({hairColor: val})},
                            }}><DefaultMultipleAutocomplete
                                labelName="Kolor włosów"
                                options={this.state.autocompleteData.hairColors || []}
                                id="characterGeneratorHairColor"
                                canBeGenerated
                                disablePortal
                                tooltip tootipText={textsPolish.toolTipColorOfHair}
                                generated={this.state.hairColor}
                                onRandomClick={() => this.generateOneAttribute("Kolor włosów")}
                                disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                ifTooltipRace={(this.state.race===undefined || this.state.race==="" || this.state.race === null)}
                            /></careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({personalities: val})},
                            }}><DefaultMultipleAutocomplete
                                labelName="Cechy charakteru"
                                options={this.state.autocompleteData.personalityNames || []}
                                id="characterGeneratorPersonality"
                                multiple
                                disablePortal
                                tooltip tootipText={textsPolish.toolTipCharacter}
                                canBeGenerated
                                generated={this.state.personalities}
                                onRandomClick={() => this.generateOneAttribute("Cechy charakteru")}
                                disabled={this.state.religion === undefined || this.state.religion === "" || this.state.religion === null ||
                                this.state.race === undefined || this.state.race === "" || this.state.race === null ||
                                this.state.yearOfBirth === undefined || this.state.yearOfBirth === "" || this.state.yearOfBirth === null ||
                                this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null
                                }
                            />
                            </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({apperances: val})},
                            }}><DefaultMultipleAutocomplete
                                labelName="Cechy wyglądu"
                                options={this.state.autocompleteData.apperanceNames || []}
                                id="characterGeneratorApperances"
                                multiple
                                tooltip tootipText={textsPolish.toolTipAppearance}
                                canBeGenerated
                                disablePortal
                                generated={this.state.apperances}
                                onRandomClick={() => this.generateOneAttribute("Cechy wyglądu")}
                                disabled={this.state.religion === undefined || this.state.religion === "" || this.state.religion === null ||
                                this.state.race === undefined || this.state.race === "" || this.state.race === null ||
                                this.state.yearOfBirth === undefined || this.state.yearOfBirth === "" || this.state.yearOfBirth === null ||
                                this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.sex === undefined || this.state.sex === "" || this.state.sex === null ||
                                this.state.yearOfBirth === undefined || this.state.yearOfBirth === "" || this.state.yearOfBirth === null ||
                                this.state.weight === undefined || this.state.weight === "" || this.state.weight === null ||
                                this.state.height === undefined || this.state.height === "" || this.state.height === null}
                            />
                            </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({previousCareers: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Poprzednie profesje"
                                tooltip tootipText={textsPolish.toolTipPreviousProfession}
                                options={this.state.autocompleteData.careerNames || []}
                                id="characterGeneratorPreviousCareers"
                                multiple
                                disablePortal
                                generated={this.state.previousCareers}
                            />
                            </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({dominatingEmotions: val})},}}>
                                <DefaultMultipleAutocomplete
                                labelName="Dominujące emocje"
                                tooltip tootipText={textsPolish.toolTipEmotions}
                                options={this.state.autocompleteData.emotionNames || []}
                                id="characterGeneratorEmotions"
                                multiple
                                disablePortal
                                canBeGenerated
                                generated={this.state.dominatingEmotions}
                                onRandomClick={() => this.generateOneAttribute("Dominujące emocje")}
                                disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null ||
                                    this.state.yearOfBirth===undefined || this.state.yearOfBirth==="" || this.state.yearOfBirth === null
                                }
                                ifTooltipRace={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                idTooltipBirthYear={this.state.yearOfBirth===undefined || this.state.yearOfBirth==="" || this.state.yearOfBirth === null}
                            />
                            </careerContext.Provider>
                            <careerContext.Provider value={{
                                update: (val) => {this.setState({skills: val})},
                            }}><DefaultMultipleAutocomplete
                                labelName="Umiejętności"
                                tooltip tootipText={textsPolish.toolTipSkills}
                                options={this.state.autocompleteData.skillNames || []}
                                id="characterGeneratorSkills"
                                multiple
                                disablePortal
                                canBeGenerated
                                generated={this.state.skills}
                                onRandomClick={() => this.generateOneAttribute("Umiejętności")}
                                disabled={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.race === undefined || this.state.race === "" || this.state.race === null}
                                    ifTooltipRace={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                    ifTooltipProfession={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null}

                            /></careerContext.Provider>

                            <careerContext.Provider value={{
                                update: (val) => {this.setState({talents: val})},
                            }}><DefaultMultipleAutocomplete
                                labelName="Zdolności"
                                tooltip tootipText={textsPolish.toolTipTalents}
                                options={this.state.autocompleteData.talentNames || []}
                                id="characterGeneratorTalents"
                                multiple
                                disablePortal
                                canBeGenerated
                                generated={this.state.talents}
                                onRandomClick={() => this.generateOneAttribute("Zdolności")}
                                disabled={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.race === undefined || this.state.race === "" || this.state.race === null
                                }
                                ifTooltipRace={this.state.race===undefined || this.state.race==="" || this.state.race === null}
                                ifTooltipProfession={this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null}
                            /></careerContext.Provider>


                            <careerContext.Provider value={{
                                update: (val) => {this.setState({prediction: val})},
                            }}>
                                <GeneratorTextField label="Przepowiednia" canBeGenerated
                                                    generated={this.state.prediction}
                                                    onRandomClick={() => this.generateOneAttribute("Przepowiednia")}
                                                    disabled={false}
                                                    tooltip tootipText={textsPolish.toolTipProphecy}
                                />
                            </careerContext.Provider>
                    </div>
                    <div className="block-element">
                        <div className = "flex-component space-between-component">
                        <div className="white-caption">Umiejętności bojowe:</div>
                        <div className="flex-element">
                            <button className="detaleButton reverse" onClick={() => this.generateOneAttribute("Bazowe statystyki")} disabled={this.state.race===undefined || this.state.race==="" || this.state.race === null}><div className = "text-with-fafa">Wylosuj statystyki Bazowe </div><span>{element}</span>
                            </button>
                            <button className="detaleButton reverse" onClick={() => this.generateOneAttribute("Statystyki końcowe")} disabled={this.state.baseWeaponSkills === undefined || this.state.baseWeaponSkills === "" || this.state.baseWeaponSkills === null ||
                                this.state.baseBallisticSkills === undefined || this.state.baseBallisticSkills === "" || this.state.baseBallisticSkills === null ||
                                this.state.baseStrength === undefined || this.state.baseStrength === "" || this.state.baseStrength === null ||
                                this.state.baseToughness === undefined || this.state.baseToughness === "" || this.state.baseToughness === null ||
                                this.state.baseAgility === undefined || this.state.baseAgility === "" || this.state.baseAgility === null ||
                                this.state.baseIntelligence === undefined || this.state.baseIntelligence === "" || this.state.baseIntelligence === null ||
                                this.state.baseWillPower === undefined || this.state.baseWillPower === "" || this.state.baseWillPower === null ||
                                this.state.baseFellowship === undefined || this.state.baseFellowship === "" || this.state.baseFellowship === null ||
                                this.state.baseAttacks === undefined || this.state.baseAttacks === "" || this.state.baseAttacks === null ||
                                this.state.baseWounds === undefined || this.state.baseWounds === "" || this.state.baseWounds === null ||
                                this.state.baseMovement === undefined || this.state.baseMovement === "" || this.state.baseMovement === null ||
                                this.state.baseMagic === undefined || this.state.baseMagic === "" || this.state.baseMagic === null ||
                                this.state.currentCareer === undefined || this.state.currentCareer === "" || this.state.currentCareer === null ||
                                this.state.talents === undefined || this.state.talents === "" || this.state.talents === null
                            }><div className = "text-with-fafa">Wylosuj statystyki Obecne </div><span>{element}</span></button>
                        </div>
                        </div>
                        <div className= "center">
                        <div className="block-grid">
                            <div className="grid">
                                <div className="grid-column">
                                    <div className="title-column"> ***</div>
                                    <div className="grid-name-element">Baza</div>
                                    <div className="grid-name-element">Obecnie</div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">WW</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseWeaponSkills: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWeaponSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endWeaponSkills: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWeaponSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">US</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{update: (val) => {this.setState({baseBallisticSkills: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseBallisticSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endBallisticSkills: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endBallisticSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">K</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseStrength: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseStrength}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{update: (val) => {this.setState({endStrength: val})},}}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endStrength}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ODP</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseToughness: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseToughness}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endToughness: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endToughness}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ZR</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseAgility: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseAgility}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endAgility: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endAgility}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">INT</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseIntelligence: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseIntelligence}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endIntelligence: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endIntelligence}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SW</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseWillPower: val})
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWillPower}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({endWillPower: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWillPower}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">OGD</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseFellowship: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseFellowship}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endFellowship: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endFellowship}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="block-grid">
                            <div className="grid">
                                <div className="grid-column">
                                    <div className="title-column"> ***</div>
                                    <div className="grid-name-element">Baza</div>
                                    <div className="grid-name-element">Obecnie</div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">A</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseAttacks: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseAttacks}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endAttacks: val})
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endAttacks}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ŻYW</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({baseWounds: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWounds}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endWounds: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWounds}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SZ</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseMovement: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseMovement}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({endMovement: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endMovement}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">M</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({baseMagic: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseMagic}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {this.setState({endMagic: val})},
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endMagic}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div className="block-element">{this.state.generated &&
                        <div className="positive-message">Aby zobaczyć wygenerowaną postać, kliknij <a href={this.state.href}>tutaj</a></div>}</div>
                        <div className="block-element">{this.state.isError &&
                        <ErrorGenerator errorText={this.state.errorText}/>}</div>
                            <button className="green-button" onClick={this.save}>Zapisz</button>
                        </div>
                    </div>
                </div>

        )
    }
}
export default CharacterGeneratorPage;