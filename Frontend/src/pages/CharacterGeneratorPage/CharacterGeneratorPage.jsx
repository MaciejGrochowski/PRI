import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import MenuItem from "@material-ui/core/MenuItem";
import {starSigns} from "../../enums/StarSigns";
import {religions} from "../../enums/Religions";
import grid from "../../styles/grid.css";

const mygrid = {
        width: '30px',
        height: "30px",
        border: 'solid 1px white',
        borderRadius: '25%',
        marginBotton: '5%',
        marginTop:'5%',
};



class CharacterGeneratorPage extends React.Component {

    constructor() {
        super();
        this.state = {
            autocompleteData: { //To będzie z bazy danych pobierane na podobieństwo filtrów
                careerNames: ["Profesja1", "Profesja2"],
                placeNames: ["Miejsce1", "Miejsce2"],
                eyeColors: ["Kolor1", "Kolor2"],
                hairColors: ["Kolor1", "Kolor2"],
                personalityNames: ["cecha1", "cecha2"],
                apperanceNames: ["cecha1", "cecha2"],
                emotionNames: ["Emocja1", "Emocja2"],
                talentNames: ["Talent1", "Talent2"],
                skillNames: ["Umiejętność1", "Umiejętność2"]
            }
        }
    }


    render(){
        return (
            <div className = "plainPage">
                <div className = "pageName">Tworzenie postaci</div>
                    <div className = "block-element">
                        <div className = "flex-element">
                        <div className = "white-caption">Statystyki: </div>
                        <button className = "detaleButton">Generuj losowe</button>
                        </div>
                    </div>
                    <div className = "flex-element">
                <div className = "block-element">
                    <div className="flex-element">
                        <div className = "generator-element"><TextField label="Imię" id="characterGeneratorName"/></div>
                        <div className = "generator-element"><TextField label="Nazwisko" id="characterGeneratorSurname"/></div>
                    </div>
                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Profesja"
                    options={this.state.autocompleteData.careerNames || []}
                    id="characterGeneratorCurrentCareer"
                /></div>
                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Miejsce pobytu"
                    options={this.state.autocompleteData.placeNames || []}
                    id="characterGeneratorLivePlace"
                /></div>
                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Miejsce urodzenia"
                    options={this.state.autocompleteData.placeNames || []}
                    id="characterGeneratorBirthPlace"
                /></div>
                <div className="ziemniak generator-element">
                    <TextField id="characterGeneratorRace" defaultValue="HUMAN" label="Rasa" select>
                        <MenuItem value={'HUMAN'}>Człowiek</MenuItem>
                        <MenuItem value={'ELF'}>Elf</MenuItem>
                        <MenuItem value={'DWARF'}>Krasnolud</MenuItem>
                        <MenuItem value={'HALFLING'}>Niziołek</MenuItem>
                    </TextField>
                </div>

                <div className="ziemniak generator-element">
                    <TextField defaultValue="MALE" id="characterGeneratorSex" label="Płeć" select>
                        <MenuItem value={'MALE'}>Mężczyzna</MenuItem>
                        <MenuItem value={'FEMALE'}>Kobieta</MenuItem>
                    </TextField>
                </div>
                <div className = "flex-element">
                    <div className = "generator-element"><TextField label="Dzień urodzenia" id="characterGeneratorDayOfBirth"/></div>
                    <div className="ziemniak generator-element">
                    <TextField id="characterGeneratorMonthOfBirth" defaultValue="HUMAN" label="Miesiąc urodzenia" select>
                        {starSigns && starSigns.map((item, i) => (
                            <MenuItem value={i}>{item}</MenuItem>
                            )
                        )}
                    </TextField>
                    </div>
                {/*    ToDo resztę miesięcy sprawdzić i dopisać*/}
                <div className = "generator-element"><TextField label="Rok urodzenia" id="characterGeneratorYearOfBirth"/></div>

            </div>
            <div className="flex-element">
                <div className = "generator-element"><TextField label="Wzrost" id="characterGeneratorHeight"/></div>
                <div className = "generator-element"><TextField label="Waga" id="characterGeneratorWeight"/></div>
            </div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Kolor oczu"
                    options={this.state.autocompleteData.eyeColors || []}
                    id="characterGeneratorEyeColor"
                /></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Kolor włosów"
                    options={this.state.autocompleteData.hairColors || []}
                    id="characterGeneratorHairColor"
                /></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Cechy charakteru"
                    options={this.state.autocompleteData.personalityNames || []}
                    id="characterGeneratorPersonality"
                    multiple
                /></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Cechy wyglądu"
                    options={this.state.autocompleteData.apperanceNames || []}
                    id="characterGeneratorApperances"
                    multiple
                /></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Poprzednie profesje"
                    options={this.state.autocompleteData.careerNames || []}
                    id="characterGeneratorPreviousCareers"
                    multiple
                /></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Dominujące emocje"
                    options={this.state.autocompleteData.emotionNames || []}
                    id="characterGeneratorEmotions"
                    multiple
                /></div>

                <div className = "generator-element"><div className="ziemniak">
                    <TextField id="characterGeneratorReligion" defaultValue="0" label="Religia" select>
                        {religions && religions.map((item, i) => (
                                <MenuItem value={i}>{item}</MenuItem>
                            )
                        )}
                    </TextField>
                </div></div>

                <div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Umiejętności"
                    options={this.state.autocompleteData.skillNames || []}
                    id="characterGeneratorSkills"
                    multiple
                /></div>

<               div className = "generator-element"><DefaultMultipleAutocomplete
                    labelName="Zdolności"
                    options={this.state.autocompleteData.talentNames || []}
                    id="characterGeneratorTalents"
                    multiple
                /></div>

                <div className = "generator-element"><TextField label="Przepowiednia" id="characterGeneratorPrediction"/></div>
                </div>
                <div className="block-element">
                <div className = "white-caption">Umiejętności bojowe</div>
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
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">US</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">K</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODP</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ZR</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">INT</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SW</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ODG</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
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
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">ŻYW</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">S</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">WT</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">SZ</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        <div className="grid-column">
                            <div className = "grid-name-element title-column">M</div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorBaseWeaponSkills"/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorCareerWeaponSkills" disabled/></div>
                            <div className = "grid-element"><TextField style= {mygrid} id="characterGeneratorEndWeaponSkills"/></div>
                        </div>
                        </div></div>
                    <div className = "flex-element">
                        <button className = "green-button">Zapisz</button>
                        <button className = "red-button">Anuluj</button>
                    </div>
                </div>

                </div>
            {/* block-element na umiejętności bojowe
            ToDo na tej samej zasadzie inne skille bojowe. Pamiętać, że drugi element jest disabled (cechy z rozwoju zawsze zależą od profesji i będą autocompletowane) */}
        </div>
        )
    }

}

export default CharacterGeneratorPage;