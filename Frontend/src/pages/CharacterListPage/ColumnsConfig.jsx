import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";
import {religions} from "../../enums/Religions";
import {starSigns} from "../../enums/StarSigns";

export const columnConfig = (autocompleteData={}, visibilityProperties={}) => (
    [
        {
            title: 'Imię', field: 'name',
            removable: true,
            hidden: !visibilityProperties.name,
            filter: <div class = "ziemniak"><TextField label="Imię" id="characterFilterName"/></div>
        },
        {
            title: 'Nazwisko',
            field: 'surname',
            hidden: !visibilityProperties.surname,
            filter: <div class = "ziemniak"><TextField label="Nazwisko" id="characterFilterSurname"/></div>
        },
        {
            title: 'Płeć',
            field: 'sex',
            hidden: !visibilityProperties.sex,
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
            hidden: !visibilityProperties.race,
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
            hidden: !visibilityProperties.livePlace,
            field: 'livePlace',
            filter: <div class = "ziemniak"><TextField id="characterFilterLivePlace" label="Miejsce pobytu"/></div>
        },
        {
            title: 'Profesja',
            field: 'currentCareer',
            hidden: !visibilityProperties.career,
            filter: <div class = "ziemniak"> <DefaultMultipleAutocomplete
                labelName="Profesje"
                options={autocompleteData.careerNames || []}
                id="characterFilterCareers"
            />
            </div>
        },
        {
            title: 'Kolor oczu',
            field: 'eyeColor',
            hidden: !visibilityProperties.eyeColor,
            filter: <div className="ziemniak"><TextField label="Kolor oczu" id="characterFilterEyeColor"/></div>
        },

        {
            title: 'Kolor włosów',
            field: 'hairColor',
            hidden: !visibilityProperties.hairColor,
            filter: <div className="ziemniak"><TextField label="Kolor włosów" id="characterFilterHairColor"/></div>
        },

        {
            title: 'Miejsce urodzenia',
            field: 'birthPlace',
            hidden: !visibilityProperties.birthPlace,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miejsce urodzenia"
                options={autocompleteData.placeNames || []}
                id="characterFilterBirthPlaces"
            /></div>
        },

        {
            title: 'Znak gwiezdny',
            field: 'starSign',
            hidden: !visibilityProperties.starSign,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Znak gwiezdny"
                options={starSigns}
                id="characterFilterStarSigns"
            /></div>
        },

        {
            title: 'Emocje',
            field: 'emotions',
            hidden: !visibilityProperties.emotions,
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Emocje"
                options={autocompleteData.emotionNames || []}
                id="characterFilterEmotions"
            /></div>
        },

        {
            title: 'Religia',
            field: 'religion',
            hidden: !visibilityProperties.religion,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Religia"
                options={religions}
                id="characterFilterReligions"
            /></div>
        },

        {
            title: 'Przepowiednia',
            field: 'prediction',
            hidden: !visibilityProperties.prediction,
            filter: <div className="ziemniak"><TextField label="Przepowiednia" id="characterFilterPrediction"/></div>
        },

        {
            title: 'Umiejętności',
            sorting: false,
            hidden: !visibilityProperties.skills,
            field: 'skills',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Umiejętności"
                options={autocompleteData.skillNames || []}
                id="characterFilterSkills"
            /></div>
        },
        {
            title: 'Zdolności',
            field: 'talents',
            hidden: !visibilityProperties.talents,
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Zdolności"
                options={autocompleteData.talentNames || []}
                id="characterFilterTalents"
            /></div>
        },

        {
            title: 'Cechy charakteru',
            field: 'personalities',
            hidden: !visibilityProperties.personalities,
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Cechy charakteru"
                options={autocompleteData.personalityNames || []}
                id="characterFilterPersonalities"
            /></div>
        },


        {
            title: 'Cechy wyglądu',
            field: 'apperances',
            hidden: !visibilityProperties.apperances,
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Cechy wyglądu"
                options={autocompleteData.apperanceNames || []}
                id="characterFilterApperances"
            /></div>
        },







    ]
)
