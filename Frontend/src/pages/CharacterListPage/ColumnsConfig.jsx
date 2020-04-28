import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";

export const columnConfig = (careerNames=[], birthPlaces=[], starSigns=[], emotions=[], religions=[], skills=[], talents=[], personalities=[], apperances=[]) => (
    [
        {
            title: 'Imię', field: 'name',
            removable: true,
            filter: <div class = "ziemniak"><TextField label="Imię" id="characterFilterName"/></div>
        },
        {
            title: 'Nazwisko',
            field: 'surname',
            filter: <div class = "ziemniak"><TextField label="Nazwisko" id="characterFilterSurname"/></div>
        },
        {
            title: 'Płeć',
            field: 'sex',
            lookup: {"MALE": "M", "FEMALE": "K"},
            filter: <div class = "ziemniak">
                <TextField defaultValue="fill" id="characterFilterSex" label="Płeć" select>
                        <MenuItem value={'fill'}>Dowolna</MenuItem>
                        <MenuItem value={'MALE'}>Mężczyzna</MenuItem>
                        <MenuItem value={'FEMALE'}>Kobieta</MenuItem>
                </TextField>
                </div>
        },
        {
            title: 'Rasa',
            field: 'race',
            lookup: {"HUMAN": "Człowiek", "DWARF": "Krasnolud", "ELF": "Elf", "HALFLING": "Niziołek"},
            filter: <div class = "ziemniak">
                <TextField id="characterFilterRace" defaultValue="fill" label="Rasa" select>
                    <MenuItem value={'fill'}>Dowolna</MenuItem>
                    <MenuItem value={'HUMAN'}>Człowiek</MenuItem>
                    <MenuItem value={'ELF'}>Elf</MenuItem>
                    <MenuItem value={'DWARF'}>Krasnolud</MenuItem>
                    <MenuItem value={'HALFLING'}>Niziołek</MenuItem>
                    </TextField>
            </div>
        },
        {
            title: 'Miejsce pobytu',
            cellStyle: {width: '700px'},
            field: 'livePlace',
            filter: <div class = "ziemniak"><TextField id="characterFilterLivePlace" label="Miejsce pobytu"/></div>
        },
        {
            title: 'Profesja',
            field: 'careerName',
            filter: <div class = "ziemniak"> <DefaultMultipleAutocomplete
                labelName="Profesje"
                options={careerNames}
                id="characterFilterCareers"
            />
            </div>
        },
        {
            title: 'Kolor oczu',
            field: 'eyeColor',
            filter: <div className="ziemniak"><TextField label="Kolor oczu" id="characterFilterEyeColor"/></div>
        },

        {
            title: 'Kolor włosów',
            field: 'hairColor',
            filter: <div className="ziemniak"><TextField label="Kolor włosów" id="characterFilterHairColor"/></div>
        },

        {
            title: 'Miejsce urodzenia',
            field: 'birthPlace',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miejsce urodzenia"
                options={birthPlaces}
                id="characterFilterBirthPlaces"
            /></div>
        },

        {
            title: 'Znak gwiezdny',
            field: 'starSign',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Znak gwiezdny"
                options={starSigns}
                id="characterFilterStarSigns"
            /></div>
        },

        {
            title: 'Emocje',
            field: 'emotions',
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Emocje"
                options={emotions}
                id="characterFilterEmotions"
            /></div>
        },

        {
            title: 'Religia',
            field: 'religion',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Religia"
                options={religions}
                id="characterFilterReligions"
            /></div>
        },

        {
            title: 'Przepowiednia',
            field: 'prediction',
            filter: <div className="ziemniak"><TextField label="Przepowiednia" id="characterFilterPrediction"/></div>
        },

        {
            title: 'Umiejętności',
            sorting: false,
            field: 'skills',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Umiejętności"
                options={skills}
                id="characterFilterSkills"
            /></div>
        },
        {
            title: 'Zdolności',
            field: 'talents',
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Zdolności"
                options={talents}
                id="characterFilterTalents"
            /></div>
        },

        {
            title: 'Cechy charakteru',
            field: 'personalities',
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Cechy charakteru"
                options={personalities}
                id="characterFilterPersonalities"
            /></div>
        },


        {
            title: 'Cechy wyglądu',
            field: 'apperances',
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Cechy wyglądu"
                options={apperances}
                id="characterFilterApperances"
            /></div>
        },







    ]
)
