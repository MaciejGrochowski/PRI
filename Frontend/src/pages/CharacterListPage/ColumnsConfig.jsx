import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";
//ToDo Więcej autocompletów...

export const columnConfig = (autocompleteData={}, visibilityProperties={}) => {
    return [
        {
            title: 'Imię', field: 'name',
            removable: true,
            hidden: !visibilityProperties.name,
            filter: <div class="ziemniak"><TextField label="Imię" id="characterFilterName"/></div>
        },
        {
            title: 'Nazwisko',
            field: 'surname',
            hidden: !visibilityProperties.surname,
            filter: <div class="ziemniak"><TextField label="Nazwisko" id="characterFilterSurname"/></div>
        },
        { //ToDo Kasia Ostylować to, aby rasa i płeć były krótsze jako autocomplety
            title: 'Płeć',
            field: 'sex',
            hidden: !visibilityProperties.sex,
            lookup: {"MALE": "M", "FEMALE": "K"},
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                    labelName="Płeć"
                    options={["Mężczyzna", "Kobieta"]}
                    id="characterFilterSex"
                /></div>
        },
        {
            title: 'Rasa',
            field: 'race',
            hidden: !visibilityProperties.race,
            lookup: {"HUMAN": "Człowiek", "DWARF": "Krasnolud", "ELF": "Elf", "HALFLING": "Niziołek"},
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Rasa"
                options={["Człowiek", "Elf", "Krasnolud", "Niziołek"]}
                id="characterFilterRace"
                multiple
            /></div>




            //     <div class="ziemniak">
            //     <TextField id="characterFilterRace" defaultValue="fill" label="Rasa" select>
            //         <MenuItem value={'fill'}>Dowolna</MenuItem>
            //         <MenuItem value={'HUMAN'}>Człowiek</MenuItem>
            //         <MenuItem value={'ELF'}>Elf</MenuItem>
            //         <MenuItem value={'DWARF'}>Krasnolud</MenuItem>
            //         <MenuItem value={'HALFLING'}>Niziołek</MenuItem>
            //     </TextField>
            // </div>
        },
        {
            title: 'Miejsce pobytu',
            cellStyle: {width: '700px'},
            hidden: !visibilityProperties.livePlace,
            field: 'livePlace',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miejsce pobytu"
                options={autocompleteData.placeNames || []}
                id="characterFilterLivePlaces"
                multiple
            /></div>
        },
        {
            title: 'Profesja',
            field: 'currentCareer',
            hidden: !visibilityProperties.currentCareer,
            filter: <div class="ziemniak"><DefaultMultipleAutocomplete
                labelName="Profesje"
                options={autocompleteData.careerNames || []}
                id="characterFilterCareers"
                multiple
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
                multiple
            /></div>
        },

        {
            title: 'Znak gwiezdny',
            field: 'starSign',
            hidden: !visibilityProperties.starSign,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Znak gwiezdny"
                options={autocompleteData.starSignNames || []}
                id="characterFilterStarSigns"
                multiple
            /></div>
        },

        {
            title: 'Emocje',
            field: 'dominatingEmotions',
            hidden: !visibilityProperties.dominatingEmotions,
            sorting: false,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Emocje"
                options={autocompleteData.emotionNames || []}
                id="characterFilterEmotions"
                multiple
            /></div>
        },

        {
            title: 'Religia',
            field: 'religion',
            hidden: !visibilityProperties.religion,
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Religia"
                options={autocompleteData.religionNames || []}
                id="characterFilterReligions"
                multiple
                notSortOptions
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
                multiple
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
                multiple
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
                multiple
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
                multiple
            /></div>
        },


    ]
}
