import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";

export const columnConfig = (careerNames=[]) => (
    [
        {
            title: 'Imię', field: 'name',
            removable: true,
            headerStyle: {
                backgroundColor: '#292F2F',
            },
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
                <TextField style = {{width: '120px'}} defaultValue="fill" id="characterFilterSex" label="Płeć" select>
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
                <TextField id="characterFilterRace" defaultValue="fill" style = {{width: '120px'}} label="Rasa" select>
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
            filter: <div class = "ziemniak"><TextField label="Miejsce pobytu"/></div>
        },
        {
            title: 'Profesja',
            field: 'careerName',
            filter: <DefaultMultipleAutocomplete
                labelName="Profesje"
                options={careerNames}
                id="characterFilterCareers"
            />
        }
    ]
)
