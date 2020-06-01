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


import ReactDOM from 'react-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSyncAlt} from '@fortawesome/free-solid-svg-icons';
import ErrorGenerator from "../../components/ErrorLayout/ErrorGenerator";
import {careerContext} from "./context";
import GeneratorTextField from "../../components/Generator/GeneratorTextField";

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
        let output = {
            currentCareer: this.state.currentCareer,
            sex: this.state.sex,
            race: this.state.race,
            previousCareers: this.state.previousCareers,
            monthOfBirth: this.state.monthOfBirth,
            eyeColor: this.state.eyeColor,
            hairColor: this.state.hairColor,
            livePlace: this.state.livePlace,
            birthPlace: this.state.birthPlace,
            dominatingEmotions: this.state.dominatingEmotions,
            religion: this.state.religion,
            skills: this.state.skills,
            talents: this.state.talents,
            apperance: this.state.apperances,
            personality: this.state.personalities,
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
        this.setState({isError: true, errorText: error.response.data.message})
    }

    saveSuccessHandler = response => {
        this.setState({generated: true, href: "/characterDetails/" + response.data, isError: false})
        window.open("/characterDetails/" + response.data);
    }

    fullRandomGenerate = () => {
        generatorService.fullRandomGenerate()
            .then(response => this.fullRandomGenerateSuccessHandler(response))
            .catch(error => console.log(error))
    }

    fullRandomGenerateSuccessHandler = response => {
        this.setState({
            // fullGenerated: {name: response.data.name},
            name: response.data.name,
            surname: response.data.surname,
            weight: response.data.weight,
            height: response.data.height,
            dayOfBirth: response.data.dayOfBirth,
            yearOfBirth: response.data.yearOfBird,
            birthPlace: response.data.birthPlace,
            livePlace: response.data.livePlace,
            currentCareer: response.data.currentCareer,
            race: response.data.race,
            sex: response.data.sex,
            monthOfBirth: response.data.monthOfBirth,
            eyeColor: response.data.eyeColor,
            hairColor: response.data.hairColor,
            personalities: response.data.personality,
            apperances: response.data.apperance,
            dominatingEmotions: response.data.dominatingEmotions,
            religion: response.data.religion,
            prediction: response.data.prediction,
            previousCareers: response.data.previousCareers,
            skills: this.mapSkillsArrayToString(response.data.skills),
            talents: this.mapTalentsArrayToString(response.data.talents),
            endWeaponSkills: response.data.endWeaponSkills, endBallisticSkills: response.data.endBallisticSkills,
            endStrength: response.data.endStrength, endToughness: response.data.endToughness,
            endAgility: response.data.endAgility, endIntelligence: response.data.endIntelligence,
            endWillPower: response.data.endWillPower, endFellowship: response.data.endFellowship,
            endAttacks: response.data.endAttacks, endWounds: response.data.endWounds,
            endMovement: response.data.endMovement, endMagic: response.data.endMagic,
            baseWeaponSkills: response.data.baseWeaponSkills, baseBallisticSkills: response.data.baseBallisticSkills,
            baseStrength: response.data.baseStrength, baseToughness: response.data.baseToughness,
            baseAgility: response.data.baseAgility, baseIntelligence: response.data.baseIntelligence,
            baseWillPower: response.data.baseWillPower, baseFellowship: response.data.baseFellowship,
            baseAttacks: response.data.baseAttacks, baseWounds: response.data.baseWounds,
            baseMovement: response.data.baseMovement, baseMagic: response.data.baseMagic
        })
        // if(response.data.previousCareers!=="") this.setState({previousCareers: response.data.previousCareers});
    }

    mapTalentsArrayToString = talents => {
        let output = ""
        talents = talents.map(s => s.name)
        for (const i in talents) {
            output = output + talents[i] + ",";
        }
        return output.substring(0, output.length - 1);
    }

    mapSkillsArrayToString = skills => {
        let output = ""
        skills = skills.map(s => s.name + " +" + s.level)
        for (const i in skills) {
            output = output + skills[i] + ",";
        }
        return output.substring(0, output.length - 1);
    }

    generateOneAttribute = attrName => {
        const characterInput = this.getDataFromForm();
        generatorService.generateOneAttribute(attrName, characterInput)
            .then(response => this.generateOneAttributeSuccessHandler(attrName, response))
    }

    generateOneAttributeSuccessHandler = (attrName, response) => {
        if(attrName==="Miejsce urodzenia") this.setState({birthPlace: response.data.birthPlace})
        if(attrName==="Rasa") this.setState({race: response.data.race})
        if(attrName==="Płeć") this.setState({sex: response.data.sex})
    }


    render() {
        return (
            <div className="pageWithContext">
                <div className="pageName">Tworzenie postaci</div>
                <div className="block-element">{this.state.generated &&
                <div className="positive-message">Aby zobaczyć wygenerowaną postać, kliknij <a
                    href={this.state.href}>tutaj</a></div>}</div>
                <div className="block-element">{this.state.isError &&
                <ErrorGenerator errorText={this.state.errorText}/>}</div>
                <div className="block-element">
                    <div className="flex-div">
                        <div className="white-caption">Statystyki:</div>
                        <button className="detaleButton" onClick={() => this.fullRandomGenerate()}>Generuj losowe
                        </button>
                    </div>
                </div>
                <div className="flex-div">
                    <div className="block-element">
                        <div className="flex-div">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        name: val
                                    })
                                },
                            }}>
                                <GeneratorTextField label="Imię" generated={this.state.name} canBeGenerated/>
                            </careerContext.Provider>


                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        surname: val
                                    })
                                },
                            }}>
                                <GeneratorTextField label="Nazwisko" generated={this.state.surname} canBeGenerated/>
                            </careerContext.Provider>

                        </div>
                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        currentCareer: val
                                    })
                                },
                            }}>
                                <DefaultMultipleAutocomplete
                                    labelName="Profesja"
                                    options={this.state.autocompleteData.careerNames || []}
                                    id="characterGeneratorCurrentCareer"
                                    canBeGenerated
                                    generated={this.state.currentCareer}
                                />
                            </careerContext.Provider>
                        </div>
                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        livePlace: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Miejsce pobytu"
                                options={this.state.autocompleteData.placeNames || []}
                                id="characterGeneratorLivePlace"
                                canBeGenerated
                                generated={this.state.livePlace}
                            />
                            </careerContext.Provider>
                        </div>
                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        birthPlace: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Miejsce urodzenia"
                                options={this.state.autocompleteData.placeNames || []}
                                id="characterGeneratorBirthPlace"
                                generated={this.state.birthPlace}
                                canBeGenerated
                                onRandomClick={() => this.generateOneAttribute("Miejsce urodzenia")}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        race: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Rasa"
                                options={["Człowiek", "Elf", "Krasnolud", "Niziołek"]}
                                id="characterGeneratorRace"
                                width={135}
                                canBeGenerated
                                generated={this.state.race}
                                onRandomClick={() => this.generateOneAttribute("Rasa")}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        sex: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Płeć"
                                options={["Mężczyzna", "Kobieta"]}
                                id="characterGeneratorSex"
                                width={138}
                                canBeGenerated
                                generated={this.state.sex}
                                onRandomClick={() => this.generateOneAttribute("Płeć")}
                            /></careerContext.Provider>
                        </div>

                        <div className="flex-div">
                            <div className="generator-element">
                                <careerContext.Provider value={{
                                    update: (val) => {
                                        this.setState({
                                            dayOfBirth: val
                                        })
                                    },
                                }}>
                                    <GeneratorTextField label="Dzień urodzenia" generated={this.state.dayOfBirth}
                                                        canBeGenerated/>
                                </careerContext.Provider>

                            </div>


                            <div className="generator-element">
                                <careerContext.Provider value={{
                                    update: (val) => {
                                        this.setState({
                                            monthOfBirth: val
                                        })
                                    },
                                }}><DefaultMultipleAutocomplete
                                    labelName="Miesiąc"
                                    options={months}
                                    id="characterGeneratorMonthOfBirth"
                                    width={150}
                                    generated={this.state.monthOfBirth}
                                />
                                </careerContext.Provider>
                            </div>
                            <div className="generator-element">
                                <careerContext.Provider value={{
                                    update: (val) => {
                                        this.setState({
                                            yearOfBirth: val
                                        })
                                    },
                                }}>
                                    <GeneratorTextField label="Rok urodzenia" generated={this.state.yearOfBirth}
                                                        canBeGenerated/>
                                </careerContext.Provider>

                            </div>

                        </div>
                        <div className="flex-div">
                            <div className="generator-element">
                                <careerContext.Provider value={{
                                    update: (val) => {
                                        this.setState({
                                            height: val
                                        })
                                    },
                                }}>
                                    <GeneratorTextField label="Wzrost" generated={this.state.height} canBeGenerated/>
                                </careerContext.Provider>

                            </div>
                            <div className="generator-element">
                                <careerContext.Provider value={{
                                    update: (val) => {
                                        this.setState({
                                            weight: val
                                        })
                                    },
                                }}>
                                    <GeneratorTextField label="Waga" generated={this.state.weight} canBeGenerated/>
                                </careerContext.Provider>
                            </div>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        eyeColor: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Kolor oczu"
                                options={this.state.autocompleteData.eyeColors || []}
                                id="characterGeneratorEyeColor"
                                canBeGenerated
                                generated={this.state.eyeColor}
                            /></careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        hairColor: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Kolor włosów"
                                options={this.state.autocompleteData.hairColors || []}
                                id="characterGeneratorHairColor"
                                canBeGenerated
                                generated={this.state.hairColor}
                            /></careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        personalities: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Cechy charakteru"
                                options={this.state.autocompleteData.personalityNames || []}
                                id="characterGeneratorPersonality"
                                multiple
                                canBeGenerated
                                generated={this.state.personalities}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        apperances: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Cechy wyglądu"
                                options={this.state.autocompleteData.apperanceNames || []}
                                id="characterGeneratorApperances"
                                multiple
                                canBeGenerated
                                generated={this.state.apperances}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        previousCareers: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Poprzednie profesje"
                                options={this.state.autocompleteData.careerNames || []}
                                id="characterGeneratorPreviousCareers"
                                multiple
                                canBeGenerated
                                generated={this.state.previousCareers}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        dominatingEmotions: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Dominujące emocje"
                                options={this.state.autocompleteData.emotionNames || []}
                                id="characterGeneratorEmotions"
                                multiple
                                canBeGenerated
                                generated={this.state.dominatingEmotions}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        religion: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Religia"
                                options={religions || []}
                                id="characterGeneratorReligion"
                                canBeGenerated
                                generated={this.state.religion}
                            />
                            </careerContext.Provider>
                        </div>

                        <div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        skills: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Umiejętności"
                                options={this.state.autocompleteData.skillNames || []}
                                id="characterGeneratorSkills"
                                multiple
                                canBeGenerated
                                generated={this.state.skills}
                            /></careerContext.Provider>
                        </div>

                        <               div className="generator-element">
                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        talents: val
                                    })
                                },
                            }}><DefaultMultipleAutocomplete
                                labelName="Zdolności"
                                options={this.state.autocompleteData.talentNames || []}
                                id="characterGeneratorTalents"
                                multiple
                                canBeGenerated
                                generated={this.state.talents}
                            /></careerContext.Provider>
                        </div>

                        <div className="generator-element">

                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        prediction: val
                                    })
                                },
                            }}>
                                <GeneratorTextField label="Przepowiednia" canBeGenerated
                                                    generated={this.state.prediction}/>
                            </careerContext.Provider>
                        </div>
                    </div>
                    <div className="block-element">

                        <div className="white-caption">Umiejętności bojowe</div>
                        <div className="block-component">
                            <button className="detaleButton" disabled>Wylosuj statystyki Bazowe <span>{element}</span>
                            </button>
                            <button className="detaleButton">Wylosuj statystyki Obecnie <span>{element}</span></button>
                        </div>
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
                                            update: (val) => {
                                                this.setState({
                                                    baseWeaponSkills: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWeaponSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endWeaponSkills: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWeaponSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">US</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseBallisticSkills: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseBallisticSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endBallisticSkills: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endBallisticSkills}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">K</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseStrength: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseStrength}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endStrength: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endStrength}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ODP</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseToughness: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseToughness}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endToughness: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endToughness}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">ZR</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseAgility: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseAgility}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endAgility: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endAgility}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">INT</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseIntelligence: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseIntelligence}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endIntelligence: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endIntelligence}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SW</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseWillPower: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWillPower}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endWillPower: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWillPower}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">OGD</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseFellowship: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseFellowship}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endFellowship: val
                                                })
                                            },
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
                                            update: (val) => {
                                                this.setState({
                                                    baseAttacks: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseAttacks}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endAttacks: val
                                                })
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
                                                this.setState({
                                                    baseWounds: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseWounds}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endWounds: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endWounds}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">SZ</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseMovement: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseMovement}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endMovement: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endMovement}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                                <div className="grid-column">
                                    <div className="grid-name-element title-column">M</div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    baseMagic: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.baseMagic}/>
                                        </careerContext.Provider>
                                    </div>
                                    <div className="grid-element">
                                        <careerContext.Provider value={{
                                            update: (val) => {
                                                this.setState({
                                                    endMagic: val
                                                })
                                            },
                                        }}>
                                            <GeneratorTextField style={mygrid} generated={this.state.endMagic}/>
                                        </careerContext.Provider>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="flex-center-element">
                            <button className="green-button" onClick={this.save}>Zapisz</button>
                            {/* <button className="red-button">Anuluj</button> */}
                        </div>
                    </div>
                </div>
            </div>
        )
    }

}

export default CharacterGeneratorPage;