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
            previousCareers: this.mapFilterArrayToString(this.state.previousCareers),
            monthOfBirth: this.state.monthOfBirth,
            eyeColor: this.state.eyeColor,
            hairColor: this.state.hairColor,
            livePlace: this.state.livePlace,
            birthPlace: this.state.birthPlace,
            emotions: this.mapFilterArrayToString(this.state.dominatingEmotions),
            religion: this.state.religion,
            skills: this.mapFilterArrayToString(this.state.skills),
            talents: this.mapFilterArrayToString(this.state.talents),
            apperances: this.mapFilterArrayToString(this.state.apperances),
            personalities: this.mapFilterArrayToString(this.state.personalities),
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
        //
        // const baseWeaponSkills = document.getElementById("characterGeneratorBaseWeaponSkills");
        // if (baseWeaponSkills && baseWeaponSkills.value !== "") output = {
        //     ...output,
        //     baseWeaponSkills: baseWeaponSkills.value
        // }
        // const endWeaponSkills = document.getElementById("characterGeneratorEndWeaponSkills");
        // if (endWeaponSkills && endWeaponSkills.value !== "") output = {
        //     ...output,
        //     endWeaponSkills: endWeaponSkills.value
        // }
        // const baseIntelligence = document.getElementById("characterGeneratorBaseIntelligence");
        // if (baseIntelligence && baseIntelligence.value !== "") output = {
        //     ...output,
        //     baseIntelligence: baseIntelligence.value
        // }
        // const endIntelligence = document.getElementById("characterGeneratorEndIntelligence");
        // if (endIntelligence && endIntelligence.value !== "") output = {
        //     ...output,
        //     endIntelligence: endIntelligence.value
        // }
        // const baseWillPower = document.getElementById("characterGeneratorBaseWillPower");
        // if (baseWillPower && baseWillPower.value !== "") output = {...output, baseWillPower: baseWillPower.value}
        // const endWillPower = document.getElementById("characterGeneratorEndWillPower");
        // if (endWillPower && endWillPower.value !== "") output = {...output, endWillPower: endWillPower.value}
        // const baseFellowship = document.getElementById("characterGeneratorBaseFellowship");
        // if (baseFellowship && baseFellowship.value !== "") output = {...output, baseFellowship: baseFellowship.value}
        // const endFellowship = document.getElementById("characterGeneratorEndFellowship");
        // if (endFellowship && endFellowship.value !== "") output = {...output, endFellowship: endFellowship.value}
        // const baseAttacks = document.getElementById("characterGeneratorBaseAttacks");
        // if (baseAttacks && baseAttacks.value !== "") output = {...output, baseAttacks: baseAttacks.value}
        // const endAttacks = document.getElementById("characterGeneratorEndAttacks");
        // if (endAttacks && endAttacks.value !== "") output = {...output, endAttacks: endAttacks.value}
        // const baseWounds = document.getElementById("characterGeneratorBaseWounds");
        // if (baseWounds && baseWounds.value !== "") output = {...output, baseWounds: baseWounds.value}
        // const endWounds = document.getElementById("characterGeneratorEndWounds");
        // if (endWounds && endWounds.value !== "") output = {...output, endWounds: endWounds.value}
        // const baseMovement = document.getElementById("characterGeneratorBaseMovement");
        // if (baseMovement && baseMovement.value !== "") output = {...output, baseMovement: baseMovement.value}
        // const endMovement = document.getElementById("characterGeneratorEndMovement");
        // if (endMovement && endMovement.value !== "") output = {...output, endMovement: endMovement.value}
        // const baseMagic = document.getElementById("characterGeneratorBaseMagic");
        // if (baseMagic && baseMagic.value !== "") output = {...output, baseMagic: baseMagic.value}
        // const endMagic = document.getElementById("characterGeneratorEndMagic");
        // if (endMagic && endMagic.value !== "") output = {...output, endMagic: endMagic.value}
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
        this.setState({generated: true, href: "/characterDetails/" + response.data})
        window.open("/characterDetails/" + response.data);
    }

    fullRandomGenerate = () => {
        this.setState({fullGenerated: {name: "Jan"}, name: "Jan"}) //ToDo Trzeba tak wszystkie cechy.
    }

    render() {
        return (
            <div className="pageWithContext">
                <div className="pageName">Tworzenie postaci</div>
                <div className="block-element">{this.state.generated &&
                <div className="positive-message">Aby zobaczyć wygenerowaną postać, kliknij <a
                    href={this.state.href}>tutaj</a></div>}</div>
                <div className ="block-element">{this.state.isError && <ErrorGenerator errorText={this.state.errorText}/>}</div>
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
                                <GeneratorTextField label="Imię" generated={this.state.fullGenerated.name} canBeGenerated/>
                            </careerContext.Provider>


                            <careerContext.Provider value={{
                                update: (val) => {
                                    this.setState({
                                        surname: val
                                    })
                                },
                            }}>
                                <GeneratorTextField label="Nazwisko" canBeGenerated/>
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
                                canBeGenerated
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
                                    <GeneratorTextField label="Dzień urodzenia" canBeGenerated/>
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
                                    <GeneratorTextField label="Rok urodzenia" canBeGenerated/>
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
                                    <GeneratorTextField label="Wzrost" canBeGenerated/>
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
                                    <GeneratorTextField label="Waga" canBeGenerated/>
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
                                <GeneratorTextField label="Przepowiednia" canBeGenerated/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                            <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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
                                        <GeneratorTextField style={mygrid}/>
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