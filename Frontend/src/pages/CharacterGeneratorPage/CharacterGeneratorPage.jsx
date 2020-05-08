import React from "react";
import {Link} from "react-router-dom";
import {TextField} from "@material-ui/core";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import MenuItem from "@material-ui/core/MenuItem";
import {starSigns} from "../../enums/StarSigns";
import {religions} from "../../enums/Religions";


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
                <TextField label="Imię" id="characterGeneratorName"/>
                <TextField label="Nazwisko" id="characterGeneratorSurname"/>
                <DefaultMultipleAutocomplete
                    labelName="Profesja"
                    options={this.state.autocompleteData.careerNames || []}
                    id="characterGeneratorCurrentCareer"
                />
                <DefaultMultipleAutocomplete
                    labelName="Miejsce pobytu"
                    options={this.state.autocompleteData.placeNames || []}
                    id="characterGeneratorLivePlace"
                />
                <DefaultMultipleAutocomplete
                    labelName="Miejsce urodzenia"
                    options={this.state.autocompleteData.placeNames || []}
                    id="characterGeneratorBirthPlace"
                />
                <div className="ziemniak">
                    <TextField id="characterGeneratorRace" defaultValue="HUMAN" label="Rasa" select>
                        <MenuItem value={'HUMAN'}>Człowiek</MenuItem>
                        <MenuItem value={'ELF'}>Elf</MenuItem>
                        <MenuItem value={'DWARF'}>Krasnolud</MenuItem>
                        <MenuItem value={'HALFLING'}>Niziołek</MenuItem>
                    </TextField>
                </div>

                <div className="ziemniak">
                    <TextField defaultValue="MALE" id="characterGeneratorSex" label="Płeć" select>
                        <MenuItem value={'MALE'}>Mężczyzna</MenuItem>
                        <MenuItem value={'FEMALE'}>Kobieta</MenuItem>
                    </TextField>
                </div>

                <TextField label="Dzień urodzenia" id="characterGeneratorDayOfBirth"/>

                <div className="ziemniak">
                    <TextField id="characterGeneratorMonthOfBirth" defaultValue="HUMAN" label="Miesiąc urodzenia" select>
                        <MenuItem value={'1'}>Nachgeheim</MenuItem>
                        <MenuItem value={'2'}>Sigmarzeit</MenuItem>
                        <MenuItem value={'3'}>Ulriczeit</MenuItem>
                        <MenuItem value={'4'}>Vorgeheim</MenuItem>
                    </TextField>
                </div>
            {/*    ToDo resztę miesięcy sprawdzić i dopisać*/}

                <TextField label="Rok urodzenia" id="characterGeneratorYearOfBirth"/>

                <TextField label="Wzrost" id="characterGeneratorHeight"/>

                <TextField label="Waga" id="characterGeneratorWeight"/>

                <div className="ziemniak">
                    <TextField id="characterGeneratorMonthOfBirth" defaultValue="HUMAN" label="Miesiąc urodzenia" select>
                        {starSigns && starSigns.map((item, i) => (
                            <MenuItem value={i}>{item}</MenuItem>
                            )
                        )}
                    </TextField>
                </div>

                <DefaultMultipleAutocomplete
                    labelName="Kolor oczu"
                    options={this.state.autocompleteData.eyeColors || []}
                    id="characterGeneratorEyeColor"
                />

                <DefaultMultipleAutocomplete
                    labelName="Kolor włosów"
                    options={this.state.autocompleteData.hairColors || []}
                    id="characterGeneratorHairColor"
                />

                <DefaultMultipleAutocomplete
                    labelName="Cechy charakteru"
                    options={this.state.autocompleteData.personalityNames || []}
                    id="characterGeneratorPersonality"
                    multiple
                />

                <DefaultMultipleAutocomplete
                    labelName="Cechy wyglądu"
                    options={this.state.autocompleteData.apperanceNames || []}
                    id="characterGeneratorApperances"
                    multiple
                />

                <DefaultMultipleAutocomplete
                    labelName="Poprzednie profesje"
                    options={this.state.autocompleteData.careerNames || []}
                    id="characterGeneratorPreviousCareers"
                    multiple
                />

                <DefaultMultipleAutocomplete
                    labelName="Dominujące emocje"
                    options={this.state.autocompleteData.emotionNames || []}
                    id="characterGeneratorEmotions"
                    multiple
                />

                <div className="ziemniak">
                    <TextField id="characterGeneratorReligion" defaultValue="0" label="Religia" select>
                        {religions && religions.map((item, i) => (
                                <MenuItem value={i}>{item}</MenuItem>
                            )
                        )}
                    </TextField>
                </div>


                <DefaultMultipleAutocomplete
                    labelName="Umiejętności"
                    options={this.state.autocompleteData.skillNames || []}
                    id="characterGeneratorSkills"
                    multiple
                />

                <DefaultMultipleAutocomplete
                    labelName="Zdolności"
                    options={this.state.autocompleteData.talentNames || []}
                    id="characterGeneratorTalents"
                    multiple
                />

                <TextField label="Przepowiednia" id="characterGeneratorPrediction"/>

                <TextField  id="characterGeneratorBaseWeaponSkills"/>
                <TextField  id="characterGeneratorCareerWeaponSkills" disabled/>
                <TextField  id="characterGeneratorEndWeaponSkills"/>
                {/*ToDo na tej samej zasadzie inne skille bojowe. Pamiętać, że drugi element jest disabled (cechy z rozwoju zawsze zależą od profesji i będą autocompletowane)*/}



            </div>
        )
    }

}

export default CharacterGeneratorPage;